package pep_2_tingeso.msrepairs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCostDetails {
    private String patent; // Patente
    private String mark;
    private String model;
    private String type;
    private String typemotor;
    private int year;
    private int sumaReparaciones;
    private int recargos;
    private int descuentos;
    private int iva;
    private int mount;
    private String admissiondate; //ddmmyyyy
    private String admissionhour; //hhmm
    private String enddate;
    private String endhour;
    private String clientdate;
    private String clienthour;
    private List<String> reparaciones;
}
