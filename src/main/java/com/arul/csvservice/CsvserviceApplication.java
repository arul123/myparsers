package com.arul.csvservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.PathSelectors;
import org.springframework.context.annotation.Bean;
import com.arul.csvservice.config.CSVBeansConfig;
import org.springframework.context.annotation.Import;

/**
* @author Arul J
*
*/
@SpringBootApplication
@EnableSwagger2
@Import(CSVBeansConfig.class)
public class CsvserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CsvserviceApplication.class, args);
	}


	@Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.regex("(?!/error.*).*"))                        
          .build();                                           
    }
}


