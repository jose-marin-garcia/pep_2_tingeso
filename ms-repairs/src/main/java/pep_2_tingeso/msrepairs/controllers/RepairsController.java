package pep_2_tingeso.msrepairs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pep_2_tingeso.msrepairs.dto.VehicleCostDetails;
import pep_2_tingeso.msrepairs.dto.VehicleDto;
import pep_2_tingeso.msrepairs.entities.BondEntity;
import pep_2_tingeso.msrepairs.entities.HistoricEntity;
import pep_2_tingeso.msrepairs.entities.HistoryRepairsEntity;
import pep_2_tingeso.msrepairs.entities.MarkEntity;
import pep_2_tingeso.msrepairs.model.Registry;
import pep_2_tingeso.msrepairs.model.Vehicle;
import pep_2_tingeso.msrepairs.services.*;

import java.util.List;

@RestController
@RequestMapping("/repairs")
//@CrossOrigin("*")
public class RepairsController {

    @Autowired
    MarksService marksService;

    @Autowired
    BondsService bondsService;

    @Autowired
    RegisterService registerService;

    @Autowired
    HistoricsService historicService;

    @Autowired
    HistoryRepairsService historyRepairService;

    @Autowired
    VehiclesService vehicleService;

/*    __  __          _
     |  \/  |__ _ _ _| |__ ___
     | |\/| / _` | '_| / /(_-<
     |_|  |_\__,_|_| |_\_\/__/*/

    @GetMapping("/")
    public ResponseEntity<List<MarkEntity>> listTypeRepairs() {
        List<MarkEntity> marks = marksService.getMarks();
        return ResponseEntity.ok(marks);
    }

    @GetMapping("/{idMark}")
    public ResponseEntity<String> getMark(@PathVariable Long idMark){
        String mark = marksService.getMarkName(idMark);
        return ResponseEntity.ok(mark);
    }

    @PostMapping("/mark/{mark}")
    public ResponseEntity<MarkEntity> saveMark(@PathVariable String mark) {
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
        System.out.println(registo.getVehicle().getMark());
        HistoricEntity nuevoRegistro = registerService.saveRegistry(registo.getVehicle(), registo.getReparations(), registo.getIdBond());
        return ResponseEntity.ok(nuevoRegistro);
    }


  /*_  _ _    _           _
   | || (_)__| |_ ___ _ _(_)__
   | __ | (_-<  _/ _ \ '_| / _|
   |_||_|_/__/\__\___/_| |_\__|*/

    @GetMapping("/historiales")
    public ResponseEntity<List<HistoricEntity>> getHistorics() {
        return ResponseEntity.ok(historicService.getHistorics());
    }

    @GetMapping("/historiales/{mes}/{anio}")
    public ResponseEntity<List<HistoricEntity>> getHistoricsByMonthAndYear(@PathVariable String mes, @PathVariable String anio) {
        return ResponseEntity.ok(historicService.getHistoricsByMonthAndYear(mes, anio));
    }

/*    _____               ___                _
     |_   _|  _ _ __  ___| _ \___ _ __  __ _(_)_ _ ___
       | || || | '_ \/ -_)   / -_) '_ \/ _` | | '_(_-<
       |_| \_, | .__/\___|_|_\___| .__/\__,_|_|_| /__/
           |__/|_|               |_|                  */

    @GetMapping("/typerepair/{idHistoric}")
    public ResponseEntity<List<String>> getRepairsOfHistoric(@PathVariable Long idHistoric){
        List<String> historic = historyRepairService.getRepairsOfHistoric(idHistoric);
        return ResponseEntity.ok(historic);
    }

/*
 *     __   __   _    _    _
 *     \ \ / /__| |_ (_)__| |___
 *      \ V / -_) ' \| / _| / -_)
 *       \_/\___|_||_|_\__|_\___|
 *
 */

    @GetMapping("/vehicles-not-finished/")
    public ResponseEntity<List<VehicleDto>> getVehiclesNotFinished(){
        return ResponseEntity.ok(vehicleService.getVehiclesNotFinished());
    }

    @PutMapping("/finish/{patent}")
    public ResponseEntity<Vehicle> finishVehicle(@PathVariable String patent){
        return ResponseEntity.ok(vehicleService.finishVehicle(patent));
    }

    @GetMapping("/vehicles-not-removed/")
    public ResponseEntity<List<VehicleDto>> getVehiclesNotRemoved(){
        return ResponseEntity.ok(vehicleService.getVehiclesNotRemoved());
    }

    @PutMapping("/remove/{patent}")
    public ResponseEntity<Vehicle> removeVehicle(@PathVariable String patent){
        return ResponseEntity.ok(vehicleService.removeVehicle(patent));
    }


/*
 *      _  _ _    _                ___                _
 *     | || (_)__| |_ ___ _ _ _  _| _ \___ _ __  __ _(_)_ _
 *     | __ | (_-<  _/ _ \ '_| || |   / -_) '_ \/ _` | | '_|
 *     |_||_|_/__/\__\___/_|  \_, |_|_\___| .__/\__,_|_|_|
 *                            |__/        |_|
 */

    @GetMapping("/historyrepairbyidhistoric/{idHistoric}")
    public ResponseEntity<List<HistoryRepairsEntity>> getHistoricByRepair(@PathVariable Long idHistoric){
        return ResponseEntity.ok(historyRepairService.getHistorRepairByIdHistoric(idHistoric));
    }

    @GetMapping("/historyrepairbyidreparacion/{idReparacion}")
    public ResponseEntity<List<HistoryRepairsEntity>> getHistoryRepairByIdReparacion(@PathVariable Long idReparacion){
        return ResponseEntity.ok(historyRepairService.getHistoryRepairByIdReparacion(idReparacion));
    }

    @GetMapping("/costos-vehiculos")
    public ResponseEntity<List<VehicleCostDetails>> getCostosVehiculos() {
        return ResponseEntity.ok(historyRepairService.getCostosVehiculos());
    }
}
