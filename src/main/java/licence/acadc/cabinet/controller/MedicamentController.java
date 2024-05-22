package licence.acadc.cabinet.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import licence.acadc.cabinet.modele.entity.Fichier;
import licence.acadc.cabinet.modele.facade.CabUserFacade;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import licence.acadc.cabinet.modele.entity.Medicament;
import licence.acadc.cabinet.modele.facade.MedicamentFacade;
import org.apache.commons.fileupload.util.Streams;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

@Named
@ViewScoped
public class MedicamentController implements Serializable {

    @Inject
    private MedicamentFacade MedicamentFacade;
    
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

    public void onInitMedicament() {
        entity = new Medicament();
    }

    public void createMedicament() {
        
        MedicamentFacade.create(entity);
        listAll = MedicamentFacade.findAll();
        entity = new Medicament();
        PrimeFaces.current().executeScript("PF('MedocDlg').hide()"); 
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Le medicament a été Crée avec succès"));
    }

    public void onRowEditMedicament(RowEditEvent event) {
        MedicamentFacade.edit(entity);

    }

    public void removeMedicament() {
        MedicamentFacade.remove(entity);
        listAll.remove(entity);
    }
    
    

    public static String getUserPrincipalRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getUserPrincipal().toString();
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

    
}
