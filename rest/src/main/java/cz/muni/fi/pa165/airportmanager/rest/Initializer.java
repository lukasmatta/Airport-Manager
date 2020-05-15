package cz.muni.fi.pa165.airportmanager.rest;

import javax.servlet.Filter;

import cz.muni.fi.pa165.airportmanager.dto.UserAuthenticateDTO;
import cz.muni.fi.pa165.airportmanager.facade.UserFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.FrameworkServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Servlet initializer
 *
 * @author Petr Kantek
 */
public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
        @Autowired
        private UserFacade userFacade;

        @Override
        protected Class<?>[] getRootConfigClasses() {
            return new Class[]{MyConfiguration.class};
        }

        @Override
        protected Class<?>[] getServletConfigClasses() {
            return null;
        }

        @Override
        protected String[] getServletMappings() {
            return new String[]{"/"};
        }

        @Override
        protected Filter[] getServletFilters() {
            CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
            encodingFilter.setEncoding("utf-8");
            encodingFilter.setForceEncoding(true);

            ShallowEtagHeaderFilter shallowEtagHeaderFilter = new ShallowEtagHeaderFilter();

            return new Filter[]{encodingFilter, shallowEtagHeaderFilter};
        }

        @Override
        public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
            super.onStartup(servletContext);
            servletContext.addListener(RequestContextListener.class);
        }
}
