package com.example.PropertyManagement.controller;

import com.example.PropertyManagement.entity.Invoice;
import com.example.PropertyManagement.repository.InvoiceRepository;
import com.example.PropertyManagement.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/create")
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.create(invoice));
    }

    @GetMapping("/getById")
    public ResponseEntity<Invoice> getById(@RequestParam Long id) {
        return ResponseEntity.ok(invoiceService.getById(id));
    }

    @PutMapping("/update")
    public ResponseEntity<Invoice> update(@RequestParam Long id,
                                          @RequestBody Invoice invoice) {
        return ResponseEntity.ok(invoiceService.update(id, invoice));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete(@RequestParam Long id) {
        invoiceService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<InvoiceRepository.InvoiceRowView>> listForTable() {
        return ResponseEntity.ok(invoiceService.listForTable());
    }
}