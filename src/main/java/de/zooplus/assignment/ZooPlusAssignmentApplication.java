package de.zooplus.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The Class ZooPlusAssignmentApplication is tarting point for Spring-Boot app.
 */
@ComponentScan("de.zooplus.*")
@EnableJpaRepositories("de.zooplus.*")
@EntityScan("de.zooplus.model")
@SpringBootApplication
public class ZooPlusAssignmentApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ZooPlusAssignmentApplication.class, args);
	}

}
