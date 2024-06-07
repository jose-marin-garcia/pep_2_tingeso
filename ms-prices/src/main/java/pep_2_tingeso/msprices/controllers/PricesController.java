package pep_2_tingeso.msprices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msprices.dto.PreciosDTO;
import pep_2_tingeso.msprices.entities.PricesEntity;
import pep_2_tingeso.msprices.entities.TypeRepairsEntity;
import pep_2_tingeso.msprices.services.PricesService;
import pep_2_tingeso.msprices.services.TypeRepairsService;

import java.util.List;

@RestController
@RequestMapping("/prices")
//@CrossOrigin("*")
public class PricesController {

    @Autowired
    TypeRepairsService typeRepairsService;

    @Autowired
    PricesService pricesService;

    /*
      _____                 ___                _
     |_   _|  _ _ __  ___  | _ \___ _ __  __ _(_)_ _ ___
       | || || | '_ \/ -_) |   / -_) '_ \/ _` | | '_(_-<
       |_| \_, | .__/\___| |_|_\___| .__/\__,_|_|_| /__/
           |__/|_|                 |_|
    */

    @GetMapping("/")
    public ResponseEntity<List<TypeRepairsEntity>> listTypeRepairs() {
        List<TypeRepairsEntity> listTypeRepairs = typeRepairsService.listTypeRepairs();
        return ResponseEntity.ok(listTypeRepairs);
    }

    @GetMapping("/{idTypeRepair}")
    public ResponseEntity<String> getTypeRepair(@PathVariable Long idTypeRepair) {
        String typeRepair = typeRepairsService.getTypeRepairByID(idTypeRepair);
        return ResponseEntity.ok(typeRepair);
    }

    @PostMapping("/typerepair/")
    public ResponseEntity<TypeRepairsEntity> saveTypeRepair(@RequestBody TypeRepairsEntity typeRepair) {
        TypeRepairsEntity typeRepairNew = typeRepairsService.saveTypeRepair(typeRepair);
        return ResponseEntity.ok(typeRepairNew);
    }

    @PostMapping("/typerepair/list/")
    public ResponseEntity<List<TypeRepairsEntity>> saveTypeRepairs(@RequestBody List<TypeRepairsEntity> typeRepair) {
        List<TypeRepairsEntity> typesRepairNew = typeRepairsService.saveTypesRepair(typeRepair);
        return ResponseEntity.ok(typesRepairNew);
    }

    /*
      ___     _
     | _ \_ _(_)__ ___ ___
     |  _/ '_| / _/ -_|_-<
     |_| |_| |_\__\___/__/
    */

    @GetMapping("/price/get/{idTypeRepair}/{motorType}")
    public ResponseEntity<Integer> getPrice(@PathVariable Long idTypeRepair, @PathVariable String motorType) {
        int price = pricesService.getPrice(idTypeRepair, motorType);
        return ResponseEntity.ok(price);
    }

    @PostMapping("/price/")
    public ResponseEntity<PricesEntity> savePrice(@RequestBody PricesEntity priceEntity) {
        PricesEntity priceNew = pricesService.savePrice(priceEntity);
        return ResponseEntity.ok(priceNew);
    }

    @PostMapping("/price/list/")
    public ResponseEntity<List<PricesEntity>> savePrices(@RequestBody List<PricesEntity> pricesEntity) {
        List<PricesEntity> pricesNew = pricesService.savePrices(pricesEntity);
        return ResponseEntity.ok(pricesNew);
    }

    @PostMapping("/reparacion-precios")
    public ResponseEntity<PreciosDTO> saveTypeRepairAndPrices(@RequestBody PreciosDTO reparacionPrecios) {
        PreciosDTO reparacionprecios = typeRepairsService.saveTypeRepairAndPrices(reparacionPrecios);
        return ResponseEntity.ok(reparacionprecios);
    }

}
