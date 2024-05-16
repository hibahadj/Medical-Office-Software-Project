package licence.acadc.cabinet.controller;




import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Named
@RequestScoped
    public class Logout implements Serializable {
    
    
    public Logout() {
    }

    public String deconnection() throws ServletException {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest req = (HttpServletRequest) externalContext.getRequest();
            externalContext.invalidateSession();
            req.logout();
        return "/index.jsp";
    }
}

