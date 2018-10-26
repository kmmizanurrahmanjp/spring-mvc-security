package xyz.mizan.spring.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import xyz.mizan.spring.mvc.entity.Employee;
import xyz.mizan.spring.mvc.service.EmployeeService;

@Controller
@RequestMapping(value="/employee")
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.service") 
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping("/list")
	public ModelAndView goIndex() {
		ModelAndView mv = new ModelAndView();
		List<Employee> listEmployee = employeeService.getAllEmployees();
		mv.addObject("listEmployee", listEmployee);
		mv.addObject("employee", new Employee());
		mv.setViewName("employee");
		return mv;
	}
	
	@PostMapping("/add")
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		if (employee.getId() == 0) {
			employeeService.addEmployee(employee);
		} else {
			employeeService.updateEmployee(employee);
		}
		return new ModelAndView("redirect:/employee/list");
	}
	
	@GetMapping("/delete")
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		int employeeId = Integer.parseInt(request.getParameter("id"));
		employeeService.deleteEmployee(employeeId);
		return new ModelAndView("redirect:/employee/list");
	}

	@GetMapping("/switch")
	public ModelAndView editContact(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		int employeeId = Integer.parseInt(request.getParameter("id"));
		Employee employee = employeeService.getEmployee(employeeId);
		mv.addObject("employee", employee);
		List<Employee> listEmployee = employeeService.getAllEmployees();
		mv.addObject("listEmployee", listEmployee);
		mv.setViewName("employee");
		return mv;
	}

}
