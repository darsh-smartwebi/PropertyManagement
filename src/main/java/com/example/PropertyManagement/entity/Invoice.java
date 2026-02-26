package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "invoices")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_no", unique = true)
    private String invoiceNo;

    @Column(name = "invoice_month", unique = true)
    private Integer invoiceMonth;

    @Column(name = "invoice_year", unique = true)
    private Integer invoiceYear;

    @Column(name="base_rent", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal baseRent = BigDecimal.ZERO;

    @Column(name="late_fee",precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal lateFee = BigDecimal.ZERO;

    @Column(name="discount",precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name="total_rent",precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal totalRent = BigDecimal.ZERO;

    private Long tenantId;
}