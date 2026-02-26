package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "invoices",
        uniqueConstraints = @UniqueConstraint(
                name = "uq_invoice_period",
                columnNames = {"tenant_id", "invoice_month", "invoice_year"}
        )
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_month", nullable = false)
    private Integer invoiceMonth; // 1..12

    @Column(name = "invoice_year", nullable = false)
    private Integer invoiceYear;

    @Column(name = "base_rent", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal baseRent = BigDecimal.ZERO;

    @Column(name = "late_fee", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal lateFee = BigDecimal.ZERO;

    @Column(name = "discount", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name = "total_rent", nullable = false, precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal totalRent = BigDecimal.ZERO;

    // FK: invoices.tenant_id -> tenants.id
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tenant_id", nullable = false)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Tenant tenant;
}