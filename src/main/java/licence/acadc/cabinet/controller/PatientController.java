package licence.acadc.cabinet.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import licence.acadc.cabinet.modele.entity.Patient;
import licence.acadc.cabinet.modele.facade.CabUserFacade;
import licence.acadc.cabinet.modele.facade.PatientFacade;
import org.primefaces.PrimeFaces;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

@Named
@ViewScoped
public class PatientController implements Serializable {

    @Inject
    private PatientFacade patientFacade;
    @Inject
    private CabUserFacade userFacade;

    private byte[] file;
    private Patient entity;
    private List<Patient> listAll;
    private Date sysDate = new Date();

    @PostConstruct
    public void init() {
        listAll = patientFacade.findAll();
    }

    public PatientController() {
    }

    public void onRowSelect(SelectEvent event) {
        entity = (Patient) event.getObject();
        //showDetail = true;
        //editMode = true;
    }

    public void onInitPatient() {
        entity = new Patient();
    }

    public void createPatient() {
        entity.setPatCreeDate(sysDate);
        entity.setFkUserPat(userFacade.findByUsername(getUserPrincipalRequest()));
        patientFacade.create(entity);
        listAll = patientFacade.findAll();
        entity = new Patient();
        PrimeFaces.current().executeScript("PF('patientDlg').hide()");
    }

    public void onRowEditPatient(RowEditEvent event) {
    }

    public void removePatient() throws Exception {
        patientFacade.remove(entity);
        listAll.remove(entity);
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

    public static String getUserPrincipalRequest() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getUserPrincipal().toString();
    }

    public Patient getEntity() {
        return entity;
    }

    public void setEntity(Patient entity) {
        this.entity = entity;
    }

    public List<Patient> getListAll() {
        return listAll;
    }

    public void setListAll(List<Patient> listAll) {
        this.listAll = listAll;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }
}
