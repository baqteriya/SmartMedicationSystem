package com.riya.smartmedication.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Service;

import com.riya.smartmedication.entity.Medicine;
import com.riya.smartmedication.repository.MedicineRepository;

@Service
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public MedicineService(MedicineRepository medicineRepository) {
        this.medicineRepository = medicineRepository;
    }

    public Medicine addMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getMedicinesByUser(int userId) {
        return medicineRepository.findByUserId(userId);
    }

    public void deleteMedicine(int id) {
        medicineRepository.deleteById(id);
    }

    public Medicine getMedicineById(int id) {
        return medicineRepository.findById(id).orElse(null);
    }

    public Medicine updateMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    public List<Medicine> getTodaysMedicines(int userId) {

        LocalDate today = LocalDate.now();

        return medicineRepository
                .findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
                        userId,
                        today,
                        today
                );
    }

    public String getMedicineStatus(
            Medicine medicine,
            boolean taken) {

        if (taken) {
            return "TAKEN";
        }

        LocalTime now = LocalTime.now();

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("HH:mm");

        LocalTime medicineTime =
                LocalTime.parse(
                        medicine.getTime(),
                        formatter
                );

        System.out.println(
                "Medicine: " +
                medicine.getMedicineName() +
                " | Medicine Time: " +
                medicineTime +
                " | Current Time: " +
                now
        );

        if (now.isBefore(medicineTime)) {
            return "UPCOMING";
        }

        return "MISSED";
    }
}