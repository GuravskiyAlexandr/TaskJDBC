package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final String URL = "jdbc:mysql://localhost:3306/task_core";
    private final String USERNAME = "root";
    private final String PASSWORD = "root";

    public Connection connectionDataBase() throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Filed connection");
        }
        return null;
    }
}
