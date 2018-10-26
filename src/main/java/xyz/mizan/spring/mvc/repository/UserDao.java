package xyz.mizan.spring.mvc.repository;

import org.springframework.stereotype.Service;

import xyz.mizan.spring.mvc.entity.User;

@Service
public interface UserDao {

	User findByUserName(String username);
}
