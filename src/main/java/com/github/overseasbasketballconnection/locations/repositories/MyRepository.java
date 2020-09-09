package com.github.overseasbasketballconnection.locations.repositories;

import com.github.overseasbasketballconnection.locations.models.MyModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyRepository extends CrudRepository<MyModel, Long> {
}
