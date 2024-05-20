
package licence.acadc.cabinet.modele.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import licence.acadc.cabinet.modele.entity.Fichier;

@Stateless        
public class FichierFacade extends AbstractFacade<Fichier> {

    @PersistenceContext(unitName = "Cab-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FichierFacade () {
        super(Fichier.class);
    }
}
