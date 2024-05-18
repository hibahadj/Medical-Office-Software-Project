
package licence.acadc.cabinet.modele.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import licence.acadc.cabinet.modele.entity.Ordonnance;

@Stateless
public class OrdonnanceFacade extends AbstractFacade<Ordonnance> {

    @PersistenceContext(unitName = "Cab-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdonnanceFacade() {
        super(Ordonnance.class);
    }
}
