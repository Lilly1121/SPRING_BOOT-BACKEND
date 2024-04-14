package com.springbootlab.cc1.controller;

import com.springbootlab.cc1.model.Owner;
import com.springbootlab.cc1.model.Vehicle;
import com.springbootlab.cc1.repository.OwnerRepository;
import com.springbootlab.cc1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleService;

    @Autowired
    private OwnerRepository ownerService;

    @PostMapping("/registerWithOwner")
    public ResponseEntity<?> registerVehicleWithOwner(@RequestBody Map<String, Object> vehicleOwnerMap) {
        // Extract vehicle details from the vehicleOwnerMap
        String model = (String) vehicleOwnerMap.get("model");
        String make = (String) vehicleOwnerMap.get("make");
        int year = (int) vehicleOwnerMap.get("year");
        String color = (String) vehicleOwnerMap.get("color");
        String registrationNumber = (String) vehicleOwnerMap.get("registrationNumber");

        // Create a new vehicle object
        Vehicle vehicle = new Vehicle(model, make, year, color, registrationNumber);

        // Extract owner details from the vehicleOwnerMap
        Map<String, Object> ownerMap = (Map<String, Object>) vehicleOwnerMap.get("owner");
        String ownerName = (String) ownerMap.get("name");
        String address = (String) ownerMap.get("address");
        String contactNumber = (String) ownerMap.get("contactNumber");

        // Create a new owner object
        Owner owner = new Owner(ownerName, address, contactNumber);

        // Associate the vehicle with the owner
        vehicle.setOwner(owner);

        // Register the vehicle with the owner
        Vehicle registeredVehicle = vehicleService.save(vehicle);

        if (registeredVehicle == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Error registering vehicle with owner");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(registeredVehicle);
    }

    // Other methods for vehicle management
}
