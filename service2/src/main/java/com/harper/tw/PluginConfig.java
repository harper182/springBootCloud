package com.harper.tw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableEurekaClient
@Configuration
public class PluginConfig {

    @Autowired
    private Environment environment;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        String title = "harper [" + environment.getActiveProfiles()+"]";
        return new Docket(DocumentationType.SWAGGER_2).select().build()
                .apiInfo(new ApiInfo(title,null,null,null,new Contact("","",""),null,null));
    }
}
