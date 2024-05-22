package licence.acadc.cabinet.modele.facade;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import licence.acadc.cabinet.modele.entity.Patient;

@Stateless
public class PatientFacade extends AbstractFacade<Patient> {

    @PersistenceContext(unitName = "Cab-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PatientFacade() {
        super(Patient.class);
    }
    
    public boolean findByNomPreDate(String nom, String prenom, Date ddn) {
        Query q = em.createNativeQuery("SELECT * FROM patient WHERE TRIM(PAT_NOM) = ? AND TRIM(PAT_PRENOM) = ?",Patient.class );
        q.setParameter(1, nom.trim());
        q.setParameter(2, prenom.trim());
        q.setParameter(3, ddn);
        List<Patient> list = q.getResultList();
        return !list.isEmpty();
    }  
}
