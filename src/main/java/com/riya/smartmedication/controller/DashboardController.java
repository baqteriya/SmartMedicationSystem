package com.riya.smartmedication.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.riya.smartmedication.entity.Medicine;
import com.riya.smartmedication.service.MedicationLogService;
import com.riya.smartmedication.service.MedicineService;

import jakarta.servlet.http.HttpSession;

@Controller
public class DashboardController {

    private final MedicineService medicineService;
    private final MedicationLogService medicationLogService;

    public DashboardController(
            MedicineService medicineService,
            MedicationLogService medicationLogService) {

        this.medicineService = medicineService;
        this.medicationLogService = medicationLogService;
    }


    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {


        // Check login

        if (session.getAttribute("userId") == null) {

            return "redirect:/login";
        }


        int userId =
                (int) session.getAttribute("userId");


        // User name

        model.addAttribute(
                "userName",
                session.getAttribute("userName")
        );


        // Today's medicines

        List<Medicine> medicines =
                medicineService.getTodaysMedicines(userId);


        model.addAttribute(
                "medicines",
                medicines
        );


        // Maps for status and reminder

        Map<Integer, String> medicineStatus =
                new HashMap<>();


        Map<Integer, Boolean> medicineDue =
                new HashMap<>();


        LocalTime now =
                LocalTime.now();


        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm");


        // Check every medicine

        for (Medicine medicine : medicines) {


            // Check whether medicine is already taken today

            boolean taken = medicationLogService
                    .getTodayLogs(userId)
                    .stream()
                    .anyMatch(log ->
                            log.getMedicineId() == medicine.getId()
                    );


            // Status

            String status =
                    medicineService.getMedicineStatus(
                            medicine,
                            taken
                    );


            medicineStatus.put(
                    medicine.getId(),
                    status
            );


            // Medicine time

            LocalTime medicineTime =
                    LocalTime.parse(
                            medicine.getTime(),
                            formatter
                    );


            // Due if time has arrived and medicine not taken

            boolean due =
                    !taken &&
                    !now.isBefore(medicineTime);


            medicineDue.put(
                    medicine.getId(),
                    due
            );

        }


        model.addAttribute(
                "medicineStatus",
                medicineStatus
        );


        model.addAttribute(
                "medicineDue",
                medicineDue
        );


        return "dashboard";
    }
}