package pep_2_tingeso.msrepairs.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="bonds")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BondEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long idMark; //A qué marca pertenece
    private int amount;
}