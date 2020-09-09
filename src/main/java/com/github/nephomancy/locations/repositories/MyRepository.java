package com.github.nephomancy.locations.repositories;

import com.github.nephomancy.locations.models.MyModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends CrudRepository<MyModel, Long> {
}
