package com.payroll.backend.service;

import com.payroll.backend.dto.PayrollResponseDTO;
import com.payroll.backend.enums.AttendanceStatus;
import com.payroll.backend.enums.SalaryType;
import com.payroll.backend.exception.ResourceNotFoundException;
import com.payroll.backend.model.Employee;
import com.payroll.backend.model.Payroll;
import com.payroll.backend.repository.AttendanceRepository;
import com.payroll.backend.repository.EmployeeRepository;
import com.payroll.backend.repository.PayrollRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PayrollService {
    private final EmployeeRepository employeeRepository;
    private final AttendanceRepository attendanceRepository;
    private  final PayrollRepository payrollRepository;

    public PayrollService(EmployeeRepository employeeRepository, AttendanceRepository attendanceRepository, PayrollRepository payrollRepository) {
        this.employeeRepository = employeeRepository;
        this.attendanceRepository = attendanceRepository;
        this.payrollRepository = payrollRepository;
    }
    public PayrollResponseDTO generatePayroll(Long employeeId, String month) {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        LocalDate start = LocalDate.parse(month + "-01");
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        long presentDays = attendanceRepository
                .countByEmployeeAndStatusAndDateBetween(
                        employee,
                        AttendanceStatus.PRESENT,
                        start,
                        end
                );

        double salary;

        if (employee.getSalaryType() == SalaryType.MONTHLY) {
            salary = (employee.getSalaryAmount() / 30) * presentDays;
        } else {
            salary = employee.getSalaryAmount() * presentDays;
        }

        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setMonth(month);
        payroll.setPresentDays((int) presentDays);
        payroll.setTotalSalary(salary);
        payrollRepository.save(payroll);

        PayrollResponseDTO response = new PayrollResponseDTO();
        response.setEmployeeName(employee.getName());
        response.setMonth(month);
        response.setPresentDays((int) presentDays);
        response.setSalaryType(employee.getSalaryType());
        response.setBaseSalary(employee.getSalaryAmount());
        response.setCalculatedSalary(salary);

        return response;
    }
    /*public  PayrollResponseDTO  generatePayroll(Long emp_id, String month){
        Employee employee = employeeRepository.findById(emp_id).orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        LocalDate start = LocalDate.parse(month + "-01");
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());

        //counting present working day after getting start and end date

        long presentDays = attendanceRepository.countByEmployeeAndStatusAndDateBetween(
                employee,
                AttendanceStatus.PRESENT,
                start,
                end
        );
        double salary;

        if(employee.getSalaryType() == SalaryType.MONTHLY){
            salary = (employee.getSalaryAmount()/30) * presentDays;
        }else{
            salary = employee.getSalaryAmount()*presentDays;
        }
        //saving data
        Payroll payroll = new Payroll();
        payroll.setEmployee(employee);
        payroll.setMonth(month);
        payroll.setPresentDays((int)presentDays);
        payroll.setTotalSalary(salary);

        payrollRepository.save(payroll);

        PayrollResponseDTO response = new PayrollResponseDTO();
        response.setEmployeeName(employee.getName());
        response.setMonth(month);
        response.setPresentDays((int) presentDays);
        response.setSalaryType(employee.getSalaryType());
        response.setBaseSalary(employee.getSalaryAmount());
        response.setCalculatedSalary(salary);

        return response;
    }*/
}
