import jakarta.persistence.EntityManager;
import util.JpaUtil;

import javax.swing.text.html.parser.Entity;

public class Main {
    static void main() {
        EntityManager em = JpaUtil.createEntityManager();








        em.close();



    }


}
