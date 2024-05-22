package pep_2_tingeso.msrepairs.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msrepairs.entities.BondEntity;
import pep_2_tingeso.msrepairs.repositories.BondRepository;

@Service
public class BondsService {

    @Autowired
    BondRepository bondRepository;

    public BondEntity getBond(Long id_mark) {
        if(id_mark == null)
            return null;
        return bondRepository.findFirstByIdMark(id_mark);
    }

    public BondEntity addBond(Long idMark, int amount) {
        return bondRepository.save(new BondEntity(null, idMark, amount));
    }

}
