package dodorian;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;



import dodorian.service.DodorianService;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@Slf4j
public class DodorianApplication implements CommandLineRunner {
	@Autowired
	DodorianService service;
	public static void main(String[] args) {
		SpringApplication.run(DodorianApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// System.out.println("11111111");
		// TODO Auto-generated method stub
		//service.dotoriRun();
		
	}

}
