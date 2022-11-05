package com.escom.tt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.escom.tt"})
public class TtApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TtApplication.class, args);
	}

	@Override
    protected SpringApplicationBuilder  configure(SpringApplicationBuilder application) {
        return application.sources(TtApplication.class);
    }

}
