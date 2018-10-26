package xyz.mizan.spring.mvc.config.core;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import xyz.mizan.spring.mvc.config.db.HibernateConfig;
import xyz.mizan.spring.mvc.config.security.SecurityAppConfig;

public class ApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	// Load database and spring security configuration
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {HibernateConfig.class, SecurityAppConfig.class};
	}

	// Load spring web configuration
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}
