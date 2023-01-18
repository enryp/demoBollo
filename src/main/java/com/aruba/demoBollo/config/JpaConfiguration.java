package com.aruba.demoBollo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.aruba.demoBollo.repository")
public class JpaConfiguration {

}
