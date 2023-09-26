package com.anony18.aopDemo.dao;

import com.anony18.aopDemo.Account;

public interface AccountDAO {
    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();
}
