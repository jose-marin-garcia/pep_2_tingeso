package pep_2_tingeso.msreports.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleCostDetails {
    private String patent;
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
    private String admissiondate;
    private String admissionhour;
    private String enddate;
    private String endhour;
    private String clientdate;
    private String clienthour;
    private List<String> reparaciones;
}
