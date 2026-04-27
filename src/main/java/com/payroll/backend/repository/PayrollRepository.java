package com.payroll.backend.repository;

import com.payroll.backend.model.Employee;
import com.payroll.backend.model.Payroll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PayrollRepository extends JpaRepository<Payroll,Long> {
    List<Payroll> findByEmployee(Employee employee);

    //if payrOll already  generated OR NOT for month
    Optional<Payroll> findByEmployeeAndMonth(Employee employee, String month);
}
