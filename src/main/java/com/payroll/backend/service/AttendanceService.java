package com.payroll.backend.service;

import com.payroll.backend.exception.ResourceNotFoundException;
import com.payroll.backend.model.Attendance;
import com.payroll.backend.model.Employee;
import com.payroll.backend.repository.AttendanceRepository;
import com.payroll.backend.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;


    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Attendance markAttendance(Attendance attendance){
        Employee employee = employeeRepository.findById(
                attendance.getEmployee().getId())
                .orElseThrow(()-> new ResourceNotFoundException("Not Found"));

        boolean exists = attendanceRepository.existsByEmployeeAndDate(
                employee,attendance.getDate()
        );

        if(exists){
            throw new RuntimeException("Already marked for this date");
        }
        attendance.setEmployee(employee);
        return attendanceRepository.save(attendance);
    }

    public List<Attendance> getAttendanceByEmployee(Long emp_id){
        Employee employee = employeeRepository.findById(emp_id)
                .orElseThrow(()-> new ResourceNotFoundException("Not found"));
        return  attendanceRepository.findByEmployee(employee);
    }

}
