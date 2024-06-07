package pep_2_tingeso.msprices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msprices.dto.PreciosDTO;
import pep_2_tingeso.msprices.entities.PricesEntity;
import pep_2_tingeso.msprices.entities.TypeRepairsEntity;
import pep_2_tingeso.msprices.repositories.TypeRepairsRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
public class TypeRepairsService {

    @Autowired
    TypeRepairsRepository typeRepairsRepository;

    @Autowired
    PricesService pricesService;

    public List<TypeRepairsEntity> listTypeRepairs() { return typeRepairsRepository.findAll(); }
    public String getTypeRepairByID(Long id) { return typeRepairsRepository.findById(id).get().getRepairName(); }

    public TypeRepairsEntity saveTypeRepair(TypeRepairsEntity typeRepair) { return typeRepairsRepository.save(typeRepair); }

    public List<TypeRepairsEntity> saveTypesRepair(List<TypeRepairsEntity> typeRepair) { return typeRepairsRepository.saveAll(typeRepair);  }

    public PreciosDTO saveTypeRepairAndPrices(PreciosDTO reparacionPrecios) {
        TypeRepairsEntity typeRepairs = typeRepairsRepository.save(new TypeRepairsEntity(null, reparacionPrecios.getReparacion()));
        pricesService.savePrice(new PricesEntity(null, "Gasolina", typeRepairs.getId(), reparacionPrecios.getPrecioGasolina()));
        pricesService.savePrice(new PricesEntity(null, "Diesel", typeRepairs.getId(), reparacionPrecios.getPrecioDiesel()));
        pricesService.savePrice(new PricesEntity(null, "Híbrido", typeRepairs.getId(), reparacionPrecios.getPrecioHibrido()));
        pricesService.savePrice(new PricesEntity(null, "Eléctrico", typeRepairs.getId(), reparacionPrecios.getPrecioElectrico()));
        return reparacionPrecios;
    }
}
