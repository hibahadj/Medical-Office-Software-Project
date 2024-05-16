package licence.acadc.cabinet.controller;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(filterName = "NavigationFilter", urlPatterns = {"/page/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.REQUEST})
public class GlobaleFilter implements Filter {

    private static final String LOGIN_PAGE = "/login.xhtml";
    //private static final String ERROR_PAGE = "/pages/erreur/erreur403.xhtml";

    private FilterConfig filterConfig = null;

    public GlobaleFilter() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        Principal loginUser = httpRequest.getUserPrincipal();
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = (HttpSession) httpRequest.getSession(false);
        String uri = httpRequest.getRequestURI();
        System.out.println("yakoubkkkkkkkkk");
        if (loginUser == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
            System.out.println("yakoub");
        }
        if (session == null) {
            if (!response.isCommitted()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
            }

        } else {
            System.out.println("yakoubbbb");
            chain.doFilter(request, response);
        } 
    }

    public FilterConfig getFilterConfig() {
        return (this.filterConfig);
    }

    public void setFilterConfig(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) {
        this.filterConfig = filterConfig;
    }

    @Override
    public String toString() {
        if (filterConfig == null) {
            return ("NavigationFilter()");
        }
        StringBuilder sb = new StringBuilder("NavigationFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }


}
