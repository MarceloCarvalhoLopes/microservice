package io.github.cursoms.mscreditcard;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
@Slf4j
public class MscreditcardApplication {

	public static void main(String[] args) {
		log.info("Information: {}", "teste info");
		log.error("Error: {}", "teste erro");
		log.warn("Warning: {}", "teste warn");
		SpringApplication.run(MscreditcardApplication.class, args);
	}

}
