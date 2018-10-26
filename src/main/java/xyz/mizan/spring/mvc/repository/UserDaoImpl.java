package xyz.mizan.spring.mvc.repository;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import xyz.mizan.spring.mvc.entity.User;

@Repository
@Transactional
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.config.db")
public class UserDaoImpl implements UserDao{

	@Autowired
    private SessionFactory sessionFactory;
	
	@Override
	public User findByUserName(String username) {
		User user = new User();
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User where username=?").setParameter(0, username).list();
		if (users.size() > 0) {
			user = users.get(0);
		} else {
			user = null;
		}
		return user;
	}

}
