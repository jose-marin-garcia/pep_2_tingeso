package pep_2_tingeso.msreports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msreports.dtos.Report1;
import pep_2_tingeso.msreports.dtos.Report2;
import pep_2_tingeso.msreports.dtos.VehicleCostDetails;
import pep_2_tingeso.msreports.services.ReportsService;

import java.util.List;

@RestController
@RequestMapping("/reports")
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @GetMapping("/costos-vehiculos")
    public ResponseEntity<List<VehicleCostDetails>> getCostosVehiculos() {
        return ResponseEntity.ok(reportsService.getCostosVehiculos());
    }

    @GetMapping("/report1")
    public ResponseEntity<List<Report1>> getReporte1(
            @RequestParam String mes,
            @RequestParam String anio) {
        return ResponseEntity.ok(reportsService.getReporte1(mes, anio));
    }

    @GetMapping("/report2")
    public ResponseEntity<List<Report2>> getReporte2(
            @RequestParam int mes,
            @RequestParam int anio){
        return ResponseEntity.ok(reportsService.getReport2(mes, anio));
    }

}
