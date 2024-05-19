package pep_2_tingeso.msprices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msprices.entities.TypeRepairsEntity;
import pep_2_tingeso.msprices.repositories.TypeRepairsRepository;

import java.util.List;
@Service
public class TypeRepairsService {

    @Autowired
    TypeRepairsRepository typeRepairsRepository;

    public List<TypeRepairsEntity> listTypeRepairs() { return typeRepairsRepository.findAll(); }
    public TypeRepairsEntity getTypeRepairByID(Long id) { return typeRepairsRepository.findById(id).get(); }

    public TypeRepairsEntity saveTypeRepair(TypeRepairsEntity typeRepair) { return typeRepairsRepository.save(typeRepair); }

    public List<TypeRepairsEntity> saveTypesRepair(List<TypeRepairsEntity> typeRepair) { return typeRepairsRepository.saveAll(typeRepair);  }

}
