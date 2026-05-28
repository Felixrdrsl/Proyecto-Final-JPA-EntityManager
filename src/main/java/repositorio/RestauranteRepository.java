package repositorio;

import jakarta.persistence.EntityManager;
import modelo.Restaurante;
import util.JpaUtil;

import java.util.List;

public class RestauranteRepository extends JpaRepository<Restaurante, Long>{

    public RestauranteRepository() {
        super(Restaurante.class);
    }



}
