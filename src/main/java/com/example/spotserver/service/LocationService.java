package com.example.spotserver.service;

import com.example.spotserver.domain.Location;
import com.example.spotserver.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public List<Location> getLocations(Double latitude, Double longitude) {
        return locationRepository.findByLatitudeBetweenAndLongitudeBetween(latitude-0.01, latitude+0.01, longitude-0.01, longitude+0.01);
    }

    public Location addLocation(Location location) {
        Location saveLocation = locationRepository.save(location);
        return saveLocation;
    }

}
