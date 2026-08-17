package com.riya.smartmedication.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.riya.smartmedication.entity.MedicationLog;
import com.riya.smartmedication.entity.Medicine;
import com.riya.smartmedication.repository.MedicationLogRepository;

@Service
public class MedicationLogService {

    private final MedicationLogRepository medicationLogRepository;

    public MedicationLogService(MedicationLogRepository medicationLogRepository) {
        this.medicationLogRepository = medicationLogRepository;
    }

    public MedicationLog markAsTaken(
            int userId,
            int medicineId) {

        LocalDate today = LocalDate.now();

        boolean alreadyTaken =
                medicationLogRepository
                        .existsByUserIdAndMedicineIdAndTakenDate(
                                userId,
                                medicineId,
                                today
                        );

        if (alreadyTaken) {
            return null;
        }

        MedicationLog log = new MedicationLog();

        log.setUserId(userId);
        log.setMedicineId(medicineId);
        log.setTakenDate(today);
        log.setTakenTime(LocalTime.now());
        log.setStatus("TAKEN");

        return medicationLogRepository.save(log);
    }

    public List<MedicationLog> getTodayLogs(int userId) {

        return medicationLogRepository.findByUserIdAndTakenDate(
                userId,
                LocalDate.now()
        );
    }

    public String getMedicineStatus(
            Medicine medicine,
            boolean taken) {

        if (taken) {
            return "TAKEN";
        }

        LocalTime now = LocalTime.now();

        LocalTime medicineTime =
                LocalTime.parse(medicine.getTime());

        if (now.isBefore(medicineTime)) {
            return "UPCOMING";
        }

        return "MISSED";
    }
}