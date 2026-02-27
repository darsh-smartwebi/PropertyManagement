package com.example.PropertyManagement.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(
        name = "invoices"
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Invoice {

    public enum InvoiceStatus {
        PAID,
        UNPAID,
        SENT
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "invoice_no", unique = true)
    private String invoiceNo;

    @Column(name = "invoice_month")
    private Integer invoiceMonth;

    @Column(name = "invoice_year")
    private Integer invoiceYear;

    @Column(name="base_rent", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal baseRent = BigDecimal.ZERO;

    @Column(name="late_fee", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal lateFee = BigDecimal.ZERO;

    @Column(name="discount", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(name="total_rent", precision = 10, scale = 2)
    @Builder.Default
    private BigDecimal totalRent = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @Builder.Default
    private InvoiceStatus status = InvoiceStatus.SENT;

    @Column(name = "tenant_id")
    private Long tenantId;
}