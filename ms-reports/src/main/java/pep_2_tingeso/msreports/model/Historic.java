package pep_2_tingeso.msreports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Historic {
    private String patent; // Patente
    private String admissiondate; //ddmmyyyy
    private String admissionhour; //hhmm
    private int mount;
    private int sumaReparaciones;
    private int descuentos;
    private int recargos;
    private int iva;
    private String enddate;
    private String endhour;
    private String clientdate;
    private String clienthour;
}
