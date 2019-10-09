package root;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UsingSpringProfileApplication implements CommandLineRunner {

	@Value("${spring.profile.test.string}")
	private String testProfileOfPropertiesFile; 
	
	public static void main(String[] args) {
		SpringApplication.run(UsingSpringProfileApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("#####################################");
		System.out.println(testProfileOfPropertiesFile);
		System.out.println("#####################################");
	}

}
