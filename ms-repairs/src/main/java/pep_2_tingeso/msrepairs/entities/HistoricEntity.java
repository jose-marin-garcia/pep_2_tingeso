package pep_2_tingeso.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table (name = "historic")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HistoricEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

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