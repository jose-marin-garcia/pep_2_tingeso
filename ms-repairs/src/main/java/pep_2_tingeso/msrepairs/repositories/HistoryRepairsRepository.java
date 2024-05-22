package pep_2_tingeso.msrepairs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;

@Repository
public interface HistoryRepairsRepository extends JpaRepository<HistoryRepairsEntity, Long> {
}
