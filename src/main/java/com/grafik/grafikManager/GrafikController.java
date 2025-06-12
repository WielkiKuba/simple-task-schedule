package com.grafik.grafikManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.metadata.HsqlTableMetaDataProvider;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.time.*;

@RestController
public class GrafikController {

    private final UserService userService;
    private final WorkService workService;

    @Autowired
    public GrafikController(UserService userService,WorkService workService){this.userService = userService;this.workService = workService;}

    @GetMapping("/user/add/{name}")
    public ResponseEntity<Void> addUser(@PathVariable String name){
        try{
            userService.addUser(name);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<List<User>> getUser(){
        return ResponseEntity.ok(userService.getAll());
    }
    @GetMapping("/user/del/{id}")
    public ResponseEntity<Void> delUser(@PathVariable Long id){
        try{
            User userTemp = userService.getById(id).get();
            userService.delUser(userTemp);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/work/add/{ownerId}/{timeStart}/{timeEnd}")
    public ResponseEntity<Void> addWork(@PathVariable Long ownerId, @PathVariable LocalDateTime timeStart,@PathVariable LocalDateTime timeEnd){
        try{
            User owner = userService.getById(ownerId).get();
            workService.addWork(timeStart,timeEnd,owner);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(null);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/work/del/{id}")
    public ResponseEntity<Void> delWork(@PathVariable Long id){
        try{
            Work workTemp =  workService.getById(id).get();
            workService.delWork(workTemp);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping("/work/id/{ownerId}")
    public ResponseEntity<List<Work>> getWork(@PathVariable Long ownerId){
        try{
            return ResponseEntity.ok(workService.getByOwner(userService.getById(ownerId).get()));
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @PostMapping("/work/date/{date}")
    public ResponseEntity<List<Work>> getWork(@PathVariable LocalDate date){
        try{
            return ResponseEntity.ok(workService.getAllInDay(date));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    @GetMapping("/work")
    public ResponseEntity<List<Work>> getWork(){return ResponseEntity.ok(workService.getAll());}
}
