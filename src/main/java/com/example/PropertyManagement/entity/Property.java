package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @Column(name = "property_name")
    private String propertyName;

    @Column(name = "property_address")
    private String propertyAddress;

    @Column(name = "property_type")
    private String propertyType;

    @Column(name = "no_of_rooms")
    private Integer noOfRooms;

    @Column(name="monthly_rent" ,precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal monthlyRent = BigDecimal.ZERO;

    @Column(name ="floor_area" )
    private Integer floorArea;

    @Lob
    @Column(name = "property_notes")
    private String propertyNotes;

    @Enumerated(EnumType.STRING)
    @Column(name ="property_status")
    private Status status;
}