package com.riya.smartmedication.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riya.smartmedication.service.MedicationLogService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicationLogController {

    private final MedicationLogService medicationLogService;

    public MedicationLogController(MedicationLogService medicationLogService) {
        this.medicationLogService = medicationLogService;
    }

    @GetMapping("/mark-taken")
    public String markAsTaken(
            @RequestParam int medicineId,
            HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        int userId = (int) session.getAttribute("userId");

        medicationLogService.markAsTaken(userId, medicineId);

        return "redirect:/dashboard";
    }
}