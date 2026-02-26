package com.example.PropertyManagement.repository;

import com.example.PropertyManagement.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property,Long> {
    List<Property> findAllByOrderByIdDesc();
    @Query("SELECT p FROM Property p WHERE " +
            "LOWER(p.propertyName) LIKE LOWER(CONCAT('%', :search, '%')) " +
            "ORDER BY p.id DESC")

    List<Property> searchProperty(@Param("search") String search);
}
