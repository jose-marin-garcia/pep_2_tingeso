package pep_2_tingeso.msprices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msprices.entities.MarksEntity;

@Repository
public interface MarksRepository extends JpaRepository<MarksEntity, Long> {

}
