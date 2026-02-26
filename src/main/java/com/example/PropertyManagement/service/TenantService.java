package com.example.PropertyManagement.service;

import com.example.PropertyManagement.entity.Tenant;
import com.example.PropertyManagement.repository.TenantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class TenantService {

    private final TenantRepository repo;

    public Tenant create(Tenant tenant) {

        if (tenant.getRentAmt() == null) {
            tenant.setRentAmt(BigDecimal.ZERO);
        }

        if (tenant.getSecurityDeposit() == null) {
            tenant.setSecurityDeposit(BigDecimal.ZERO);
        }

        return repo.save(tenant);
    }

    public Tenant getById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found: " + id));
    }

    public List<Tenant> getAll(String search) {

        if (search != null && !search.isBlank()) {
            return repo.searchAllFields(search);
        }

        return repo.findAll();
    }

    public Tenant update(Long id, Tenant updated) {

        Tenant existing = repo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tenant not found: " + id));

        existing.setFullName(updated.getFullName());
        existing.setEmail(updated.getEmail());
        existing.setPhoneNo(updated.getPhoneNo());
        existing.setLeaseStartDate(updated.getLeaseStartDate());
        existing.setLeaseEndDate(updated.getLeaseEndDate());
        existing.setRentAmt(updated.getRentAmt());
        existing.setSecurityDeposit(updated.getSecurityDeposit());
        existing.setTenantNotes(updated.getTenantNotes());
        existing.setLeaseStatus(updated.getLeaseStatus());
        existing.setPropertyName(updated.getPropertyName());

        return repo.save(existing);
    }

    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new EntityNotFoundException("Tenant not found: " + id);
        }
        repo.deleteById(id);
    }

}
