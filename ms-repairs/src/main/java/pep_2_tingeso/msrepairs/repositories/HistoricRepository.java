package pep_2_tingeso.msrepairs.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;

@Repository
public interface HistoricRepository extends JpaRepository<HistoricEntity, Long> {
}
