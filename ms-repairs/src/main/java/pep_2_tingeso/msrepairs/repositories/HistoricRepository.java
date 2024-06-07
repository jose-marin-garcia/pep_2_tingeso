package pep_2_tingeso.msrepairs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;

import java.util.List;

@Repository
public interface HistoricRepository extends JpaRepository<HistoricEntity, Long> {
    @Query(value = "SELECT * FROM historic WHERE MONTH(STR_TO_DATE(enddate, '%d/%m/%Y')) = ?1 AND YEAR(STR_TO_DATE(enddate, '%d/%m/%Y')) = ?2", nativeQuery = true)
    List<HistoricEntity> findByMonthAndYear(String month, String year);

    HistoricEntity findByPatent(String patent);

    List<HistoricEntity> findAllByEndhourIsNullAndEnddateIsNull();

    List<HistoricEntity> findAllByEndhourIsNotNullAndEnddateIsNotNullAndClientdateIsNullAndClienthourIsNull();


}
