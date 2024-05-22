package pep_2_tingeso.msrepairs.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pep_2_tingeso.msrepairs.entities.BondEntity;

@Repository
public interface BondRepository extends JpaRepository<BondEntity, Long> {

    BondEntity findFirstByIdMark(Long id);
}
