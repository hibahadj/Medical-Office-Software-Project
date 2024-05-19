package licence.acadc.cabinet.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import licence.acadc.cabinet.modele.entity.Medicament;
import licence.acadc.cabinet.modele.facade.MedicamentFacade;

@Named
@ViewScoped
public class MedicamentController implements Serializable {

    @Inject
    private MedicamentFacade MedicamentFacade;

    private byte[] file;
    private Medicament entity;
    private List<Medicament> listAll;
    
    @PostConstruct
    public void init() {
        listAll = MedicamentFacade.findAll();
    }

    public MedicamentController() {
    }
    
    public void onRowSelect(SelectEvent event) {
        entity = (Medicament) event.getObject();
        //showDetail = true;
        //editMode = true;
    }

    public void createPMedicamentt() {
        MedicamentFacade.create(entity);
    }

    public void onRowEditMedicament(RowEditEvent event) {
        MedicamentFacade.edit(entity);
        
    }

    public void removeMedicament() throws Exception {
        MedicamentFacade.remove(entity);
    }

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

    public Medicament getEntity() {
        return entity;
    }

    public void setEntity(Medicament entity) {
        this.entity = entity;
    }

    public List<Medicament> getListAll() {
        return listAll;
    }

    public void setListAll(List<Medicament> listAll) {
        this.listAll = listAll;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
