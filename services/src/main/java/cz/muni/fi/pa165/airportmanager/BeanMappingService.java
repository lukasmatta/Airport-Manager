package cz.muni.fi.pa165.airportmanager;

import org.dozer.Mapper;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Bean Mapping Service
 *
 * @author Tomáš Janíček
 */

public interface BeanMappingService {

    <T> List<T> mapToList(Collection<?> objects, Class<T> mapToClass);

    <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass);

    <T> T mapTo(Object u, Class<T> mapToClass);
}