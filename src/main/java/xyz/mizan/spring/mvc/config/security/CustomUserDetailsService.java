package xyz.mizan.spring.mvc.config.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import xyz.mizan.spring.mvc.entity.UserRole;
import xyz.mizan.spring.mvc.repository.UserDao;

@Service("customUserDetailsService")
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.repository") 
public class CustomUserDetailsService implements UserDetailsService{

		@Autowired
		private UserDao userDao;

		@Override
		public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
			xyz.mizan.spring.mvc.entity.User user = userDao.findByUserName(username);
			List<GrantedAuthority> authorities =  buildUserAuthority(user.getUserRole());
			return buildUserForAuthentication(user, authorities);
		}

		// Converts com.mkyong.users.model.User user to org.springframework.security.core.userdetails.User
		private User buildUserForAuthentication(xyz.mizan.spring.mvc.entity.User user, List<GrantedAuthority> authorities) {
			return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
		}

		private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
			Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
			// Build user's authorities
			for (UserRole userRole : userRoles) {
				setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
			}
			List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);
			return Result;
		}

}
