package pep_2_tingeso.msrepairs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.MarkEntity;

@Repository
public interface MarksRepository extends JpaRepository<MarkEntity, Long> {

    Long findByMarkName(String markName);

}