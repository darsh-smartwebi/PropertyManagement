package com.example.PropertyManagement.repository;

import com.example.PropertyManagement.entity.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findByLeaseStatus(Tenant.LeaseStatus leaseStatus);

    @Query("""
    SELECT t FROM Tenant t
    WHERE 
        LOWER(t.fullName) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.email) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(t.phoneNo) LIKE LOWER(CONCAT('%', :search, '%'))
        OR LOWER(CAST(t.leaseStatus AS string)) LIKE LOWER(CONCAT('%', :search, '%'))
        OR CAST(t.propertyName AS string) LIKE CONCAT('%', :search, '%')
    """)
    List<Tenant> searchAllFields(@Param("search") String search);
}
