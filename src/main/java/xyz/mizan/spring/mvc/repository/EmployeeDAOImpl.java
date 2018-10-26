package xyz.mizan.spring.mvc.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.mizan.spring.mvc.entity.Employee;

@Repository
@Transactional
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.config.db") 
public class EmployeeDAOImpl implements EmployeeDAO {
 
    /*@Autowired
    private SessionFactory sessionFactory;*/
    
    @Autowired
	private HibernateTemplate  hibernateTemplate;
 
    @Override
    public void addEmployee(Employee employee) {
        //sessionFactory.getCurrentSession().saveOrUpdate(employee);
    	hibernateTemplate.save(employee);
    }
 
    @SuppressWarnings("unchecked")
    public List<Employee> getAllEmployees() {
        //return sessionFactory.getCurrentSession().createQuery("from Employee").list();
    	String hql = "FROM Employee";
		return (List<Employee>) hibernateTemplate.find(hql);
    }

    @Override
	public void deleteEmployee(Integer employeeId) {
		/*Employee employee = (Employee) sessionFactory.getCurrentSession().load(Employee.class, employeeId);
        if (null != employee) {
            this.sessionFactory.getCurrentSession().delete(employee);
        }*/
		hibernateTemplate.delete(getEmployee(employeeId));
		
	}

    @Override
	public Employee updateEmployee(Employee employee) {
		//sessionFactory.getCurrentSession().update(employee);
		hibernateTemplate.update(employee);
        return employee;
	}

	@Override
	public Employee getEmployee(int employeeid) {
		 //return (Employee) sessionFactory.getCurrentSession().get(Employee.class, employeeid);
		return hibernateTemplate.get(Employee.class, employeeid);
	}
 
}
