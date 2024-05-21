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

import licence.acadc.cabinet.modele.entity.Patient;
import licence.acadc.cabinet.modele.entity.RendezVous;
import licence.acadc.cabinet.modele.facade.FichierFacade;
import licence.acadc.cabinet.modele.facade.PatientFacade;
import licence.acadc.cabinet.modele.facade.RendezVousFacade;
import org.apache.commons.fileupload.util.Streams;
import org.primefaces.model.file.UploadedFiles;
import org.primefaces.shaded.commons.io.FilenameUtils;
import org.primefaces.shaded.commons.io.IOUtils;

@Named
@ViewScoped
public class PatientController implements Serializable {

    @Inject
    private PatientFacade patientFacade;
    @Inject
    private CabUserFacade userFacade;
    @Inject
    private RendezVousFacade rdvFacade;
    @Inject
    private FichierFacade fileFacade;

    private Date sysDate = new Date();

    private Patient entity;
    private List<Patient> listAll;
    private RendezVous entityRdv;
    private Fichier entityFile;

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
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Le Patient a été Crée avec succès"));
    }

    public void onRowEditPatient(RowEditEvent event) {
        patientFacade.edit(entity);

    }

    public void removePatient() {
        patientFacade.remove(entity);
        listAll.remove(entity);
    }

    public void onInitRdv() {
        entityRdv = new RendezVous();
    }

    public void createRdv() {
        entityRdv.setRdvEtat("En cours");
        entityRdv.setFkRdvPat(entity);
        try {
            rdvFacade.create(entityRdv);
            entity.getRendezVousList().add(entityRdv);
            entityRdv = new RendezVous();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Le rendez-vous a été Crée avec succès"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
        }
    }

    public void onRowEditRdv(RowEditEvent event) {
        RendezVous rdv = (RendezVous) event.getObject();
        try {
            rdvFacade.edit(rdv);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Modification effectué avec succès"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
        }
    }

    public void onInitFile() {
        entityFile = new Fichier();
    }

    public void uploadFile(FileUploadEvent event) {
        UploadedFile f = (UploadedFile) event.getFile();
        if (f != null) {
            try {
                System.out.println("uploadMultiple1");
                InputStream inputStr = f.getInputStream();
                entityFile = new Fichier();
                entityFile.setFichName(f.getFileName());
                entityFile.setFichFile(IOUtils.toByteArray(inputStr));
                entityFile.setFichDate(new Date());
                entityFile.setFichType(FilenameUtils.getExtension(f.getFileName()));
                entityFile.setFkFichPat(entity);
                try {
                    fileFacade.create(entityFile);
                    entity.getFichierList().add(entityFile);
                    FacesMessage message = new FacesMessage("Successful", f.getFileName() + " is uploaded.");
                    FacesContext.getCurrentInstance().addMessage(null, message);
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
                }

            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
            }
        }
    }

    public void removeFile() {
        fileFacade.remove(entityFile);
        entity.getFichierList().remove(entityFile);
        entityFile = new Fichier();
    }

    public void downloadFile(Fichier f) throws IOException {
        InputStream uploadFile;
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        uploadFile = new ByteArrayInputStream(f.getFichFile());
        String fileType = FacesContext.getCurrentInstance().getExternalContext().getMimeType(f.getFichName());
        if (fileType == null) {
            fileType = "application/x-www-form-urlencoded";
        }
        ec.responseReset();
        ec.setResponseContentType(fileType);
        String attachmentName = "attachment; filename=\"" + f.getFichName() + "\"";
        ec.setResponseHeader("Content-Disposition", attachmentName);
        OutputStream output = ec.getResponseOutputStream();
        Streams.copy(uploadFile, output, false);
        fc.responseComplete();
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

    public RendezVous getEntityRdv() {
        return entityRdv;
    }

    public void setEntityRdv(RendezVous entityRdv) {
        this.entityRdv = entityRdv;
    }

    public Fichier getEntityFile() {
        return entityFile;
    }

    public void setEntityFile(Fichier entityFile) {
        this.entityFile = entityFile;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }
}
