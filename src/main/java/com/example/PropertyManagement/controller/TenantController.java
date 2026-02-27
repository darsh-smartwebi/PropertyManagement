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

    @PostMapping("/create")
    public Tenant create(@RequestBody Tenant tenant) {
        return tenantService.createTenant(tenant);
    }

    @GetMapping("/getById")
    public Tenant getById(@RequestParam Long id) {
        return tenantService.getTenantById(id);
    }

    @GetMapping("/getAll")
    public List<Tenant> getAll(
            @RequestParam(required = false) String search) {

        return tenantService.getAll(search);
    }

    @PutMapping("/update")
    public Tenant update(@RequestParam Long id,
                         @RequestBody Tenant tenant) {
        return tenantService.updateTenant(id, tenant);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam Long id) {
        tenantService.deleteTenant(id);
    }
}