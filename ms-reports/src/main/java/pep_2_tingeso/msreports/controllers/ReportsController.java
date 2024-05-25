package pep_2_tingeso.msreports.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pep_2_tingeso.msreports.dtos.VehicleCostDetails;
import pep_2_tingeso.msreports.services.ReportsService;

import java.util.List;

@RestController
@RequestMapping("/reports")
//@CrossOrigin("*")
public class ReportsController {

    @Autowired
    ReportsService reportsService;

    @GetMapping("/costos-vehiculos")
    public ResponseEntity<List<VehicleCostDetails>> getCostosVehiculos() {
        return ResponseEntity.ok(reportsService.getCostosVehiculos());
    }

}
