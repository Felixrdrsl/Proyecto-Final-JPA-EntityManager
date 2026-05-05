package modelo;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "restaurantes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Restaurante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String ciudad;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private String telefono;

    @OneToMany(mappedBy = "restaurante",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Mesa> mesas;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append(" | ").append(nombre)
                .append(" | ").append(ciudad)
                .append(" | ").append(direccion)
                .append(" | ").append(telefono);
        return sb.toString();
    }


}
