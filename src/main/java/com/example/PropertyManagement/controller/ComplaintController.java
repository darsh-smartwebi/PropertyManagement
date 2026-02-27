package com.example.PropertyManagement.controller;

import com.example.PropertyManagement.entity.Complaint;
import com.example.PropertyManagement.service.ComplaintService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/complaints")
@RequiredArgsConstructor
public class ComplaintController {

    @Autowired
    private ComplaintService service;

    @GetMapping("/getAll")
    public List<Complaint> getAll(
            @RequestParam(required = false) String search
    ) {
        return service.getAll(search);
    }

    @GetMapping("/getById")
    public Complaint getById(
            @RequestParam Long id
    ) {
        return service.getComplaintById(id);
    }

    @PostMapping("/create")
    public Complaint create(
            @RequestBody Complaint complaint
    ) {
        return service.createComplaint(complaint);
    }

    @PutMapping("/update")
    public Complaint update(
            @RequestParam Long id,
            @RequestBody Complaint complaint
    ) {
        return service.updateComplaint(id, complaint);
    }

    @DeleteMapping("/delete")
    public String delete(
            @RequestParam Long id
    ) {
        service.deleteComplaint(id);
        return "Complaint deleted successfully";
    }
}