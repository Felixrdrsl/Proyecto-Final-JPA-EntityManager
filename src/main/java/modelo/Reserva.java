package modelo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Reserva {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha_reserva", nullable = false)
    private LocalDate fechaReserva;

    @Column(name = "hora_reserva", nullable = false)
    private LocalTime horaReserva;

    @Column(name = "num_personas", nullable = false)
    private int numPersonas;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", nullable = false)
    private EstadoReserva estado;

    @Column(name = "importe_estimado")
    private double importeEstimado;

    @ManyToOne
    @JoinColumn(name = "mesa_id")
    @ToString.Exclude
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @ToString.Exclude
    private Cliente cliente;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append(" | ")
                .append(fechaReserva).append(" ")
                .append(horaReserva).append(" | ")
                .append(numPersonas).append(" personas").append(" | ")
                .append(estado).append(" | ")
                .append(importeEstimado).append("€").append(" | ")
                .append(cliente.getNombre()).append(" | Mesa ")
                .append(mesa.getNumero());

        return sb.toString();
    }


}
