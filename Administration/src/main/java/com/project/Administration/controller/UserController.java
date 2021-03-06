package com.project.Administration.controller;

import com.project.Administration.model.UserModel;
import com.project.Administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> users(){
        return ResponseEntity.ok().body(userService.listUsers());
    }

    @GetMapping("/user/id={id}")
    public ResponseEntity<UserModel> userById(@PathVariable Long id){
        return ResponseEntity.ok().body(userService.userById(id));
    }

    @GetMapping("/users/admin={bool}")
    public ResponseEntity<List<UserModel>> listAdminIsBool(@PathVariable boolean bool){
        return ResponseEntity.ok().body(userService.whereAdminIsBool(bool));
    }

    @GetMapping("/users/registration-after/{date}")
    public ResponseEntity<List<UserModel>> usersDateAfter(@PathVariable Date date){
        return ResponseEntity.ok().body(userService.dateAfter(date));
    }

    @GetMapping("/users/age={age}")
    public ResponseEntity<List<UserModel>> ageGreaterThanEqual(@PathVariable int age){
        return ResponseEntity.ok().body(userService.ageGreaterThanEqual(age));
    }

    @GetMapping("/users/order-by-surname")
    public ResponseEntity<List<UserModel>> orderBySurname(){
        return ResponseEntity.ok().body(userService.orderBySurname());
    }

    @PostMapping("/create")
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userModel));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel userModel){
        return ResponseEntity.ok().body(userService.updateUser(userModel, id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id){
        return ResponseEntity.ok().body(userService.delete(id));
    }
}
