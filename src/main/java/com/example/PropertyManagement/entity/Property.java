package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "properties")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Property {

    public enum Status { Occupied, Vacant, Maintenance }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "property_name", nullable = false, length = 150)
    private String propertyName;

    @Column(name = "property_address", nullable = false, length = 255)
    private String propertyAddress;

    @Column(name = "property_type", nullable = false, length = 50)
    private String propertyType;

    @Column(name = "no_of_rooms")
    private Integer noOfRooms;

    @Column(name = "monthly_rent", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal monthlyRent = BigDecimal.ZERO;

    @Column(name = "floor_area")
    private Integer floorArea;

    @Lob
    @Column(name = "property_notes")
    private String propertyNotes;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @Builder.Default
    private Status status = Status.Vacant;
}