package cz.muni.fi.pa165.airportmanager.rest.security;
import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

public class LoggedFilter implements Filter {
    final static Logger logger = LoggerFactory.getLogger(UserFilter.class);

    @Autowired
    private UserFacade userFacade;

    public void init(FilterConfig filterConfig) {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String auth = request.getHeader("Authorization");
        logger.debug("METHOD:", request.getMethod());
        System.out.println("METHOD" + request.getMethod());

        if ("OPTIONS".equals(request.getMethod())) {
            responsePreflight(response);
            return;
        }

        if (auth == null) {
            response401(response,"No Authorization header");
            return;
        }

        String[] credentials = parseAuthHeader(auth);
        String username = credentials[0];
        String password = credentials[1];

        if (username.isEmpty()|| password.isEmpty()) {
            response401(response, "Username and password can't be null");
            return;
        }

        UserDTO matchingUser = userFacade.findByName(username);

        if (matchingUser == null) {
            response401(response,"No matching user");
            return;
        }

        if (!checkRequirements(matchingUser)) {
            response401(response,"Insufficient permissions");
            return;
        }

        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setName(matchingUser.getName());
        userAuthenticateDTO.setPassword(password);

        if (!userFacade.authenticate(userAuthenticateDTO)) {
            logger.warn("wrong credentials: user={} password={}", credentials[0], credentials[1]);
            response401(response, "Can't authenticate user");
        }

        filterChain.doFilter(request, response);
    }

    private void response401(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//        response.setHeader("WWW-Authenticate", "Basic realm=\"type username and password\"");
        response.getWriter().println(String.format("<html><body><h1>401 Unauthorized %s</h1></body></html>", message));
    }

    private void responsePreflight(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "Authorization");
        response.getWriter().println("<html><body><h1>Preflight</h1></body></html>");
    }

    private String[] parseAuthHeader(String auth) {
        return new String(Base64.getDecoder().decode(auth.split(" ")[1])).split(":", 2);
    }

    public Boolean checkRequirements(UserDTO user) {
        return true;
    }
}
