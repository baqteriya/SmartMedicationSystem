package com.riya.smartmedication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riya.smartmedication.entity.MedicationLog;

public interface MedicationLogRepository
        extends JpaRepository<MedicationLog, Integer> {

    List<MedicationLog> findByUserId(int userId);

    List<MedicationLog> findByUserIdAndTakenDate(
            int userId,
            LocalDate takenDate
    );

    boolean existsByUserIdAndMedicineIdAndTakenDate(
            int userId,
            int medicineId,
            LocalDate takenDate
    );
}