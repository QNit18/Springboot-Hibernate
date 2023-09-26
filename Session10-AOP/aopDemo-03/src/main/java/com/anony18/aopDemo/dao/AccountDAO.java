package com.anony18.aopDemo.dao;

import com.anony18.aopDemo.Account;

import java.util.List;

public interface AccountDAO {


    // add new method : findAccounts()
    List<Account> findAccount();

    List<Account> findAccount(boolean tripWire);

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);

}
