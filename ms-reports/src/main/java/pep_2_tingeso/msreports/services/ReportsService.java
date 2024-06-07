package pep_2_tingeso.msreports.services;

import org.springframework.http.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msreports.dtos.Report1;
import pep_2_tingeso.msreports.dtos.Report2;
import pep_2_tingeso.msreports.dtos.VehicleCostDetails;
import pep_2_tingeso.msreports.model.Historic;
import pep_2_tingeso.msreports.model.HistoryRepair;
import pep_2_tingeso.msreports.model.TypeRepair;
import pep_2_tingeso.msreports.model.Vehicle;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportsService {

    @Autowired
    RestTemplate restTemplate;

    public List<VehicleCostDetails> getCostosVehiculos() {

        List<VehicleCostDetails> vehicleCostDetails = new ArrayList<>();

        List<Historic> historiales = restTemplate.exchange(
                "http://ms-repairs/repairs/historiales",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Historic>>() {}).getBody();

        for (Historic historial : historiales){

            Vehicle vehiculo = restTemplate.getForObject("http://ms-vehicles/vehicles/"+historial.getPatent(), Vehicle.class);

            VehicleCostDetails vehicleCostDetail = new VehicleCostDetails();

            vehicleCostDetail.setPatent(historial.getPatent());

            vehicleCostDetail.setModel(vehiculo.getModel());
            vehicleCostDetail.setMark(restTemplate.getForObject("http://ms-repairs/repairs/"+vehiculo.getMark(), String.class));
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

            vehicleCostDetail.setReparaciones(restTemplate.getForObject("http://ms-repairs/repairs/typerepair/"+historial.getId(), List.class));

            vehicleCostDetails.add(vehicleCostDetail);
        }
        return vehicleCostDetails;
    }

    public List<Report1> getReporte1(String mes, String anio) {
        List<Report1> reports1 = new ArrayList<>();
        ResponseEntity<List<TypeRepair>> response = restTemplate.exchange("http://ms-prices/prices/", HttpMethod.GET, null, new ParameterizedTypeReference<List<TypeRepair>>() {});
        List<TypeRepair> reparaciones = response.getBody();
        System.out.println(reparaciones);

        ResponseEntity<List<Historic>> response3 = restTemplate.exchange("http://ms-repairs/repairs/historiales/" + mes + "/" + anio, HttpMethod.GET, null, new ParameterizedTypeReference<List<Historic>>() {});
        List<Historic> historiales = response3.getBody();
        System.out.println(historiales);

        assert historiales != null;
        Map<Long, String> historialesMap = Historial2Map(historiales);

        assert reparaciones != null;
        for (TypeRepair reparacion : reparaciones) {
            Report1 report1 = new Report1();
            report1.setRepairName(reparacion.getRepairName());

            ResponseEntity<List<HistoryRepair>> response2 = restTemplate.exchange("http://ms-repairs/repairs/historyrepairbyidreparacion/" + reparacion.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
            List<HistoryRepair> repairs = response2.getBody();
            System.out.println(repairs);

            assert repairs != null;
            List<HistoryRepair> filteredRepairs = repairs.stream()
                    .filter(repair -> historialesMap.containsKey(repair.getIdHistorial()))
                    .toList();

            for (HistoryRepair repair : filteredRepairs) {

                Vehicle vehiculo = restTemplate.getForObject("http://ms-vehicles/vehicles/"+historialesMap.get(repair.getIdHistorial()), Vehicle.class);
                System.out.println(vehiculo);

                String type = vehiculo.getType();
                ResponseEntity<Integer> response4 = restTemplate.exchange("http://ms-prices/prices/price/get/"+repair.getIdReparacion()+"/"+vehiculo.getTypemotor(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
                int cost = response4.getBody();
                System.out.println("reparacion:" + repair.getIdReparacion() + "costo:" + cost);

                switch (type) {
                    case "Sedan":
                        report1.setCountSedans(report1.getCountSedans() + 1);
                        report1.setMountSedans(report1.getMountSedans() + cost);
                            break;
                        case "Hatchback":
                            report1.setCountHatchbacks(report1.getCountHatchbacks() + 1);
                            report1.setMountHatchbacks(report1.getMountHatchbacks() + cost);
                            break;
                        case "SUV":
                            report1.setCountSuvs(report1.getCountSuvs() + 1);
                            report1.setMountSuvs(report1.getMountSuvs() + cost);
                            break;
                        case "Pickup":
                            report1.setCountPickups(report1.getCountPickups() + 1);
                            report1.setMountPickups(report1.getMountPickups() + cost);
                            break;
                        case "Furgoneta":
                            report1.setCountFurgons(report1.getCountFurgons() + 1);
                            report1.setMountFurgons(report1.getMountFurgons() + cost);
                            break;
                    }
                    report1.setMountTotal(report1.getMountTotal() + cost);
                    report1.setCountTotal(report1.getCountTotal() + 1);
                }
            reports1.add(report1);
        }
        System.out.println(reports1);
        return reports1;
    }

    public Map<Long, String> Historial2Map (List<Historic> historiales){
        Map<Long, String> historialesMap = new HashMap<>();
        for (Historic historial : historiales) {
            historialesMap.put(historial.getId(), historial.getPatent());
        }
        return historialesMap;
    }

    public List<Report2> getReport2(int mes, int anio) {
        List<Report2> reports2 = new ArrayList<>();
        ResponseEntity<List<TypeRepair>> response = restTemplate.exchange("http://ms-prices/prices/", HttpMethod.GET, null, new ParameterizedTypeReference<List<TypeRepair>>() {});
        List<TypeRepair> reparaciones = response.getBody();
        System.out.println(reparaciones);

        assert reparaciones != null;
        for (TypeRepair reparacion : reparaciones) {
            Report2 report2 = new Report2();
            report2.setNombreReparacion(reparacion.getRepairName());
            LocalDate inputDate = LocalDate.of(anio, mes, 1);
            report2.setMes1(String.valueOf(inputDate.getMonth()));
            report2.setMes2(String.valueOf(inputDate.minusMonths(1).getMonth()));
            report2.setMes3(String.valueOf(inputDate.minusMonths(2).getMonth()));

            for (int i = 1; i <= 3; i++) {
                ResponseEntity<List<Historic>> response2 = restTemplate.exchange("http://ms-repairs/repairs/historiales/" + inputDate.getMonth().getValue() + "/" + inputDate.getYear(), HttpMethod.GET, null, new ParameterizedTypeReference<List<Historic>>() {});
                System.out.print(inputDate.getMonth().getValue());
                List<Historic> historiales = response2.getBody();

                assert historiales != null;
                for (Historic historial : historiales) {
                    ResponseEntity<List<HistoryRepair>> response3 = restTemplate.exchange("http://ms-repairs/repairs/historyrepairbyidhistoric/" + historial.getId(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
                    List<HistoryRepair> repairs = response3.getBody();

                    assert repairs != null;
                    List<HistoryRepair> filteredRepairs = repairs.stream()
                            .filter(repair -> Objects.equals(repair.getIdReparacion(), reparacion.getId()))
                            .toList();

                    if (!filteredRepairs.isEmpty()) {
                        Vehicle vehiculo = restTemplate.getForObject("http://ms-vehicles/vehicles/" + historial.getPatent(), Vehicle.class);
                        System.out.println(vehiculo);

                        assert vehiculo != null;
                        ResponseEntity<Integer> response4 = restTemplate.exchange("http://ms-prices/prices/price/get/" + reparacion.getId() + "/" + vehiculo.getTypemotor(), HttpMethod.GET, null, new ParameterizedTypeReference<>() {});
                        int cost = response4.getBody();
                        if (i == 1){
                            report2.setMes1mount(report2.getMes1mount() + cost);
                            report2.setMes1count(report2.getMes1count() + 1);
                        } else if (i == 2){
                            report2.setMes2mount(report2.getMes2mount() + cost);
                            report2.setMes2count(report2.getMes2count() + 1);
                        } else {
                            report2.setMes3mount(report2.getMes3mount() + cost);
                            report2.setMes3count(report2.getMes3count() + 1);
                        }
                    }
                }
                inputDate = inputDate.minusMonths(1);
            }
            if(report2.getMes1count() == 0 || report2.getMes2count() == 0 || report2.getMes3count() == 0){
                report2.setVariacion12Count(String.valueOf(Double.NaN));
                report2.setVariacion12Mount(String.valueOf(Double.NaN));
                report2.setVariacion23Count(String.valueOf(Double.NaN));
                report2.setVariacion23Mount(String.valueOf(Double.NaN));
                reports2.add(report2);
            }else{
                report2.setVariacion12Count(((report2.getMes1count() - report2.getMes2count()) / report2.getMes2count()) * 100 + "%");
                report2.setVariacion12Mount(((report2.getMes1mount() - report2.getMes2mount()) / report2.getMes2mount()) * 100 + "%");
                report2.setVariacion23Count(((report2.getMes2count() - report2.getMes3count()) / report2.getMes3count()) * 100 + "%");
                report2.setVariacion23Mount(((report2.getMes2mount() - report2.getMes3mount()) / report2.getMes3mount()) * 100 + "%");
                reports2.add(report2);
            }
        }
        return reports2;
    }
}
