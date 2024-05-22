package pep_2_tingeso.msvehicles.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="vehicle")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String patent;
    private Long mark;
    private String model;
    private String type;
    private int year;
    private String typemotor;
    private int numberseats;
    private int kilometers;
}
