package io.github.cursoms.mscreditassessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MicroserviceCreditAssessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceCreditAssessorApplication.class, args);
	}

}
