package com.study.springboot_base.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
    public Connection makeConnection() throws ClassNotFoundException, SQLException;
}
