package repositorio;

import jakarta.persistence.EntityManager;
import modelo.EstadoReserva;
import modelo.Reserva;
import util.JpaUtil;

import java.util.List;

public class ReservaRepository extends JpaRepository<Reserva, Long> {

    public ReservaRepository() {
        super(Reserva.class);
    }

    public List<Reserva> buscarPorEstado(EstadoReserva estado) {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            return em.createQuery("SELECT r FROM Reserva r WHERE r.estado = :estado",
                            Reserva.class)
                             .setParameter("estado", estado)
                                .getResultList();

        } finally {
            em.close();
        }
    }

    public List<Reserva> buscarPorCliente(Long clienteId) {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT r FROM Reserva r WHERE r.cliente.id = :id"
                            ,Reserva.class)
                            .setParameter("id", clienteId)
                            .getResultList();

        } finally {
            em.close();
        }
    }

}
