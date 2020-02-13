package com.modul_151.location_app.controllers;

import com.modul_151.location_app.entities.User;
import com.modul_151.location_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@RequestMapping("/api")
@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAll();
    }


    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id) {
        if (userService.findById(id) != null)
            return userService.findById(id);
        else
            return null;
    }


    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username, @PathVariable long id) {
        if (userService.findById(id) != null) {
            userService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User user) {
        if (userService.findById(id) != null) {
            User userUpdated = userService.save(user);

            return new ResponseEntity<>(userUpdated, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/user/new")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User createdUser = userService.save(user);

        // Location
        // Get current ressource URL => {id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdUser.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}