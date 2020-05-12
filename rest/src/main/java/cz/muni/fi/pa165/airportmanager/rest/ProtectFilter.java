package cz.muni.fi.pa165.airportmanager.rest;

import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.dto.UserDTO;
import cz.muni.fi.pa165.airportmanager.facade.UserFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;

/**
 * Manager protection filter
 *
 * @author Tomáš Janíček
 */
@WebFilter(urlPatterns = {"/flights/*"})
public class ProtectFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);


    @Override
    public void doFilter(ServletRequest r, ServletResponse s, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) r;
        HttpServletResponse response = (HttpServletResponse) s;

        String auth = request.getHeader("Authorization");
        if (auth == null) {
            response401(response);
            return;
        }
        String[] credentials = parseAuthHeader(auth);
        String name = credentials[0];
        String password = credentials[1];

        UserFacade userFacade = WebApplicationContextUtils.getWebApplicationContext(r.getServletContext()).getBean(UserFacade.class);
        UserDTO matchingManager = userFacade.findByName(name);
        if (matchingManager == null) {
            log.warn("no user with email {}", name);
            response401(response);
            return;
        }
        UserAuthenticateDTO userAuthenticateDTO = new UserAuthenticateDTO();
        userAuthenticateDTO.setId(matchingManager.getId());
        userAuthenticateDTO.setPassword(password);
        if (!userFacade.authenticate(userAuthenticateDTO)) {
            log.warn("wrong credentials: name={} password={}", credentials[0], credentials[1]);
            response401(response);
            return;
        }
        request.setAttribute("authenticatedManager", matchingManager);
        chain.doFilter(request, response);
    }


    private String[] parseAuthHeader(String auth) {
        return new String(Base64.getDecoder().decode(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Running in {}", filterConfig.getServletContext().getServerInfo());
    }

    @Override
    public void destroy() {

    }
}
