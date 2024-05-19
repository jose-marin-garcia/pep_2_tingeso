package pep_2_tingeso.msprices.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msprices.entities.MarksEntity;
import pep_2_tingeso.msprices.entities.PricesEntity;
import pep_2_tingeso.msprices.entities.TypeRepairsEntity;
import pep_2_tingeso.msprices.services.MarksService;
import pep_2_tingeso.msprices.services.PricesService;
import pep_2_tingeso.msprices.services.TypeRepairsService;

import java.util.List;

@RestController
@RequestMapping("/prices")
@CrossOrigin("*")
public class PricesController {

    @Autowired
    TypeRepairsService typeRepairsService;

    @Autowired
    PricesService pricesService;

    @Autowired
    MarksService marksService;

    /*
      _____                 ___                _
     |_   _|  _ _ __  ___  | _ \___ _ __  __ _(_)_ _ ___
       | || || | '_ \/ -_) |   / -_) '_ \/ _` | | '_(_-<
       |_| \_, | .__/\___| |_|_\___| .__/\__,_|_|_| /__/
           |__/|_|                 |_|
    */

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

    /*
      __  __          _
     |  \/  |__ _ _ _| |__ ___
     | |\/| / _` | '_| / /(_-<
     |_|  |_\__,_|_| |_\_\/__/
    */

    @PostMapping("/mark/")
    public ResponseEntity<MarksEntity> saveMark(@RequestBody MarksEntity mark) {
        MarksEntity markNew = marksService.saveMark(mark);
        return ResponseEntity.ok(markNew);
    }

    @PostMapping("/mark/list/")
    public ResponseEntity<List<MarksEntity>> saveMarks(@RequestBody List<MarksEntity> marks) {
        List<MarksEntity> marksNew = marksService.saveMarks(marks);
        return ResponseEntity.ok(marksNew);
    }

}
