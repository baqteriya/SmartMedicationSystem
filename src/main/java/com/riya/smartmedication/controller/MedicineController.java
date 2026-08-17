package com.riya.smartmedication.controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.riya.smartmedication.entity.Medicine;
import com.riya.smartmedication.service.MedicineService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MedicineController {

    private final MedicineService medicineService;

    public MedicineController(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    @GetMapping("/add-medicine")
    public String showAddMedicineForm(HttpSession session, Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        model.addAttribute("medicine", new Medicine());

        return "add-medicine";
    }

    @PostMapping("/add-medicine")
    public String addMedicine(
            @ModelAttribute("medicine") Medicine medicine,
            HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        int userId = (int) session.getAttribute("userId");

        medicine.setUserId(userId);

        medicineService.addMedicine(medicine);

        return "redirect:/medicines";
    }
    @GetMapping("/medicines")
    public String viewMedicines(HttpSession session, Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        int userId = (int) session.getAttribute("userId");

        model.addAttribute(
            "medicines",
            medicineService.getMedicinesByUser(userId)
        );

        return "medicines";
    }
    @GetMapping("/delete-medicine")
    public String deleteMedicine(
            @RequestParam int id,
            HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        medicineService.deleteMedicine(id);

        return "redirect:/medicines";
    }
    @GetMapping("/edit-medicine")
    public String showEditMedicineForm(
            @RequestParam int id,
            HttpSession session,
            Model model) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        Medicine medicine = medicineService.getMedicineById(id);

        model.addAttribute("medicine", medicine);

        return "edit-medicine";
    }
    @PostMapping("/edit-medicine")
    public String updateMedicine(
            @ModelAttribute("medicine") Medicine medicine,
            HttpSession session) {

        if (session.getAttribute("userId") == null) {
            return "redirect:/login";
        }

        int userId = (int) session.getAttribute("userId");

        medicine.setUserId(userId);

        medicineService.updateMedicine(medicine);

        return "redirect:/medicines";
    }
}