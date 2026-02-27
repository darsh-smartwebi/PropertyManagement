package com.example.PropertyManagement.service;

import com.example.PropertyManagement.entity.Invoice;
import com.example.PropertyManagement.repository.InvoiceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public Invoice create(Invoice invoice) {
        recomputeTotal(invoice);
        return invoiceRepository.save(invoice);
    }

    public Invoice getById(Long id) {
        return invoiceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Invoice not found"));
    }

    public Invoice update(Long id, Invoice req) {
        Invoice inv = getById(id);

        if (req.getInvoiceNo() != null) inv.setInvoiceNo(req.getInvoiceNo());
        if (req.getInvoiceMonth() != null) inv.setInvoiceMonth(req.getInvoiceMonth());
        if (req.getInvoiceYear() != null) inv.setInvoiceYear(req.getInvoiceYear());
        if (req.getBaseRent() != null) inv.setBaseRent(nz(req.getBaseRent()));
        if (req.getLateFee() != null) inv.setLateFee(nz(req.getLateFee()));
        if (req.getDiscount() != null) inv.setDiscount(nz(req.getDiscount()));
        if (req.getStatus() != null) inv.setStatus(req.getStatus());
        if (req.getTenantId() != null) inv.setTenantId(req.getTenantId());

        recomputeTotal(inv);
        return invoiceRepository.save(inv);
    }

    public void delete(Long id) {
        if (!invoiceRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Invoice not found");
        }
        invoiceRepository.deleteById(id);
    }

    public List<InvoiceRepository.InvoiceRowView> listForTable() {
        return invoiceRepository.fetchInvoiceTable();
    }

    private void recomputeTotal(Invoice inv) {
        BigDecimal total = nz(inv.getBaseRent()).add(nz(inv.getLateFee())).subtract(nz(inv.getDiscount()));
        inv.setTotalRent(total.max(BigDecimal.ZERO));
    }

    private BigDecimal nz(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}