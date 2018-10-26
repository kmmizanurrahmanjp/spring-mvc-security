package xyz.mizan.spring.mvc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import xyz.mizan.spring.mvc.entity.Employee;

@Service
public interface EmployeeService {

	public void addEmployee(Employee employee);
	 
    public List<Employee> getAllEmployees();
 
    public void deleteEmployee(Integer employeeId);
 
    public Employee getEmployee(int employeeid);
 
    public Employee updateEmployee(Employee employee);
}
