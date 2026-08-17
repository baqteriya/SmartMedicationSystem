package com.riya.smartmedication.service;

import java.time.LocalTime;

import org.springframework.stereotype.Service;

import com.riya.smartmedication.entity.Medicine;

@Service
public class ReminderService {

    public boolean isMedicineDue(Medicine medicine) {

        LocalTime now = LocalTime.now();

        LocalTime medicineTime =
                LocalTime.parse(medicine.getTime());

        return !now.isBefore(medicineTime);
    }
}