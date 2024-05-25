package pep_2_tingeso.msvehicles.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msvehicles.entities.VehicleEntity;
import pep_2_tingeso.msvehicles.services.VehicleService;

@RestController
@RequestMapping("/vehicles")
//@CrossOrigin("*")
public class VehiclesController {

    @Autowired
    VehicleService vehicleService;

   /*__   __   _    _    _
     \ \ / /__| |_ (_)__| |___
      \ V / -_) ' \| / _| / -_)
       \_/\___|_||_|_\__|_\___|*/

    @GetMapping("/{patent}")
    public VehicleEntity getVehicle(@PathVariable String patent) {
        VehicleEntity vehicle = vehicleService.getVehicle(patent);
        return vehicle;
    }

    @PostMapping("/vehicle/")
    public VehicleEntity saveVehicle(@RequestBody VehicleEntity vehicle) {
        VehicleEntity vehicleNew = vehicleService.saveVehicle(vehicle);
        return vehicleNew;
    }

}
