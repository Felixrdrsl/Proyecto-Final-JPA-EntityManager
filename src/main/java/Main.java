import jakarta.persistence.EntityManager;
import modelo.*;
import repositorio.ClienteRepository;
import repositorio.MesaRepository;
import repositorio.ReservaRepository;
import repositorio.RestauranteRepository;
import servicio.ReservaServicio;


import javax.swing.text.html.parser.Entity;
import java.time.LocalDate;
import java.time.LocalTime;

public class Main {
    static void main() {


        RestauranteRepository restauranteRepository = new RestauranteRepository();
        MesaRepository mesaRepository = new MesaRepository();
        ClienteRepository clienteRepository = new ClienteRepository();
        ReservaRepository reservaRepository = new ReservaRepository();

        // ======================
        // RESTAURANTES (2)
        // ======================

        Restaurante r1 = new Restaurante(
                null,
                "La Esquina",
                "Sevilla",
                "C/ Sierpes 12",
                "954123456",
                null
        );

        Restaurante r2 = new Restaurante(
                null,
                "Brasa Norte",
                "Madrid",
                "C/ Gran Vía 45",
                "912345678",
                null
        );

        restauranteRepository.save(r1);
        restauranteRepository.save(r2);

        // ======================
        // MESAS (12)
        // ======================

        Mesa m1 = new Mesa(null, 1, 4, true, r1, null);
        Mesa m2 = new Mesa(null, 2, 2, false, r1, null);
        Mesa m3 = new Mesa(null, 3, 4, false, r1, null);
        Mesa m4 = new Mesa(null, 4, 6, true, r1, null);
        Mesa m5 = new Mesa(null, 5, 2, false, r1, null);
        Mesa m6 = new Mesa(null, 6, 8, true, r1, null);

        Mesa m7 = new Mesa(null, 7, 4, true, r2, null);
        Mesa m8 = new Mesa(null, 8, 2, false, r2, null);
        Mesa m9 = new Mesa(null, 9, 6, true, r2, null);
        Mesa m10 = new Mesa(null, 10, 4, false, r2, null);
        Mesa m11 = new Mesa(null, 11, 8, true, r2, null);
        Mesa m12 = new Mesa(null, 12, 2, false, r2, null);

        mesaRepository.save(m1);
        mesaRepository.save(m2);
        mesaRepository.save(m3);
        mesaRepository.save(m4);
        mesaRepository.save(m5);
        mesaRepository.save(m6);
        mesaRepository.save(m7);
        mesaRepository.save(m8);
        mesaRepository.save(m9);
        mesaRepository.save(m10);
        mesaRepository.save(m11);
        mesaRepository.save(m12);

        // ======================
        // CLIENTES (10)
        // ======================

        Cliente c1 = new Cliente(null, "Marta Pérez", "marta@email.com", "600111111", true, null);
        Cliente c2 = new Cliente(null, "Ana López", "ana@email.com", "600222222", false, null);
        Cliente c3 = new Cliente(null, "Carlos Ruiz", "carlos@email.com", "600333333", true, null);
        Cliente c4 = new Cliente(null, "Lucía Gómez", "lucia@email.com", "600444444", false, null);
        Cliente c5 = new Cliente(null, "David Torres", "david@email.com", "600555555", false, null);
        Cliente c6 = new Cliente(null, "Elena Castro", "elena@email.com", "600666666", true, null);
        Cliente c7 = new Cliente(null, "Sergio Díaz", "sergio@email.com", "600777777", false, null);
        Cliente c8 = new Cliente(null, "Paula Romero", "paula@email.com", "600888888", true, null);
        Cliente c9 = new Cliente(null, "Javier Martín", "javier@email.com", "600999999", false, null);
        Cliente c10 = new Cliente(null, "Laura Gil", "laura@email.com", "600000000", true, null);

        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);
        clienteRepository.save(c4);
        clienteRepository.save(c5);
        clienteRepository.save(c6);
        clienteRepository.save(c7);
        clienteRepository.save(c8);
        clienteRepository.save(c9);
        clienteRepository.save(c10);

        // ======================
        // RESERVAS (30)
        // ======================

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,12), LocalTime.of(21,30), 4, EstadoReserva.CONFIRMADA, 86.50, m1, c1));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,12), LocalTime.of(20,00), 2, EstadoReserva.CONFIRMADA, 42.00, m2, c2));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,12), LocalTime.of(22,00), 5, EstadoReserva.CANCELADA, 120.00, m3, c3));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,12), LocalTime.of(19,30), 3, EstadoReserva.CONFIRMADA, 60.00, m4, c4));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,12), LocalTime.of(21,00), 6, EstadoReserva.NO_SHOW, 150.00, m5, c5));

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,13), LocalTime.of(20,15), 2, EstadoReserva.PENDIENTE, 35.00, m6, c6));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,13), LocalTime.of(22,30), 4, EstadoReserva.CONFIRMADA, 90.00, m7, c7));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,13), LocalTime.of(19,00), 2, EstadoReserva.CANCELADA, 78.00, m8, c8));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,13), LocalTime.of(21,10), 6, EstadoReserva.NO_SHOW, 115.00, m9, c9));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,13), LocalTime.of(20,45), 2, EstadoReserva.CONFIRMADA, 40.00, m10, c10));

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,14), LocalTime.of(18,30), 2, EstadoReserva.CONFIRMADA, 55.00, m11, c1));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,14), LocalTime.of(19,15), 3, EstadoReserva.PENDIENTE, 60.00, m12, c2));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,14), LocalTime.of(20,00), 4, EstadoReserva.CANCELADA, 70.00, m1, c3));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,14), LocalTime.of(21,45), 5, EstadoReserva.NO_SHOW, 80.00, m2, c4));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,14), LocalTime.of(22,10), 6, EstadoReserva.CONFIRMADA, 90.00, m3, c5));

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,15), LocalTime.of(18,00), 2, EstadoReserva.PENDIENTE, 100.00, m4, c6));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,15), LocalTime.of(19,40), 3, EstadoReserva.CANCELADA, 110.00, m5, c7));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,15), LocalTime.of(20,20), 4, EstadoReserva.NO_SHOW, 120.00, m6, c8));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,15), LocalTime.of(21,50), 5, EstadoReserva.CONFIRMADA, 130.00, m7, c9));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,15), LocalTime.of(22,30), 6, EstadoReserva.PENDIENTE, 140.00, m8, c10));

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,16), LocalTime.of(18,10), 2, EstadoReserva.CONFIRMADA, 150.00, m9, c1));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,16), LocalTime.of(19,20), 3, EstadoReserva.PENDIENTE, 160.00, m10, c2));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,16), LocalTime.of(20,30), 4, EstadoReserva.CANCELADA, 170.00, m11, c3));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,16), LocalTime.of(21,40), 5, EstadoReserva.NO_SHOW, 180.00, m12, c4));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,16), LocalTime.of(22,50), 6, EstadoReserva.CONFIRMADA, 190.00, m1, c5));

        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,17), LocalTime.of(18,25), 2, EstadoReserva.PENDIENTE, 200.00, m2, c6));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,17), LocalTime.of(19,35), 3, EstadoReserva.CANCELADA, 210.00, m3, c7));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,17), LocalTime.of(20,45), 4, EstadoReserva.NO_SHOW, 220.00, m4, c8));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,17), LocalTime.of(21,55), 5, EstadoReserva.CONFIRMADA, 230.00, m5, c9));
        reservaRepository.save(new Reserva(null, LocalDate.of(2026,5,17), LocalTime.of(22,05), 6, EstadoReserva.PENDIENTE, 240.00, m6, c10));


       IO.println("-----------------------------------------------------------------------------");
        System.out.println("=== RESTAURANTES ===");
        restauranteRepository.findAll().forEach(System.out::println);

        System.out.println("=== MESAS ===");
        mesaRepository.findAll().forEach(System.out::println);

        System.out.println("=== CLIENTES ===");
        clienteRepository.findAll().forEach(System.out::println);

        System.out.println("=== RESERVAS ===");
        reservaRepository.findAll().forEach(System.out::println);

        ReservaServicio servicio = new ReservaServicio();

        servicio.getReservasConfirmadas();
        servicio.getReservasPorRestaurante(1L);
        servicio.getReservasPendientesHoy();
        servicio.getRecaudacionPorRestaurante();
        servicio.getRestauranteConMasMesas();
        servicio.getReservasProblematicas();
        servicio.getReservasPorCiudad();
        servicio.getMesasMasSolicitadas();
        servicio.getImporteMedioPorTerraza();
        servicio.getClientesFrecuentes(3);


    }



}
