package com.study.springboot_base.user;

import java.sql.*;

public class UserDao {

    public void add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = connection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "insert into users(name,id,password) values(?,?,?)"
        );
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getId());
        preparedStatement.setString(3, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Connection connection = connection();
        PreparedStatement ps = connection.prepareStatement(
                "select * from users where id = ?"
        );
        ps.setString(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();
        User user = new User();
        user.setId(rs.getString("username"));
        user.setId(rs.getString("id"));
        user.setId(rs.getString("password"));

        connection.close();
        ps.close();
        rs.close();

        return user;
    }

    public Connection connection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/spring_boot_study", "root", "qsc20215");
        return connection;
    }
}
