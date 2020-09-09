package com.github.nephomancy.locations.utils.controllers;

import com.github.nephomancy.locations.utils.services.EntityInterface;
import com.github.nephomancy.locations.utils.services.SimpleServiceInterface;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

public interface SimpleControllerInterface<
        IdType extends Serializable,
        EntityType extends EntityInterface<IdType>,
        CrudRepositoryType extends CrudRepository<EntityType, IdType>,
        ServiceType extends SimpleServiceInterface<IdType, EntityType, CrudRepositoryType>> {
    ServiceType getService();

    @GetMapping("/")
    default ResponseEntity<Iterable<EntityType>> index() {
        return new ResponseEntity<>(getService().findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    default ResponseEntity<EntityType> show(@PathVariable IdType id) {
        return new ResponseEntity<>(getService().findById(id), HttpStatus.OK);
    }

    @PostMapping("/")
    default ResponseEntity<EntityType> create(@RequestBody EntityType myModel) {
        return new ResponseEntity<>(getService().create(myModel), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    default ResponseEntity<EntityType> update(@PathVariable IdType id, @RequestBody EntityType myModel) {
        return new ResponseEntity<>(getService().update(id, myModel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    default ResponseEntity<EntityType> destroy(@PathVariable IdType id) {
        return new ResponseEntity<>(getService().delete(id), HttpStatus.OK);
    }
}
