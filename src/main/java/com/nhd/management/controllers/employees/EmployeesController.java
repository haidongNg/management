package com.nhd.management.controllers.employees;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.nhd.management.services.employee.IEmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeesController {
  private final IEmployeeService employeeService;
  
  public EmployeesController(IEmployeeService theEmployeeService) {
    employeeService = theEmployeeService;
  }

  @GetMapping("")
  public String index(Model theModel) {
    
    return "pages/employees/index.html";
  }

}
