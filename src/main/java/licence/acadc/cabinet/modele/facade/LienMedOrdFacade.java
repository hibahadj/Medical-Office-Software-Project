
package licence.acadc.cabinet.modele.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import licence.acadc.cabinet.modele.entity.LienMedOrd;

@Stateless
public class LienMedOrdFacade extends AbstractFacade<LienMedOrd> {

    @PersistenceContext(unitName = "Cab-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LienMedOrdFacade() {
        super(LienMedOrd.class);
    }
}
