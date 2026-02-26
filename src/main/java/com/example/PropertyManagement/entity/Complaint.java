package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaints")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Complaint {

    public enum Priority { Low, Medium, High, Urgent }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "complaint_category", nullable = false, length = 80)
    private String complaintCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "complaint_priority", nullable = false)
    @Builder.Default
    private Priority complaintPriority = Priority.Medium;

    @Lob
    @Column(name = "complaint_description", nullable = false)
    private String complaintDescription;

    // FK: complaints.property_id -> properties.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "property_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Property property;

    // FK: complaints.tenant_id -> tenants.id (nullable)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Tenant tenant;
}
