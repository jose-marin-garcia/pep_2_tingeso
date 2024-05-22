package pep_2_tingeso.msrepairs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msrepairs.entities.BondEntity;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.entities.MarkEntity;
import pep_2_tingeso.msrepairs.model.Registry;
import pep_2_tingeso.msrepairs.model.Vehicle;
import pep_2_tingeso.msrepairs.services.BondsService;
import pep_2_tingeso.msrepairs.services.MarksService;
import pep_2_tingeso.msrepairs.services.RegisterService;

import java.util.List;

@RestController
@RequestMapping("/repairs")
@CrossOrigin("*")
public class RepairsController {

    @Autowired
    MarksService marksService;

    @Autowired
    BondsService bondsService;

    @Autowired
    RegisterService registerService;

/*    __  __          _
     |  \/  |__ _ _ _| |__ ___
     | |\/| / _` | '_| / /(_-<
     |_|  |_\__,_|_| |_\_\/__/*/

    @GetMapping("/")
    public ResponseEntity<List<MarkEntity>> listTypeRepairs() {
        List<MarkEntity> marks = marksService.getMarks();
        return ResponseEntity.ok(marks);
    }

    @PostMapping("/mark/")
    public ResponseEntity<MarkEntity> saveMark(@RequestBody MarkEntity mark) {
        MarkEntity markNew = marksService.saveMark(mark);
        return ResponseEntity.ok(markNew);
    }

    @PostMapping("/mark/list/")
    public ResponseEntity<List<MarkEntity>> saveMarks(@RequestBody List<MarkEntity> marks) {
        List<MarkEntity> marksNew = marksService.saveMarks(marks);
        return ResponseEntity.ok(marksNew);
    }

/*    ___              _
     | _ ) ___ _ _  __| |___
     | _ \/ _ \ ' \/ _` (_-<
     |___/\___/_||_\__,_/__/*/

    @GetMapping("/bonds/{idMark}")
    public ResponseEntity<BondEntity> getBond(@PathVariable Long idMark){
        BondEntity discount = bondsService.getBond(idMark);
        if (discount != null) {
            return ResponseEntity.ok(discount);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/bonds/{idMark}/{amount}")
    public ResponseEntity<BondEntity> addBond(@PathVariable Long idMark, @PathVariable int amount){
        BondEntity discount = bondsService.addBond(idMark, amount);
        return ResponseEntity.ok(discount);
    }

/*    ___          _    _
     | _ \___ __ _(_)__| |_ ___ _ _
     |   / -_) _` | (_-<  _/ -_) '_|
     |_|_\___\__, |_/__/\__\___|_|
             |___/*/

    @PostMapping("/")
    public ResponseEntity<HistoricEntity> addRegister(@RequestBody Registry registo) {
        System.out.println(registo);
        HistoricEntity nuevoRegistro = registerService.saveRegistry(registo.getVehicle(), registo.getReparations(), registo.getIdBond());
        return ResponseEntity.ok(nuevoRegistro);
    }


}
