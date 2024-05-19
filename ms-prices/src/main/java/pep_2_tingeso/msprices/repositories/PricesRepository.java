package pep_2_tingeso.msprices.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pep_2_tingeso.msprices.entities.PricesEntity;

public interface PricesRepository extends JpaRepository<PricesEntity, Long> {

    // Método utilizando convención de nombres
    @Query("SELECT p.price FROM PricesEntity p WHERE p.idtyperepair = :idTypeRepair AND p.motortype = :motorType")
    int findPriceByIdtyperepairAndMotortype(Long idTypeRepair, String motorType);

}
