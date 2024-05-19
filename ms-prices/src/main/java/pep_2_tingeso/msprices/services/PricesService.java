package pep_2_tingeso.msprices.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pep_2_tingeso.msprices.entities.PricesEntity;
import pep_2_tingeso.msprices.entities.TypeRepairsEntity;
import pep_2_tingeso.msprices.repositories.PricesRepository;

import java.util.List;

@Service
public class PricesService {
    @Autowired
    PricesRepository pricesRepository;

    public PricesEntity savePrice(PricesEntity priceEntity) { return pricesRepository.save(priceEntity); }

    public List<PricesEntity> savePrices(List<PricesEntity> pricesEntity) { return pricesRepository.saveAll(pricesEntity);  }
}
