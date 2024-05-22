package pep_2_tingeso.msrepairs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {
    private String motortype;
    private Long idtyperepair;
    private int price;
}
