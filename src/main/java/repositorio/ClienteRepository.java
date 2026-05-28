package repositorio;

import jakarta.persistence.EntityManager;
import modelo.Cliente;
import util.JpaUtil;

import java.util.List;

public class ClienteRepository extends JpaRepository<Cliente, Long> {

    public ClienteRepository() {
        super(Cliente.class);
    }


    public List<Cliente> buscarVip() {

        EntityManager em = JpaUtil.createEntityManager();

        try {

            return em.createQuery("SELECT c FROM Cliente c WHERE c.vip = true"
                    , Cliente.class).getResultList();

        } finally {
            em.close();
        }
    }


}
