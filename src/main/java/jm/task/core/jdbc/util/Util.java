package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection connectionDataBase() throws ClassNotFoundException {
        final String URL = "jdbc:mysql://localhost:3306/task_core";
        final String USERNAME = "root";
        final String PASSWORD = "root";

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Filed connection");
        }
        return connection;
    }
}
