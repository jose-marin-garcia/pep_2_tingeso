package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.repositories.HistoricRepository;

import java.util.List;

@Service
public class HistoricsService {

    @Autowired
    HistoricRepository historicRepository;

    public List<HistoricEntity> getHistorics() {
        return historicRepository.findAll();
    }

    public List<HistoricEntity> getHistoricsByMonthAndYear(String mes, String anio) {
        List<HistoricEntity> historics = historicRepository.findByMonthAndYear(mes, anio);
        System.out.println(historics);
        return historics;
    }
}
