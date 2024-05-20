
package licence.acadc.cabinet.modele.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import licence.acadc.cabinet.modele.entity.CabUser;

@Stateless
public class CabUserFacade extends AbstractFacade<CabUser> {

    @PersistenceContext(unitName = "Cab-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CabUserFacade() {
        super(CabUser.class);
    }

    public CabUser findByUsername(String userName) {
        Query q = em.createNamedQuery("CabUser.findByUsername");
        q.setParameter("username", userName);
        List<CabUser> list = q.getResultList();
        return list.isEmpty() ? null : list.get(0);
    }
}
