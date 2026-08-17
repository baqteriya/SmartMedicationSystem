package com.riya.smartmedication.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.riya.smartmedication.entity.MedicationLog;
import com.riya.smartmedication.entity.Medicine;
import com.riya.smartmedication.service.MedicationLogService;
import com.riya.smartmedication.service.MedicineService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicationHistoryController {

    private final MedicationLogService medicationLogService;
    private final MedicineService medicineService;

    public MedicationHistoryController(
            MedicationLogService medicationLogService,
            MedicineService medicineService) {

        this.medicationLogService = medicationLogService;
        this.medicineService = medicineService;
    }

    @GetMapping("/medication-history")
    public String medicationHistory(
            HttpSession session,
            Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        int userId = (int) session.getAttribute("userId");

        List<MedicationLog> logs =
                medicationLogService.getTodayLogs(userId);

        List<Medicine> medicines =
                medicineService.getMedicinesByUser(userId);

        model.addAttribute("logs", logs);
        model.addAttribute("medicines", medicines);

        return "medication-history";
    }
}