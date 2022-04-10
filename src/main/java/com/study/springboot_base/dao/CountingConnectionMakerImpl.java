package com.study.springboot_base.dao;

import com.study.springboot_base.connection.ConnectionMaker;

import java.sql.Connection;
import java.sql.SQLException;

public class CountingConnectionMakerImpl implements ConnectionMaker {
    int counter = 0;
    private ConnectionMaker realConnectionMaker;

    public CountingConnectionMakerImpl(ConnectionMaker realConnectionMaker){
        this.realConnectionMaker = realConnectionMaker;
    }
    public int getCounter(){
        return this.counter;
    }

    @Override
    public Connection makeConnection() throws ClassNotFoundException, SQLException {
        this.counter++;
        return realConnectionMaker.makeConnection();
    }
}
