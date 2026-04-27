package com.payroll.backend.repository;

import com.payroll.backend.enums.AttendanceStatus;
import com.payroll.backend.model.Attendance;
import com.payroll.backend.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    // Get all attendance of an employee
    List<Attendance> findByEmployee(Employee employee);

    // Get attendance between dates (for payroll)
    List<Attendance> findByEmployeeAndDateBetween(Employee employee, LocalDate start, LocalDate end);

    // Count present days
    long countByEmployeeAndStatusAndDateBetween(
            Employee employee,
            AttendanceStatus status,
            LocalDate start,
            LocalDate end
    );

    // Check if attendance already marked for a day
    boolean existsByEmployeeAndDate(Employee employee, LocalDate date);
}
