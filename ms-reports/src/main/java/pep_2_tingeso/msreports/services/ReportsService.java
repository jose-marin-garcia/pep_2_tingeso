package pep_2_tingeso.msreports.services;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msreports.dtos.VehicleCostDetails;
import pep_2_tingeso.msreports.model.Historic;
import pep_2_tingeso.msreports.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportsService {

    @Autowired
    RestTemplate restTemplate;

    public List<VehicleCostDetails> getCostosVehiculos() {

        List<VehicleCostDetails> vehicleCostDetails = new ArrayList<>();

        List<Historic> historiales = restTemplate.exchange(
                "http://localhost:8080/repairs/historiales",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Historic>>() {}).getBody();

        for (Historic historial : historiales){

            Vehicle vehiculo = restTemplate.getForObject("http://localhost:8080/vehicles/"+historial.getPatent(), Vehicle.class);

            VehicleCostDetails vehicleCostDetail = new VehicleCostDetails();

            vehicleCostDetail.setPatent(historial.getPatent());

            vehicleCostDetail.setModel(vehiculo.getModel());
            vehicleCostDetail.setMark(restTemplate.getForObject("http://localhost:8080/repairs/"+vehiculo.getMark(), String.class));
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

            vehicleCostDetails.add(vehicleCostDetail);
        }
        return vehicleCostDetails;
    }
}
