package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msrepairs.entities.MarkEntity;
import pep_2_tingeso.msrepairs.repositories.MarksRepository;

import java.util.List;

@Service
public class MarksService {

    @Autowired
    MarksRepository marksRepository;

    public List<MarkEntity> getMarks() { return marksRepository.findAll(); }

    public MarkEntity saveMark(String markEntity) { return marksRepository.save(new MarkEntity(null, markEntity)); }

    public List<MarkEntity> saveMarks(List<MarkEntity> marksEntity) { return marksRepository.saveAll(marksEntity);  }

    public String getMarkName(Long idMark) { return marksRepository.findById(idMark).get().getMarkName(); }
}