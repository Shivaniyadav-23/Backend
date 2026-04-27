package com.payroll.backend.dto;

import com.payroll.backend.enums.AttendanceStatus;

import java.time.LocalDate;

public class AttendanceDTO {

    private Long employeeId;
    private LocalDate date;
    private AttendanceStatus status;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public AttendanceStatus getStatus() {
        return status;
    }

    public void setStatus(AttendanceStatus status) {
        this.status = status;
    }
}
