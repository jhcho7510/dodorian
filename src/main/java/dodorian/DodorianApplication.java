package dodorian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import dodorian.service.DodorianService;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
public class DodorianApplication implements CommandLineRunner {
	@Autowired
	DodorianService service;
	public static void main(String[] args) {
		SpringApplication.run(DodorianApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		service.dotoriRun();
		
	}

}
