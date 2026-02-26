package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(nullable = false, length = 150)
    private String fullname;

    @Column(length = 150, unique = true)
    private String email;

    @Column(name = "phone_no", length = 30)
    private String phoneNo;

    @Column(name = "lease_start_date")
    private LocalDate leaseStartDate;

    @Column(name = "lease_end_date")
    private LocalDate leaseEndDate;

    @Column(name = "rent_amt", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal rentAmt = BigDecimal.ZERO;

    @Column(name = "security_deposit", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal securityDeposit = BigDecimal.ZERO;

    @Lob
    @Column(name = "tenant_notes")
    private String tenantNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "lease_status", nullable = false)
    @Builder.Default
    private LeaseStatus leaseStatus = LeaseStatus.Upcoming;

    // FK: tenants.property_id -> properties.id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "property_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Property property;
}