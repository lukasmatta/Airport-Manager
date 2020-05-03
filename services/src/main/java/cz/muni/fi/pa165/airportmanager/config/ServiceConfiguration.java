package cz.muni.fi.pa165.airportmanager.config;

import cz.muni.fi.pa165.airportmanager.AirplaneService;
import cz.muni.fi.pa165.airportmanager.FlightService;
import cz.muni.fi.pa165.airportmanager.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.airportmanager.StewardService;
import cz.muni.fi.pa165.airportmanager.dto.AirplaneDTO;
import cz.muni.fi.pa165.airportmanager.entity.Airplane;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.dozer.loader.api.BeanMappingBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@Import(PersistenceSampleApplicationContext.class)
@ComponentScan(basePackages = {"cz.muni.fi.pa165.airportmanager"})
public class ServiceConfiguration {

    @Bean
    public Mapper dozer(){
        DozerBeanMapper dozer = new DozerBeanMapper();
        dozer.addMapping(new DozerCustomConfig());
        return dozer;
    }

    /**
     * Custom config for Dozer if needed
     *
     */
    public class DozerCustomConfig extends BeanMappingBuilder {
        @Override
        protected void configure() {
//            mapping(Airplane.class, AirplaneDTO.class);
        }
    }

}