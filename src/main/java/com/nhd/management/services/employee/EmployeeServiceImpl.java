package com.nhd.management.services.employee;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.nhd.management.models.Employee;
import com.nhd.management.repositories.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

  private IEmployeeRepository employeeRepository;

  public EmployeeServiceImpl(IEmployeeRepository theEmployeeRepository) {
    employeeRepository = theEmployeeRepository;
  }

  /**
   * List employee
   */
  @Override
  public List<Employee> findAll() {
    return employeeRepository.findAll();
  }

  /**
   * Find Id by employee
   */
  @Override
  public Employee findById(long theId) {
    Optional<Employee> result = employeeRepository.findById(theId);

    Employee theEmployee = null;

    if (result.isPresent()) {
      theEmployee = result.get();
    }
    return theEmployee;
  }

  /**
   * Create Employee
   */
  @Override
  public void save(Employee theEmployee) {
    employeeRepository.save(theEmployee);
  }

  /**
   * Delete employee by id
   */
  @Override
  public void deteleById(long theId) {
    employeeRepository.deleteById(theId);
  }
}
