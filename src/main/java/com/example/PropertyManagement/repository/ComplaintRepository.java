package com.example.PropertyManagement.repository;

import com.example.PropertyManagement.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint,Long> {
    @Query("""
    SELECT c FROM Complaint c
    WHERE 
        LOWER(c.complaintCategory) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(c.complaintDescription) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(c.complaintPriority) LIKE LOWER(CONCAT('%', :search, '%'))
""")
    List<Complaint> searchAllFields(@Param("search") String search);
}
