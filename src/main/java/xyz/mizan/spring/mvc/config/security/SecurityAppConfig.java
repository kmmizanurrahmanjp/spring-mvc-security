package xyz.mizan.spring.mvc.config.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import xyz.mizan.spring.mvc.config.db.HibernateConfig;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "xyz.mizan.spring.mvc.config.security")
public class SecurityAppConfig extends WebSecurityConfigurerAdapter {
	
	/**
	*User details service OAH2 authentication with remember me token
	*Persistent Token Approach - In this approach, a database or other persistent storage mechanism is used to store the generated tokens.
    *
	*/
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Autowired
	HibernateConfig hibernateConfig;
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Autowired
	PersistentTokenRepository tokenRepository;
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	  public PersistentTokenRepository tokenRepository() {
	    JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl=new JdbcTokenRepositoryImpl();
	    jdbcTokenRepositoryImpl.setDataSource(hibernateConfig.getDataSource());
	    return jdbcTokenRepositoryImpl;
	}
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(customUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/employee/**").hasAnyRole("EMP", "ADMIN")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")	
        .and()
        .rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository).tokenValiditySeconds(300)
        .and()
		.logout().logoutSuccessUrl("/logout")
		.and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf();
        
    }
	
	/**
	*User details service OAH2 authentication with remember me token
    *Hash-Based Token Approach - In this approach, username, expiration time, password and a private key are hashed and sent to browser as a token. 
	*
	*/
	/*@Autowired
    PasswordEncoder passwordEncoder;
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(customUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/employee/**").hasAnyRole("EMP", "ADMIN")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")
        .and()
        .rememberMe().key("smckey").rememberMeParameter("remember-me").rememberMeCookieName("remember-me-cookie").tokenValiditySeconds(86400)
        .and()
		.logout().logoutSuccessUrl("/logout")
		.and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf();
        
    }
	*/
	
	
	
	
	/*
	 * Basic Spring windows base memory authentication
	 * .authenticated() is use when no ROLE are given from antMatchers
	 */
	
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
        .withUser("user")
        .password(encoder.encode("user"))
        .roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/employee/**")
          .authenticated()
          .antMatchers("/")
          .permitAll()
          .and()
          .httpBasic();
    }*/
	
	/*
	 * Basic Spring form base memory authentication
	 * .authenticated() is use when no ROLE are given from antMatchers
	 */
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
        .withUser("user")
        .password(encoder.encode("user"))
        .roles("USER");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/employee/**")
          .authenticated()
          .antMatchers("/")
          .permitAll()
          .and()
          .formLogin();
    }*/
	
	/*
	 * Basic Spring form base memory authentication and multipule authorization
	 * Example using both the type-safe API – hasRole – but also the expression based API, via access
	 */
	/*@Override
    protected void configure(AuthenticationManagerBuilder auth) 
      throws Exception {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication().withUser("user").password(encoder.encode("user")).roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password(encoder.encode("admin")).roles("ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
          .antMatchers("/employee/**").access("hasRole('USER')")
          .antMatchers("/admin/**").hasRole("ADMIN")
          .antMatchers("/")
          .permitAll()
          .and()
          .formLogin();
    }*/	
	
	
	/**
	*In memory authentication with custom login form
	*/
    /*
	@Autowired
    PasswordEncoder passwordEncoder;
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
        .passwordEncoder(passwordEncoder)
        .withUser("emp").password(passwordEncoder.encode("emp")).roles("EMP")
        .and()
        .withUser("admin").password(passwordEncoder.encode("admin")).roles("ADMIN");
    }
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/employee/**").hasAnyRole("EMP", "ADMIN")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")				
	    .and()
		.logout().logoutSuccessUrl("/logout")
		.and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf();
        
    }*/
	
	/**
	*JDBC authentication
	*/
	/*
    @Autowired
    PasswordEncoder passwordEncoder;
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Autowired
	HibernateConfig hibernateConfig;
	
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
	  auth.jdbcAuthentication().dataSource(hibernateConfig.getDataSource())
		.usersByUsernameQuery("SELECT username, password, enabled FROM users WHERE username = ?")
		.authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username = ?")
		.passwordEncoder(encoder());
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/employee/**").hasAnyRole("EMP", "ADMIN")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")				
	    .and()
		.logout().logoutSuccessUrl("/logout")
		.and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf();
        
    }*/
	
	/**
	*User details service OAH2 authentication
	*/
	/*@Autowired
    PasswordEncoder passwordEncoder;
	
	@Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	 
	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(customUserDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
        .antMatchers("/admin/**").hasRole("ADMIN")
        .antMatchers("/employee/**").hasAnyRole("EMP", "ADMIN")
        .antMatchers("/").permitAll()
        .and()
        .formLogin().loginPage("/login").failureUrl("/login?error").usernameParameter("username").passwordParameter("password")				
	    .and()
		.logout().logoutSuccessUrl("/logout")
		.and()
        .exceptionHandling().accessDeniedPage("/access-denied")
        .and()
        .csrf();
        
    }*/
}
