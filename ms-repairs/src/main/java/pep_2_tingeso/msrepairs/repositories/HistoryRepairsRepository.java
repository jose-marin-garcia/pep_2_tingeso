package pep_2_tingeso.msrepairs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;

import java.util.List;

@Repository
public interface HistoryRepairsRepository extends JpaRepository<HistoryRepairsEntity, Long> {
    List<HistoryRepairsEntity> findByIdHistorial(Long idHistoric);

    List<HistoryRepairsEntity> findByIdReparacion(Long idReparacion);
}
