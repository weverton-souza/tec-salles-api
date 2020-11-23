package com.tec.salles;

import com.tec.salles.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.text.ParseException;

@EnableSwagger2
@SpringBootApplication
@EnableJpaRepositories
public class TecSalesApiApplication {

	@Autowired
	public TecSalesApiApplication(DBService dbService) throws ParseException {
		dbService.instantiateTestDatabase();
	}

	public static void main(String[] args) {
		SpringApplication.run(TecSalesApiApplication.class, args);
	}

}
