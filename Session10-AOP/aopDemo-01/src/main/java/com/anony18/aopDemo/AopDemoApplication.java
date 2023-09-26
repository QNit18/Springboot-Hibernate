package com.anony18.aopDemo;

import com.anony18.aopDemo.dao.AccountDAO;
import com.anony18.aopDemo.dao.MemberShipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MemberShipDAO theMemberShipDAO){

		return runner ->{
			demoBeforeAdvice(theAccountDAO, theMemberShipDAO);
		};
	}

	private void demoBeforeAdvice(AccountDAO theAccountDAO, MemberShipDAO theMemberShipDAO){
		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();

		// call the membership business method
		theMemberShipDAO.addSillyMember();
		theMemberShipDAO.goToSleep();
	}

}
