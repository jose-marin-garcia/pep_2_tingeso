package pep_2_tingeso.msreports.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report1 {
    private String repairName;

    private int countSedans;
    private int mountSedans;

    private int countHatchbacks;
    private int mountHatchbacks;

    private int countSuvs;
    private int mountSuvs;

    private int countPickups;
    private int mountPickups;

    private int countFurgons;
    private int mountFurgons;

    private int countTotal;
    private int mountTotal;
}
