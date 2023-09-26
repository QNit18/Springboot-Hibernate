package com.anony18.aopDemo.dao;

import com.anony18.aopDemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO{
    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccount() {
        return findAccount(false);
    }

    @Override
    public List<Account> findAccount(boolean tripWire) {

        // for academic purpose ... simulate an exception
        if (tripWire){
            throw new RuntimeException("No soup for you!");
        }
        List<Account> accounts = new ArrayList<>();

        // create sample accounts
        Account temp1 = new Account("Quang", "Level 1");
        Account temp2 = new Account("Hien", "Level 2");
        Account temp3 = new Account("Ngoc", "Level 3");

        // add them to our accounts list
        accounts.add(temp1);
        accounts.add(temp2);
        accounts.add(temp3);

        return accounts;
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work: adding an account!");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }

    public String getName() {
        System.out.println(getClass() + ": getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode()");
        this.serviceCode = serviceCode;
    }
}
