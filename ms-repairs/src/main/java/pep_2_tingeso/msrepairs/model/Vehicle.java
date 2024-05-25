package pep_2_tingeso.msrepairs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    private String patent;
    private Long mark;
    private String model;
    private String type;
    private int year;
    private String typemotor;
    private int numberseats;
    private int kilometers;
}
