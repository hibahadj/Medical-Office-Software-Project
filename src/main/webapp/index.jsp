
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%--<%@page import="dz.elit.sgc.gisement.administration.service.AdminUtilisateurFacadeLocal"%>
<%@page import="dz.elit.sgc.gisement.commun.util.StaticUtil"%>
<%@page import="dz.elit.sgc.gisement.administration.entity.AdminUtilisateur"%>
<%@page import="dz.elit.sgc.commun.util.MyUtil"%>--%>
<%@page import="javax.faces.application.FacesMessage"%>
<%@page import="javax.faces.context.FacesContext"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.security.Principal"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%
    Principal loginUser = request.getUserPrincipal();
    if (loginUser != null) {
        System.out.println("loginUser"+loginUser);
        Context c = new InitialContext();
        response.sendRedirect(request.getContextPath() + "/page/acceuil.xhtml");
    } else {
        
        response.sendRedirect(request.getContextPath() + "/login.xhtml");
    }
%>