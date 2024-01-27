package com.nhd.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.nhd.management.models.Employee;

@Repository
public interface IEmployeeRepository extends JpaRepository<Employee, Long> {

}
