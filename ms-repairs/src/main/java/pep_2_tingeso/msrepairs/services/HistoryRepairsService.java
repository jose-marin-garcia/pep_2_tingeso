package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msrepairs.dto.VehicleCostDetails;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;
import pep_2_tingeso.msrepairs.model.Vehicle;
import pep_2_tingeso.msrepairs.repositories.HistoricRepository;
import pep_2_tingeso.msrepairs.repositories.HistoryRepairsRepository;
import pep_2_tingeso.msrepairs.repositories.MarksRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryRepairsService {

    @Autowired
    HistoryRepairsRepository historyRepairsRepository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    HistoricRepository historicRepository;

    @Autowired
    MarksRepository markRepository;

    public List<String> getRepairsOfHistoric(Long idHistoric) {
        List<String> nombreReparaciones = new ArrayList<>();
        List<HistoryRepairsEntity> reparaciones = historyRepairsRepository.findByIdHistorial(idHistoric);
        for (HistoryRepairsEntity reparacion : reparaciones) {
            nombreReparaciones.add(restTemplate.getForObject("http://ms-prices/prices/"+reparacion.getIdReparacion(), String.class));
        }
        return nombreReparaciones;
    }

    public List<HistoryRepairsEntity> getHistorRepairByIdHistoric(Long idRepair) {
        return historyRepairsRepository.findByIdHistorial(idRepair);
    }

    public List<HistoryRepairsEntity> getHistoryRepairByIdReparacion(Long idReparacion) {
        return historyRepairsRepository.findByIdReparacion(idReparacion);
    }

    public List<VehicleCostDetails> getCostosVehiculos() {

        List<VehicleCostDetails> vehicleCostDetails = new ArrayList<>();

        List<HistoricEntity> historiales = historicRepository.findAll();

        for (HistoricEntity historial : historiales){

            String patente = historial.getPatent();

            Vehicle vehiculo = restTemplate.getForObject("http://ms-vehicles/vehicles/"+patente, Vehicle.class);
            System.out.print(vehiculo);

            VehicleCostDetails vehicleCostDetail = new VehicleCostDetails();

            vehicleCostDetail.setPatent(patente);
            assert vehiculo != null;
            vehicleCostDetail.setModel(vehiculo.getModel());
            vehicleCostDetail.setMark((markRepository.findById(vehiculo.getMark())).get().getMarkName());
            vehicleCostDetail.setType(vehiculo.getType());
            vehicleCostDetail.setTypemotor(vehiculo.getTypemotor());
            vehicleCostDetail.setYear(vehiculo.getYear());

            vehicleCostDetail.setSumaReparaciones(historial.getSumaReparaciones());
            vehicleCostDetail.setRecargos(historial.getRecargos());
            vehicleCostDetail.setDescuentos(historial.getDescuentos());
            vehicleCostDetail.setIva(historial.getIva());
            vehicleCostDetail.setMount(historial.getMount());
            vehicleCostDetail.setAdmissiondate(historial.getAdmissiondate());
            vehicleCostDetail.setAdmissionhour(historial.getAdmissionhour());
            vehicleCostDetail.setEnddate(historial.getEnddate());
            vehicleCostDetail.setEndhour(historial.getEndhour());
            vehicleCostDetail.setClientdate(historial.getClientdate());
            vehicleCostDetail.setClienthour(historial.getClienthour());

            vehicleCostDetail.setReparaciones(getRepairsOfHistoric(historial.getId()));
            vehicleCostDetails.add(vehicleCostDetail);
            System.out.print(vehicleCostDetail);
        }
        return vehicleCostDetails;
    }
}
