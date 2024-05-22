package pep_2_tingeso.msrepairs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCostDetails {
    private String patent; // Patente
    private List<String> reparaciones;
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
}
