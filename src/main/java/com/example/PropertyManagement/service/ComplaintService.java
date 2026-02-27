package com.example.PropertyManagement.service;

import com.example.PropertyManagement.entity.Complaint;
import com.example.PropertyManagement.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComplaintService {

    @Autowired
    private ComplaintRepository repo;

    public List<Complaint> getAll(String search) {
        if (search != null && !search.isBlank()) {
            return repo.searchAllFields(search);
        }

        return repo.findAll();
    }

    public Complaint getComplaintById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Complaint not found: " + id));
    }

    public Complaint createComplaint(Complaint c) {
        c.setId(null);
        return repo.save(c);
    }

    public Complaint updateComplaint(Long id, Complaint c) {
        Complaint existing = getComplaintById(id);

        existing.setComplaintCategory(c.getComplaintCategory());
        existing.setComplaintPriority(c.getComplaintPriority());
        existing.setComplaintDescription(c.getComplaintDescription());
        existing.setPropertyName(c.getPropertyName());
        existing.setTenantName(c.getTenantName());

        return repo.save(existing);
    }

    public void deleteComplaint(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Complaint not found: " + id);
        }
        repo.deleteById(id);
    }
}
