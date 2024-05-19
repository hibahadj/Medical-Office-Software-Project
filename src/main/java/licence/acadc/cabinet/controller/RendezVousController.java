
package licence.acadc.cabinet.controller;

//import java.io.IOException;
//import java.io.InputStream;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

import licence.acadc.cabinet.modele.entity.RendezVous;
import licence.acadc.cabinet.modele.facade.RendezVousFacade;

@Named
@ViewScoped


public class RendezVousController implements Serializable {

    @Inject
    private RendezVousFacade RendezVousFacade;
    private RendezVous entity;
    private List<RendezVous> items = null;
    private List<String> treatmentOptions;

    
    @PostConstruct
    public void init() {
        treatmentOptions = new ArrayList<>();
        treatmentOptions.add("Enfant");
        treatmentOptions.add("Test");
        treatmentOptions.add("Adulte");
        treatmentOptions.add("Enciente");
    }

    public List<String> getTreatmentOptions() {
        return treatmentOptions;
    }

    public RendezVous getentity() {
        if (entity == null) {
            entity = new RendezVous();
        }
        return entity;
    }

    public void setentity(RendezVous ent) {
        this.entity = ent;
    }
    
    /*
    public void onRowSelect(SelectEvent event) {
        entity = (RendezVous) event.getObject();
        //showDetail = true;
        //editMode = true;
    }
    */
    
    
    public void createRendezVous() {
         try {
            RendezVousFacade.create(entity);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Rendez-vous créé avec succès"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erreur lors de la création du rendez-vous"));
        }
    }
    
    public List<RendezVous> getItems() {
        if (items == null) {
            items = RendezVousFacade.findAll();
        }
        return items;
    }
    
    public void setLitems(List<RendezVous> items) {
        this.items = items;
    }

    
    public void removeRendezVous() throws Exception {
        RendezVousFacade.remove(entity);
    }

    
     
    public void onRowEditRendezVous(RowEditEvent event) {
        RendezVousFacade.edit(entity);
        
    }
    
    public RendezVous getEntity() {
        return entity;
    }

    public void setEntity(RendezVous entity) {
        this.entity = entity;
    }

   
    
   
    
}
