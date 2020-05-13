package cz.muni.fi.pa165.airportmanager.sampledata;

import cz.muni.fi.pa165.airportmanager.config.ServiceConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

/**
 * Takes ServiceConfiguration and adds the SampleDataLoadingFacade bean.
 *
 * @author Martin Kuba makub@ics.muni.cz
 */
@Configuration
@Import(ServiceConfiguration.class)
@ComponentScan(basePackageClasses = {SampleDataLoadingFacadeImpl.class})
public class EshopWithSampleDataConfiguration {

    final static Logger log = LoggerFactory.getLogger(SampleDataLoadingFacadeImpl.class);

    @Autowired
    SampleDataLoadingFacadeImpl sampleDataLoadingFacade;

    @PostConstruct
    public void dataLoading() {
        log.debug("dataLoading()");
        sampleDataLoadingFacade.loadData();
    }
}
