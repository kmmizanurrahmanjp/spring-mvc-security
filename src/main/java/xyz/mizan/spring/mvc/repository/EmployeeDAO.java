package xyz.mizan.spring.mvc.repository;

import java.util.List;

import org.springframework.stereotype.Service;

import xyz.mizan.spring.mvc.entity.Employee;

@Service
public interface EmployeeDAO {

	public void addEmployee(Employee employee);
	 
    public List<Employee> getAllEmployees();
 
    public void deleteEmployee(Integer employeeId);
 
    public Employee updateEmployee(Employee employee);
 
    public Employee getEmployee(int employeeid);
}
