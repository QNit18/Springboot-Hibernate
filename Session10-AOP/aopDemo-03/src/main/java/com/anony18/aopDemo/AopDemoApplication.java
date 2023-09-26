package com.anony18.aopDemo;

import com.anony18.aopDemo.dao.AccountDAO;
import com.anony18.aopDemo.dao.MemberShipDAO;
import com.anony18.aopDemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
											   MemberShipDAO theMemberShipDAO,
											   TrafficFortuneService trafficFortuneService){

		return runner ->{
			// demoBeforeAdvice(theAccountDAO, theMemberShipDAO);
			//demoTheAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);

			//demoTheAroundAdvice(trafficFortuneService);
			demoTheAroundHandleException(trafficFortuneService);
		};
	}

	private void demoTheAroundHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("Main Program: demoTheAroundHandleException");

		System.out.println("Calling getFortune()");

		boolean tripWiere = true;

		String data = trafficFortuneService.getFortune(tripWiere);

		System.out.println("My fortune is: " + data);
		System.out.println("Finished!!!!");
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("Main Program: demoTheAroundAdvice");

		System.out.println("Calling getFortune()");

		String data = trafficFortuneService.getFortune();

		System.out.println("My fortune is: " + data);
		System.out.println("Finished!!!!");
	}


	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccount(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nMain program: ... caught exception: " + exc);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice()");
		System.out.println("-------------");
		System.out.println(theAccounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
		// call method to find the accounts
		List<Account> theAccounts = null;

		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccount(tripWire);
		}catch (Exception exc){
			System.out.println("\n\nMain program: ... caught exception: " + exc);
		}

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice()");
		System.out.println("-------------");
		System.out.println(theAccounts);
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
		// call method to find the accounts
		List<Account> theAccounts = theAccountDAO.findAccount();

		// display the accounts
		System.out.println("\n\nMain Program: demoTheAfterReturningAdvice()");
		System.out.println("-------------");
		System.out.println(theAccounts);
		System.out.println("\n");
	}

	private void demoBeforeAdvice(AccountDAO theAccountDAO, MemberShipDAO theMemberShipDAO){
		// call the business method
		Account myAccount = new Account("Van Quang", "Level Max");
		theAccountDAO.addAccount(myAccount, true);
		System.out.println();
		theAccountDAO.doWork();
		System.out.println();

		// call the membership business method
		theMemberShipDAO.addSillyMember();
		System.out.println();
		theMemberShipDAO.goToSleep();
		System.out.println();

		// call the accountdao getter/setter methods
		theAccountDAO.setName("van quang");
		System.out.println();
		theAccountDAO.setServiceCode("alisa");
		System.out.println();

		String name = theAccountDAO.getName();
		System.out.println();
		String serverCode = theAccountDAO.getServiceCode();

		System.out.println();
		theAccountDAO.addAccount(myAccount,false);
	}

}
