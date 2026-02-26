package com.example.PropertyManagement.controller;

import com.example.PropertyManagement.entity.Tenant;
import com.example.PropertyManagement.service.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tenants")
@RequiredArgsConstructor
public class TenantController {

    private final TenantService tenantService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tenant create(@RequestBody Tenant tenant) {
        return tenantService.create(tenant);
    }

    @GetMapping("/by-id")
    public Tenant getById(@RequestParam Long id) {
        return tenantService.getById(id);
    }

    @GetMapping
    public List<Tenant> getAll(
            @RequestParam(required = false) String search) {

        return tenantService.getAll(search);
    }

    @PutMapping
    public Tenant update(@RequestParam Long id,
                         @RequestBody Tenant tenant) {
        return tenantService.update(id, tenant);
    }

    @DeleteMapping
    public void delete(@RequestParam Long id) {
        tenantService.delete(id);
    }
}