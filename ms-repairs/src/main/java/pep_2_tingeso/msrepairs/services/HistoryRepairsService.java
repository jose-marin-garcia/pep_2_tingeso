package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;
import pep_2_tingeso.msrepairs.repositories.HistoryRepairsRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryRepairsService {

    @Autowired
    HistoryRepairsRepository historyRepairsRepository;

    @Autowired
    RestTemplate restTemplate;

    public List<String> getRepairsOfHistoric(Long idHistoric) {
        List<String> nombreReparaciones = new ArrayList<>();
        List<HistoryRepairsEntity> reparaciones = historyRepairsRepository.findByIdHistorial(idHistoric);
        for (HistoryRepairsEntity reparacion : reparaciones) {
            nombreReparaciones.add(restTemplate.getForObject("http://ms-prices/prices/"+reparacion.getIdReparacion(), String.class));
        }
        return nombreReparaciones;
    }

    public List<HistoryRepairsEntity> getHistorRepairByIdHistoric(Long idRepair) {
        return historyRepairsRepository.findByIdHistorial(idRepair);
    }

    public List<HistoryRepairsEntity> getHistoryRepairByIdReparacion(Long idReparacion) {
        return historyRepairsRepository.findByIdReparacion(idReparacion);
    }
}
