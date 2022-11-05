
package com.escom.tt.config;

import com.escom.tt.TtApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@Configuration
@EnableJpaRepositories(basePackageClasses = TtApplication.class)
public class SpringDataJpaDetachableRepositoryConfiguration {
    
}
