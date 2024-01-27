package com.nhd.management.services.employee;

import java.util.List;
import com.nhd.management.models.Employee;

public interface IEmployeeService {
  List<Employee> findAll();

  Employee findById(long theId);

  void save(Employee theEmployee);

  void deteleById(long theId);
}
