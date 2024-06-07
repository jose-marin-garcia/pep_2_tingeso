package pep_2_tingeso.msreports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoryRepair {
    private Long idHistorial; //A qué historial pertenece
    private Long idReparacion; //ID Reparaciones
}
