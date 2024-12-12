package io.github.cursoms.mscreditcard;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class MscreditcardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscreditcardApplication.class, args);
	}

}
