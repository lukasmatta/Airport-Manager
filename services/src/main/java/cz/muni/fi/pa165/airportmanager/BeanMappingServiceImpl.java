package cz.muni.fi.pa165.airportmanager;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Bean Mapping Service Implementation
 *
 * @author Tomáš Janíček
 */

@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    private ModelMapper modelMapper;

    @Autowired
    public BeanMappingServiceImpl(ModelMapper modelMapper){
        this.modelMapper = modelMapper;
    }

    public <T> List<T> mapToList(Collection<?> objects, Class<T> mapToClass) {
        List<T> mappedCollection = new ArrayList<>();
        for (Object object : objects) {
            mappedCollection.add(modelMapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    @Override
    public <T> Set<T> mapToSet(Collection<?> objects, Class<T> mapToClass) {
        Set<T> mappedCollection = new HashSet<>();
        for (Object object : objects) {
            mappedCollection.add(modelMapper.map(object, mapToClass));
        }
        return mappedCollection;
    }

    public <T> T mapTo(Object u, Class<T> mapToClass) {
        return modelMapper.map(u, mapToClass);
    }
}
