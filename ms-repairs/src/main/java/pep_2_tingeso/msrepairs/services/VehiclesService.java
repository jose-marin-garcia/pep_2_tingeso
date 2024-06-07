package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msrepairs.dto.VehicleDto;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.model.Vehicle;
import pep_2_tingeso.msrepairs.repositories.HistoricRepository;
import pep_2_tingeso.msrepairs.repositories.MarksRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class VehiclesService {

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    MarksRepository markRepository;

    public List<VehicleDto> getVehiclesNotFinished() {
        List<HistoricEntity> historiales = historicRepository.findAllByEndhourIsNullAndEnddateIsNull();
        System.out.println(historiales);
        List<VehicleDto> vehicles = new ArrayList<>();
        for (HistoricEntity h : historiales) {
            Vehicle v = restTemplate.getForObject("http://ms-vehicles/vehicles/"+h.getPatent(), Vehicle.class);
            System.out.println(v);
            VehicleDto vdto = new VehicleDto(v.getPatent(), markRepository.findById(v.getMark()).get().getMarkName(), v.getModel(), v.getType(), v.getYear(), v.getTypemotor(), v.getNumberseats(), v.getKilometers());
            System.out.println(vdto);
            vehicles.add(vdto);
        }
        return vehicles;
    }

    public Vehicle finishVehicle(String patent) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String fechaActual = fechaHora.format(formatoFecha);
        String horaActual = fechaHora.format(formatoHora);

        HistoricEntity historial = historicRepository.findByPatent(patent);
        historial.setEnddate(fechaActual);
        historial.setEndhour(horaActual);
        historicRepository.save(historial);
        return restTemplate.getForObject("http://ms-vehicles/vehicles/"+historial.getPatent(), Vehicle.class);
    }

    public List<VehicleDto> getVehiclesNotRemoved() {
        List<HistoricEntity> historiales = historicRepository.findAllByEndhourIsNotNullAndEnddateIsNotNullAndClientdateIsNullAndClienthourIsNull();
        List<VehicleDto> vehicles = new ArrayList<>();
        for (HistoricEntity h : historiales) {
            Vehicle v = restTemplate.getForObject("http://ms-vehicles/vehicles/"+h.getPatent(), Vehicle.class);
            VehicleDto vdto = new VehicleDto(v.getPatent(), markRepository.findById(v.getMark()).get().getMarkName(), v.getModel(), v.getType(), v.getYear(), v.getTypemotor(), v.getNumberseats(), v.getKilometers());
            vehicles.add(vdto);
        }
        return vehicles;
    }

    public Vehicle removeVehicle(String patent) {
        LocalDateTime fechaHora = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");
        String fechaActual = fechaHora.format(formatoFecha);
        String horaActual = fechaHora.format(formatoHora);

        HistoricEntity historial = historicRepository.findByPatent(patent);
        historial.setClientdate(fechaActual);
        historial.setClienthour(horaActual);
        historicRepository.save(historial);
        return restTemplate.getForObject("http://ms-vehicles/vehicles/"+historial.getPatent(), Vehicle.class);
    }

}
