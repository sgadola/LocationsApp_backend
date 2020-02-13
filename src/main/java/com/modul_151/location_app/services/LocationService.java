package com.modul_151.location_app.services;

import com.modul_151.location_app.entities.Location;
import com.modul_151.location_app.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin("http://localhost:4200")
@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    private static long idCounter = 0;


//    static {
//        toDos.add(new Location(++idCounter, "Simon Gadola", "Learn to Dance", new Date(), false));
//        toDos.add(new Location(++idCounter, "Simon Gadola", "Learn about microservices", new Date(), false));
//        toDos.add(new Location(++idCounter, "Simon Gadola", "Learn about Angular", new Date(), false));
//    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }


    public List<Location> findAllByUsername(String username) {
        List<Location> locations = locationRepository.findAll();
        List<Location> newLocations = new ArrayList<>();

        for (Location location : locations)
            if (location.getUser().getUsername() == username) {
                newLocations.add(location);
            }

        return newLocations;
    }

    public Location findById(long id) {
        if (locationRepository.findById(id).isPresent())
            return locationRepository.findById(id).get();
        else
            return null;
    }


    public Location save(Location location) {
        long id = location.getId();

        if (id == -1)
            location.setId(++idCounter);
        else
            deleteById(id);

        locationRepository.save(location);

        return location;
    }


    public void deleteById(long id) {
        Location location = findById(id);

        if (location != null)
            locationRepository.deleteById(id);
    }
}