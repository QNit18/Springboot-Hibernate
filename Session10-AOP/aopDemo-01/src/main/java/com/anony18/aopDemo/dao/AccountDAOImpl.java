package com.anony18.aopDemo.dao;

import com.anony18.aopDemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": Doing my db work: adding an account!");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}
