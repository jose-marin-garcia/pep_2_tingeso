package pep_2_tingeso.msrepairs.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDto {
    private String patent;
    private String mark;
    private String model;
    private String type;
    private int year;
    private String typemotor;
    private int numberseats;
    private int kilometers;
}
