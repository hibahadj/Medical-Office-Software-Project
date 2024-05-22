package licence.acadc.cabinet.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import licence.acadc.cabinet.modele.entity.LienMedOrd;
import licence.acadc.cabinet.modele.entity.Medicament;
import licence.acadc.cabinet.modele.entity.Ordonnance;
import licence.acadc.cabinet.modele.facade.LienMedOrdFacade;
import licence.acadc.cabinet.modele.facade.MedicamentFacade;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import licence.acadc.cabinet.modele.facade.OrdonnanceFacade;

@Named
@ViewScoped
public class OrdonanceController implements Serializable {

    @Inject
    private OrdonnanceFacade ordonanceFacade;
    @Inject
    private MedicamentFacade mdcFacade;
    @Inject
    private LienMedOrdFacade lienFacade;

    private Ordonnance entity;
    private List<Ordonnance> listAll;
    private LienMedOrd entityLien;
    private List<Medicament> listMdc;
    private int medId;

    @PostConstruct
    public void init() {
        listAll = ordonanceFacade.findAll();
        listMdc = mdcFacade.findAll();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String code = request.getParameter("search");
        if (code != null && code.equals("true")) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                entity = (Ordonnance) session.getAttribute("ordonance");
                session.removeAttribute("ordonance");
            }
        }
    }

    public OrdonanceController() {
    }

    public void onRowSelect(SelectEvent event) {
        entity = (Ordonnance) event.getObject();
    }

    public void createOrd() {
        ordonanceFacade.create(entity);
    }

    public void onRowEditOrd(RowEditEvent event) {
        ordonanceFacade.edit(entity);
    }

    public void removeOrd() throws Exception {
        ordonanceFacade.remove(entity);
        listAll.remove(entity);
    }

    public void onInitLien() {
        entityLien = new LienMedOrd();
    }

    public void createLien() {
        entityLien.setFkMedLien(mdcFacade.find(medId));
        entityLien.setFkOrdLien(entity);
        try {
            lienFacade.create(entityLien);
            entity.getLienMedOrdList().add(entityLien);
            entityLien = new LienMedOrd();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Le Medicament a été Crée avec succès"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
        }
    }

    public void onRowEditMdc(RowEditEvent event) {
        LienMedOrd lien = (LienMedOrd) event.getObject();
        try {
            lienFacade.edit(lien);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info :", "Modification effectué avec succès"));
        } catch (Exception ex) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur :", "Erreur Inconnue"));
        }
    }

    public void removeLien() {
        lienFacade.remove(entityLien);
        entity.getLienMedOrdList().remove(entityLien);
    }

    public Ordonnance getEntity() {
        return entity;
    }

    public void setEntity(Ordonnance entity) {
        this.entity = entity;
    }

    public List<Ordonnance> getListAll() {
        return listAll;
    }

    public void setListAll(List<Ordonnance> listAll) {
        this.listAll = listAll;
    }

    public LienMedOrd getEntityLien() {
        return entityLien;
    }

    public void setEntityLien(LienMedOrd entityLien) {
        this.entityLien = entityLien;
    }

    public List<Medicament> getListMdc() {
        return listMdc;
    }

    public void setListMdc(List<Medicament> listMdc) {
        this.listMdc = listMdc;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }
}
