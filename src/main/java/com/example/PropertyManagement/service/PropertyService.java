package com.example.PropertyManagement.service;

import com.example.PropertyManagement.entity.Property;
import com.example.PropertyManagement.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import tools.jackson.databind.annotation.JsonAppend;

import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {
    @Autowired
    private PropertyRepository propertyRepository;

    public Property createProperty(Property property){
        return propertyRepository.save(property);
    }

    public List<Property> getProperties(String search){
        if (search != null && !search.isEmpty() && !search.equals("null")) {
            return propertyRepository.searchProperty(search);
        }
        return propertyRepository.findAllByOrderByIdDesc();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepository.findById(id);
    }

    public Property updateProperty(long id,Property p){
        Property property=propertyRepository.findById(id).get();

        property.setPropertyName(p.getPropertyName());
        property.setPropertyAddress(p.getPropertyAddress());
        property.setPropertyType(p.getPropertyType());
        property.setNoOfRooms(p.getNoOfRooms());
        property.setMonthlyRent(p.getMonthlyRent());
        property.setFloorArea(p.getFloorArea());
        property.setPropertyNotes(p.getPropertyNotes());
        property.setStatus(p.getStatus());

        return propertyRepository.save(property);
    }

    public ResponseEntity<String> deleteProperty(long id){
        propertyRepository.deleteById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("Property Deleted Successfully");
    }
}
