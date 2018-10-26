package xyz.mizan.spring.mvc.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@Service
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.repository")
public class UserServiceImpl implements UserService{

}
