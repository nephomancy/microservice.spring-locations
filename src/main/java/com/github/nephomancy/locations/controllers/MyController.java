package com.github.nephomancy.locations.controllers;

import com.github.nephomancy.locations.services.MyService;
import com.github.nephomancy.locations.models.MyModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/my-controller")
public class MyController {
    private MyService service;

    public MyController(MyService service) {
        this.service = service;
    }

    @GetMapping("/")
    public ResponseEntity<Iterable<MyModel>> index() {
        return new ResponseEntity<>(service.index(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MyModel> show(@PathVariable Long id) {
        return new ResponseEntity<>(service.show(id), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<MyModel> create(@RequestBody MyModel myModel) {
        return new ResponseEntity<>(service.create(myModel), HttpStatus.CREATED);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MyModel> update(@PathVariable Long id, @RequestBody MyModel myModel) {
        return new ResponseEntity<>(service.update(id, myModel), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> destroy(@PathVariable Long id) {
        return new ResponseEntity<>(service.delete(id), HttpStatus.OK);
    }
}
