package com.payroll.backend.controller;

import com.payroll.backend.model.LeaveRequest;
import com.payroll.backend.service.LeaveService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/leaves")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }
    @PostMapping("/apply")
    public LeaveRequest applyLeave(@RequestBody LeaveRequest leaveRequest){
        return leaveService.applyLeave(leaveRequest);
    }

    @PutMapping("/{id}/approve")
    public LeaveRequest approveLeave(@PathVariable Long id) {
        return leaveService.approveLeave(id);
    }
    @GetMapping("/pending")
    public List<LeaveRequest> getPendingLeaves() {
        return leaveService.getPendingLeaves();
    }


    @PutMapping("/{id}/reject")
    public LeaveRequest rejectLeave(@PathVariable Long id) {
        return leaveService.rejectLeave(id);
    }


    @GetMapping("/{employeeId}")
    public List<LeaveRequest> getLeavesByEmployee(@PathVariable Long employeeId) {
        return leaveService.getLeavesByEmployee(employeeId);
    }
}
