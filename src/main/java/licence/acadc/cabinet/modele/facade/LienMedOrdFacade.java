/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package licence.acadc.cabinet.modele.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import licence.acadc.cabinet.modele.entity.LienMedOrd;

/**
 *
 * @author HADJIEDJ
 */
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
