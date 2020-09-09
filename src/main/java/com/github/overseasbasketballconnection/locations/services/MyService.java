package com.github.overseasbasketballconnection.locations.services;

import com.github.overseasbasketballconnection.locations.repositories.MyRepository;
import com.github.overseasbasketballconnection.locations.models.MyModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    private MyRepository repository;

    @Autowired
    public MyService(MyRepository repository) {
        this.repository = repository;
    }

    public Iterable<MyModel> index() {
        return repository.findAll();
    }

    public MyModel show(Long id) {
        return repository.findById(id).get();
    }

    public MyModel create(MyModel myModel) {
        return repository.save(myModel);
    }

    public MyModel update(Long id, MyModel newMyModelData) {
        MyModel originalMyModel = repository.findById(id).get();
        originalMyModel.setName(newMyModelData.getName());
        return repository.save(originalMyModel);
    }

    public Boolean delete(Long id) {
        repository.deleteById(id);
        return true;
    }
}
