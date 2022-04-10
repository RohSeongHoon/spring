package com.study.springboot_base.dao;

import com.study.springboot_base.connection.ConnectionMaker;
import com.study.springboot_base.domain.User;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.makeConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(username,id,password) values(?,?,?)"
        );
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getId());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.makeConnection();
        PreparedStatement ps = connection.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setUsername(rs.getString("username"));
        user.setId(rs.getString("id"));
        user.setPassword(rs.getString("password"));

        connection.close();
        ps.close();
        rs.close();

        return user;
    }
    public void deleteAll() throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();
        PreparedStatement ps = connection.prepareStatement("delete from users");

        ps.executeUpdate();

        ps.close();
        connection.close();
    }
    public int getCount() throws SQLException, ClassNotFoundException {
        Connection connection = connectionMaker.makeConnection();
        PreparedStatement ps = connection.prepareStatement("select count(*) from users");

        ResultSet rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);

        rs.close();
        ps.close();
        connection.close();

        return count;


    }

}
