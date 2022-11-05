package com.escom.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class TtApplication {

	public static void main(String[] args) {
		SpringApplication.run(TtApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder  configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
    
    @Override
    protected WebApplicationContext run(SpringApplication application) {
        application.getSources().remove(ErrorPageFilter.class);
        return super.run(application);
    }
    
    private static Class<TtApplication> applicationClass = TtApplication.class;
}
