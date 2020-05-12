package cz.muni.fi.pa165.airportmanager.rest.assemblers;

import cz.muni.fi.pa165.airportmanager.dto.BaseDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Generic assembler class to satisfy HATEOAS requirements of REST APIs.
 *
 * @author Petr Kantek
 */
@Component
public class GenericResourceAssembler<TEntity extends BaseDTO>  {

    public EntityModel<TEntity> toModel(TEntity entity, Class<?> controller) {
        EntityModel<TEntity> resource = new EntityModel<>(entity);

        try {
            resource.add(linkTo(controller).slash("create").withRel("CREATE"));
            resource.add(linkTo(controller).slash(entity.getId()).withRel("READ"));
            resource.add(linkTo(controller).slash("update").withRel("UPDATE"));
            resource.add(linkTo(controller).slash(entity.getId()).withRel("DELETE"));

        } catch (Exception ex) {
            Logger.getLogger(GenericResourceAssembler.class.getName()).log(Level.SEVERE, "could not link resources", ex);
        }
        return resource;
    }

    public CollectionModel<EntityModel<TEntity>> toCollectionModel(Iterable<? extends TEntity> entities, Class<?> controller) {

        Collection<EntityModel<TEntity>> resources = new ArrayList<>();
        for (TEntity entity : entities) {
            resources.add(toModel(entity, controller));
        }

        CollectionModel<EntityModel<TEntity>> modelResources = new CollectionModel<>(resources);
        modelResources.add(linkTo(controller).withSelfRel());
        return modelResources;
    }
}
