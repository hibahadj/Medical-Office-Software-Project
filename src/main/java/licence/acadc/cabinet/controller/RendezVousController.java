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

import licence.acadc.cabinet.modele.entity.RendezVous;
import licence.acadc.cabinet.modele.facade.RendezVousFacade;
import org.apache.commons.fileupload.util.Streams;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

@Named
@ViewScoped
public class RendezVousController implements Serializable {

    
    @Inject
    private RendezVousFacade rendezVousFacade;
    private Date sysDate = new Date();

    private RendezVous entity;
    private List<RendezVous> listAll;

    @PostConstruct
    public void init() {
        listAll = rendezVousFacade.findAll();
    }

    public RendezVousController() {
    }

    public void onRowSelect(SelectEvent event) {
        entity = (RendezVous) event.getObject();
        //showDetail = true;
        //editMode = true;
    }

    public void onInitRendezVous() {
        entity = new RendezVous();
    }

    public void createRendezVous() {
        rendezVousFacade.create(entity);
        listAll = rendezVousFacade.findAll();
        entity = new RendezVous();
        PrimeFaces.current().executeScript("PF('rendezVousDlg').hide()");
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Le Rendez-vous a été enregistrée avec succès"));
    }

    public void onRowEditRendezVous(RowEditEvent event) {
        rendezVousFacade.edit(entity);

    }

    public void removerdv() {
        rendezVousFacade.remove(entity);
        listAll.remove(entity);
    }

  
    
    public static String getUserPrincipalRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getUserPrincipal().toString();
    }

    public RendezVous getEntity() {
        return entity;
    }

    public void setEntity(RendezVous entity) {
        this.entity = entity;
    }

    public List<RendezVous> getListAll() {
        return listAll;
    }

    public void setListAll(List<RendezVous> listAll) {
        this.listAll = listAll;
    }
}

    
    