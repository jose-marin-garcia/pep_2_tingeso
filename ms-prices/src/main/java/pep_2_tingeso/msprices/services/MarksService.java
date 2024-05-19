package pep_2_tingeso.msprices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msprices.entities.MarksEntity;
import pep_2_tingeso.msprices.entities.PricesEntity;
import pep_2_tingeso.msprices.repositories.MarksRepository;
import pep_2_tingeso.msprices.repositories.PricesRepository;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    MarksRepository marksRepository;

    public MarksEntity saveMark(MarksEntity markEntity) { return marksRepository.save(markEntity); }

    public List<MarksEntity> saveMarks(List<MarksEntity> marksEntity) { return marksRepository.saveAll(marksEntity);  }

}
