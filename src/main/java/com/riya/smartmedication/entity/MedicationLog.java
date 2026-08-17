package com.riya.smartmedication.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MedicationLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;

    private int medicineId;

    private LocalDate takenDate;

    private LocalTime takenTime;

    private String status;

    public MedicationLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDate getTakenDate() {
        return takenDate;
    }

    public void setTakenDate(LocalDate takenDate) {
        this.takenDate = takenDate;
    }

    public LocalTime getTakenTime() {
        return takenTime;
    }

    public void setTakenTime(LocalTime takenTime) {
        this.takenTime = takenTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}