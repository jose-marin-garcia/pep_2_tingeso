package pep_2_tingeso.msreports.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report2 {
    private String nombreReparacion;
    private String mes1; //Mes de entrada
    private String mes2;
    private String mes3;
    private int mes1count;
    private int mes1mount;
    private String variacion12Count;
    private String variacion12Mount;
    private int mes2count;
    private int mes2mount;
    private String variacion23Count;
    private String variacion23Mount;
    private int mes3count;
    private int mes3mount;
}
