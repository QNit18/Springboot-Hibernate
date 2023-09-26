package com.anony18.aopDemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MemberShipDAOImpl implements MemberShipDAO{
    @Override
    public boolean addSillyMember() {
        System.out.println(getClass() + ": Doing my db work: adding an membership account!");
        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": I'm going to sleep now...");
    }

}
