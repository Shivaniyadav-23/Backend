package com.payroll.backend.controller;

import com.payroll.backend.model.Employee;
import com.payroll.backend.model.Payroll;
import com.payroll.backend.service.PayrollService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/payroll")
public class PayrollController {

    private final PayrollService payrollService;

    public PayrollController(PayrollService payrollService) {
        this.payrollService = payrollService;
    }

    @GetMapping("/generate/{employeeId}")
    public Object generatePayroll(@PathVariable Long employeeId, @RequestParam String month){
        return payrollService.generatePayroll(employeeId,month);

    }

}
