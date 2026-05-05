package modelo;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "mesas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numero;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private boolean terraza;

    @ManyToOne
    @JoinColumn(name = "restaurante_id", nullable = false)
    @ToString.Exclude
    private Restaurante restaurante;

    @OneToMany(mappedBy = "mesa",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Reserva> reservas;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id)
                .append(" | Mesa ").append(numero)
                .append(" | Capacidad: ").append(capacidad)
                .append(" | Terraza: ").append(terraza).append(" | ")
                .append(restaurante.getNombre());
        return sb.toString();
    }



}
