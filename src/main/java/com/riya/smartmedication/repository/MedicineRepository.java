package com.riya.smartmedication.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.riya.smartmedication.entity.Medicine;

public interface MedicineRepository
        extends JpaRepository<Medicine, Integer> {

    List<Medicine> findByUserId(int userId);


    List<Medicine>
    findByUserIdAndStartDateLessThanEqualAndEndDateGreaterThanEqual(
            int userId,
            LocalDate startDate,
            LocalDate endDate
    );
}