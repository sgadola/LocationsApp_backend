package com.modul_151.location_app.controllers;

import com.modul_151.location_app.entities.Location;
import com.modul_151.location_app.services.LocationService;
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
public class LocationController {

    @Autowired
    private LocationService locationService;


    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAll();
    }


    @GetMapping("/locations/{username}")
    public List<Location> getAllLocationsByUsername(@PathVariable String username) {
        return locationService.findAllByUsername(username);
    }


    @GetMapping("/location/{id}")
    public Location getLocation(@PathVariable long id) {
        if (locationService.findById(id) != null)
            return locationService.findById(id);
        else
            return null;
    }


    @DeleteMapping("/location/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable String username, @PathVariable long id) {
        if (locationService.findById(id) != null) {
            locationService.deleteById(id);

            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }


    @PutMapping("/location/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable long id, @RequestBody Location location) {
        if (locationService.findById(id) != null) {
            Location locationUpdated = locationService.save(location);

            return new ResponseEntity<>(locationUpdated, HttpStatus.OK);
        }

        return ResponseEntity.notFound().build();
    }


    @PostMapping("/location")
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.save(location);

        // Location
        // Get current ressource URL => {id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdLocation.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }
}