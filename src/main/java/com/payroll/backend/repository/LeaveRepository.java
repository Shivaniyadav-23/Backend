package com.payroll.backend.repository;

import com.payroll.backend.enums.LeaveStatus;
import com.payroll.backend.model.Employee;
import com.payroll.backend.model.LeaveRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest,Long> {

    List<LeaveRequest> findByEmployee(Employee employee);

    List<LeaveRequest> findByStatus(LeaveStatus status);
}
