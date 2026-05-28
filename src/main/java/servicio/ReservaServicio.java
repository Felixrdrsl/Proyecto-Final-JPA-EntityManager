package servicio;

import jakarta.persistence.EntityManager;
import modelo.EstadoReserva;
import modelo.Reserva;
import modelo.Restaurante;
import util.JpaUtil;

import java.time.LocalDate;
import java.util.List;

public class ReservaServicio {

    /**
     * CONSULTA 1 Reservas confirmadas ordenadas por fecha
     */
    public void getReservasConfirmadas() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Reserva> reservas = em.createQuery(
                            "FROM Reserva r WHERE r.estado = " +
                                    ":estado ORDER BY r.fechaReserva ASC",
                            Reserva.class)
                    .setParameter("estado", EstadoReserva.CONFIRMADA)
                    .getResultList();

            IO.println("--- RESERVAS CONFIRMADAS ---");

            reservas.forEach(IO::println);

        } finally {
            em.close();
        }
    }

    /**
     *CONSULTA 2 Reservas de un restaurante concreto
     * @param restauranteId
     */

    public void getReservasPorRestaurante(Long restauranteId) {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Reserva> reservas = em.createQuery(
                            "SELECT r FROM Reserva r " +
                                    "JOIN r.mesa m " +
                                    "JOIN m.restaurante res " +
                                    "WHERE res.id = :id",
                            Reserva.class
                    )
                    .setParameter("id", restauranteId)
                    .getResultList();

            IO.println("--- RESERVAS DEL RESTAURANTE ---");

            reservas.forEach(IO::println);

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 3 Reservas pendientes para hoy
     */

    public void getReservasPendientesHoy() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Reserva> reservas = em.createQuery(
                            "FROM Reserva r WHERE r.estado =" +
                                    " :estado AND r.fechaReserva = :hoy",
                            Reserva.class)
                    .setParameter("estado", EstadoReserva.PENDIENTE)
                    .setParameter("hoy", LocalDate.now())
                    .getResultList();

            IO.println("--- RESERVAS PENDIENTES DE HOY ---");

            reservas.forEach(IO::println);

        } finally {
            em.close();
        }
    }


    /**
     *  CONSULTA 4 Recaudación total por restaurante
     */

    public void getRecaudacionPorRestaurante() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Object[]> lista = em.createQuery(
                    "SELECT r.mesa.restaurante.nombre, SUM(r.importeEstimado) " +
                            "FROM Reserva r " +
                            "GROUP BY r.mesa.restaurante.nombre " +
                            "ORDER BY SUM(r.importeEstimado) DESC",
                    Object[].class
            ).getResultList();

            IO.println("--- RECAUDACIÓN POR RESTAURANTE ---");

            lista.stream()
                    .map(obj -> obj[0] + " -> " + obj[1] + "€")
                    .forEach(IO::println);

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 5 Restaurante con más mesas
     */

    public void getRestauranteConMasMesas() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            Restaurante restaurante = em.createQuery(
                            "FROM Restaurante r ORDER BY SIZE(r.mesas) DESC",
                            Restaurante.class)
                    .getResultList()
                    .stream()
                    .findFirst()
                    .orElse(null);

            IO.println("--- RESTAURANTE CON MÁS MESAS ---");

            IO.println(restaurante);

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 6 Reservas canceladas o no presentadas
     */

    public void getReservasProblematicas() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Reserva> reservas = em.createQuery(
                            "FROM Reserva r WHERE r.estado IN (:estados) " +
                                    "ORDER BY r.fechaReserva DESC",
                            Reserva.class
                    )
                    .setParameter(
                            "estados",
                            List.of(
                                    EstadoReserva.CANCELADA,
                                    EstadoReserva.NO_SHOW
                            )
                    )
                    .getResultList();

            IO.println("--- RESERVAS PROBLEMÁTICAS ---");

            reservas.forEach(IO::println);

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 7 Número de reservas por ciudad
     */

    public void getReservasPorCiudad() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Object[]> lista = em.createQuery(
                    "SELECT r.mesa.restaurante.ciudad, COUNT(r) " +
                            "FROM Reserva r " +
                            "GROUP BY r.mesa.restaurante.ciudad",
                    Object[].class
            ).getResultList();

            IO.println("--- RESERVAS POR CIUDAD ---");

            lista.forEach(obj ->
                    IO.println(obj[0] + " -> " + obj[1])
            );

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 8 Mesas más solicitadas
     */

    public void getMesasMasSolicitadas() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Object[]> lista = em.createQuery(
                    "SELECT r.mesa.numero, COUNT(r) " +
                            "FROM Reserva r " +
                            "GROUP BY r.mesa.numero " +
                            "ORDER BY COUNT(r) DESC",
                    Object[].class
            ).getResultList();

            IO.println("--- MESAS MÁS SOLICITADAS ---");

            lista.forEach(obj ->
                    IO.println("Mesa " + obj[0] + " -> " + obj[1] + " reservas")
            );

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 9 Importe medio por terraza
     */

    public void getImporteMedioPorTerraza() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<Object[]> lista = em.createQuery(
                    "SELECT m.terraza, AVG(r.importeEstimado) " +
                            "FROM Reserva r JOIN r.mesa m " +
                            "GROUP BY m.terraza",
                    Object[].class
            ).getResultList();

            IO.println("--- IMPORTE MEDIO POR TERRAZA ---");

            lista.forEach(obj -> {
                String tipo;
                if ((Boolean) obj[0]) {
                    tipo = "Terraza";
                } else {
                    tipo = "Interior";
                }

                IO.println(tipo + " -> " + obj[1] + "€");
            });

        } finally {
            em.close();
        }
    }


    /**
     * CONSULTA 10 Clientes frecuentes
     * @param minimoReservas
     */

    public void getClientesFrecuentes(int minimoReservas) {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            List<String> clientes = em.createQuery(
                            "SELECT r.cliente.nombre FROM Reserva r",
                            String.class)
                    .getResultList()
                    .stream()
                    .distinct()
                    .filter(nombre -> {
                        Long total = em.createQuery(
                                        "SELECT COUNT(r) FROM Reserva r " +
                                                "WHERE r.cliente.nombre = :nombre", Long.class)
                                .setParameter("nombre", nombre)
                                .getSingleResult();

                        return total >= minimoReservas;
                    })
                    .toList();

            IO.println("--- CLIENTES FRECUENTES ---");

            clientes.forEach(IO::println);

        } finally {
            em.close();
        }
    }


}
