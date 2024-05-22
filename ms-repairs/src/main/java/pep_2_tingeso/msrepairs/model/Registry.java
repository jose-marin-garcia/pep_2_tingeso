package pep_2_tingeso.msrepairs.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Registry {
    private Vehicle vehicle;
    private List<Long> reparations;
    private Long idBond;
}
