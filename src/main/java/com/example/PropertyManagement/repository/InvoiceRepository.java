package com.example.PropertyManagement.repository;

import com.example.PropertyManagement.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    interface InvoiceRowView {
        String getInvoiceNo();
        Integer getInvoiceMonth();
        Integer getInvoiceYear();
        String getTenantName();
        BigDecimal getTotalRent();
        String getStatus();
        Boolean getCanResend(); // for "Resend" button
    }

    // Adjust table/column names if your tenant table differs
    @Query("""
        select 
            i.invoiceNo as invoiceNo,
            i.invoiceMonth as invoiceMonth,
            i.invoiceYear as invoiceYear,
            t.fullName as tenantName,
            i.totalRent as totalRent,
            cast(i.status as string) as status,
            case when i.status in ('UNPAID','SENT') then true else false end as canResend
        from Invoice i
        left join Tenant t on t.id = i.tenantId
        order by i.invoiceYear desc, i.invoiceMonth desc, i.id desc
    """)
    List<InvoiceRowView> fetchInvoiceTable();
}
