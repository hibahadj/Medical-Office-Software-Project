
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
import org.primefaces.event.SelectEvent;
import java.util.Arrays;
import java.util.List;


import licence.acadc.cabinet.modele.entity.RendezVous;
import licence.acadc.cabinet.modele.facade.RendezVousFacade;

@Named
@ViewScoped


public class RendezVousController implements Serializable {

    @Inject
    private RendezVousFacade RendezVousFacade;
    private RendezVous entity;
    private List<RendezVous> rdv = null;
        private List<String> etatOptions = Arrays.asList("plannifié", "terminé", "annulé");

    

    
    @PostConstruct
    public void init() {
          rdv = RendezVousFacade.findAll();

    }

    

    public void onRowSelect(SelectEvent event) {
        entity = (RendezVous) event.getObject();
        //showDetail = true;
        //editMode = true;
    }

    public void createPMedicamentt() {
        RendezVousFacade.create(entity);
    }

    public void onRowEditMedicament(RowEditEvent event) {
        RendezVousFacade.edit(entity);
        
    }

    public void removeMedicament() throws Exception {
        RendezVousFacade.remove(entity);
    }

    /*
    public void uploadPatient(FileUploadEvent event) {
        try {
            UploadedFile uploadedFile = (UploadedFile) event.getFile();
            //InputStream inputStr = uploadedFile.getInputstream();
            //file = IOUtils.toByteArray(inputStr);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "le fichier " + uploadedFile.getFileName() + " est chargé."));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
        }
    }
     */
    
    public RendezVous getEntity() {
        return entity;
    }

    public void setEntity(RendezVous entity) {
        this.entity = entity;
    }

    public List<RendezVous> getListAll() {
        return rdv;
    }

    public void setListAll(List<RendezVous> listAll) {
        this.rdv = listAll;
    }
    
     public List<String> getEtatOptions() {
        return etatOptions;
    }

    public void setEtatOptions(List<String> etatOptions) {
        this.etatOptions = etatOptions;
    }
    
    

   
    
   
    
}
