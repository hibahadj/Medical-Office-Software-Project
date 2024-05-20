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

@WebFilter(filterName = "GlobaleFilter", urlPatterns = {"/page/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.ERROR, DispatcherType.REQUEST})
public class GlobaleFilter implements Filter {

    private static final String LOGIN_PAGE = "/login.xhtml";

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
        if (loginUser == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
        }
        if (session == null) {
            if (!response.isCommitted()) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_PAGE);
            }

        } else {
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
            return ("GlobaleFilter()");
        }
        StringBuilder sb = new StringBuilder("GlobaleFilter(");
        sb.append(filterConfig);
        sb.append(")");
        return (sb.toString());
    }


}
