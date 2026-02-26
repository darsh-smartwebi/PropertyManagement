package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "tenants")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Tenant {

    public enum LeaseStatus { Active, Ended, Upcoming }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_no")
    private String phoneNo;

    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;

    @Column(name = "lease_end_date")
    private LocalDate leaseEndDate;

    @Column(name="rent_amt",precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal rentAmt = BigDecimal.ZERO;

    @Column(name="security_deposit",precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal securityDeposit = BigDecimal.ZERO;

    @Lob
    @Column(name = "tenant_notes")
    private String tenantNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "lease_status")
    private LeaseStatus leaseStatus;

    @Column(name = "property_name")
    private String propertyName;
}