package com.vidhi.erp_saas.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @GetMapping("/data")
    @PreAuthorize("hasAnyRole('ADMIN','STAFF')")
    public String staffData() {
        return "Staff Access";
    }
}
