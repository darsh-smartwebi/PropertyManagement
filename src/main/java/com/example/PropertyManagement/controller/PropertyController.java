package com.example.PropertyManagement.controller;


import com.example.PropertyManagement.entity.Property;
import com.example.PropertyManagement.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/property")
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @PostMapping("/create")
    public Property createProperty(@RequestBody Property p){
        return propertyService.createProperty(p);
    }

    @GetMapping("/getAll")
    public List<Property> getProperties(@RequestParam(required = false) String search){
        return propertyService.getProperties(search);
    }

    @GetMapping("/getById")
    public ResponseEntity<Property> getPropertyById(@RequestParam Long id) {
        return propertyService.getPropertyById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/update")
    public Property updateProperty(@RequestParam long id,@RequestBody Property p){
        return propertyService.updateProperty(id,p);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteProperty(@RequestParam long id){
        return propertyService.deleteProperty(id);
    }
}
