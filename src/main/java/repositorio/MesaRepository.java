package repositorio;

import jakarta.persistence.EntityManager;
import modelo.Mesa;
import util.JpaUtil;

import java.util.List;

public class MesaRepository extends JpaRepository<Mesa, Long> {

    public MesaRepository() {
        super(Mesa.class);
    }

    public List<Mesa> buscarPorRestaurante(Long restauranteId) {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            return em.createQuery(
                            "SELECT m FROM Mesa m WHERE m.restaurante.id = :id", Mesa.class)
                    .setParameter("id", restauranteId)
                    .getResultList();

        } finally {
            em.close();
        }
    }


}
