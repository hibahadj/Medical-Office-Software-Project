package licence.acadc.cabinet.controller;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import licence.acadc.cabinet.modele.entity.Ordonnance;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;
import licence.acadc.cabinet.modele.facade.OrdonnanceFacade;

@Named
@ViewScoped
public class OrdonanceController implements Serializable {

    @Inject
    private OrdonnanceFacade ordonanceFacade;

    private Ordonnance entity;
    private List<Ordonnance> listAll;

    @PostConstruct
    public void init() {
        listAll = ordonanceFacade.findAll();
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String code = request.getParameter("search");
        if (code != null && code.equals("true")) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                entity = (Ordonnance)session.getAttribute("ordonance");
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
}
