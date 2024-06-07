package pep_2_tingeso.msprices.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreciosDTO {
    private String reparacion;
    private int precioGasolina;
    private int precioDiesel;
    private int precioHibrido;
    private int precioElectrico;
}
