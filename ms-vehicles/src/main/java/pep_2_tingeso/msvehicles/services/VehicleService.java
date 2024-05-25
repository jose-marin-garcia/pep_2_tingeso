package pep_2_tingeso.msvehicles.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msvehicles.entities.VehicleEntity;
import pep_2_tingeso.msvehicles.repositories.VehicleRepository;

@Service
public class VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    public VehicleEntity saveVehicle(VehicleEntity vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public VehicleEntity getVehicle(String patent) { return vehicleRepository.findByPatent(patent);
    }
}
