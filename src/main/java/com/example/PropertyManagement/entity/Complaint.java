package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "complaint_category")
    private String complaintCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "complaint_priority")
    private Priority complaintPriority;

    @Lob
    @Column(name = "complaint_description")
    private String complaintDescription;

    @Column(name = "property_id")
    private Long propertyId;

    @Column(name = "tenant_id")
    private Long tenantId;
}
