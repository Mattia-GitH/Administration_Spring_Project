package com.project.Administration.controller;

import com.project.Administration.model.UserModel;
import com.project.Administration.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/create")
    public ResponseEntity<UserModel> create(@RequestBody UserModel userModel){
        return ResponseEntity.ok().body(userService.createUser(userModel));
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
