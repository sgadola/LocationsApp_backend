package com.modul_151.location_app.services;

import com.modul_151.location_app.entities.Location;
import com.modul_151.location_app.entities.User;
import com.modul_151.location_app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private static long idCounter = 0;


    public List<User> findAll() {
        return userRepository.findAll();
    }


    public User findById(long id) {
        if (userRepository.findById(id).isPresent())
            return userRepository.findById(id).get();
        else
            return null;
    }


    public User save(User user) {
        long id = user.getId();

        if (id == -1)
            user.setId(++idCounter);
        else
            deleteById(id);

        userRepository.save(user);

        return user;
    }


    public void deleteById(long id) {
        User user = findById(id);

        if (user != null)
            userRepository.deleteById(id);
    }
}