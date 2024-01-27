package com.nhd.management.models;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity()
@Table(name = "employee")
public class Employee extends ZzBaseModel {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "last_name")
  private String lastName;

  @Column(name = "email", unique = true)
  private String email;

  @Column(name = "phone")
  private String phone;
  
  
  @Column(name = "hire_date")
  private Date hireDate;
  
  @Column(name="job_id")
  private String jobId;
  
  @Column(name="salary")
  private int salary;
  
  @Column(name="manager_id")
  private int managerId;
  
  
}
