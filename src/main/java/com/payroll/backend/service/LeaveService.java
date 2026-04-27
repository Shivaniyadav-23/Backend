package com.payroll.backend.service;

import com.payroll.backend.enums.LeaveStatus;
import com.payroll.backend.exception.ResourceNotFoundException;
import com.payroll.backend.model.Employee;
import com.payroll.backend.model.LeaveRequest;
import com.payroll.backend.repository.EmployeeRepository;
import com.payroll.backend.repository.LeaveRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public LeaveRequest applyLeave(LeaveRequest leaveRequest){
        Employee employee = employeeRepository.findById(
                leaveRequest.getEmployee().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        leaveRequest.setEmployee(employee);
        leaveRequest.setStatus(LeaveStatus.PENDING);
        return leaveRepository.save(leaveRequest);
    }

    public LeaveRequest approveLeave(Long id){
        LeaveRequest leave = leaveRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Leave not found"));

        leave.setStatus(LeaveStatus.APPROVED);
        return leaveRepository.save(leave);
    }

    public LeaveRequest rejectLeave(Long id){
        LeaveRequest leave = leaveRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Leave NOt found"));

        leave.setStatus(LeaveStatus.REJECTED);
        return leaveRepository.save(leave);
    }

    public List<LeaveRequest> getLeavesByEmployee(Long emp_id){
        Employee employee = employeeRepository.findById(emp_id).orElseThrow(()->new ResourceNotFoundException("Employee Not Found"));

        return leaveRepository.findByEmployee(employee);

    }
    public List<LeaveRequest> getPendingLeaves() {
        return leaveRepository.findByStatus(LeaveStatus.PENDING);
    }
}
