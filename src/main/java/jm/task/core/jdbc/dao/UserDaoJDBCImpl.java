package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {
    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        Statement statement = null;
        String SQL = "CREATE TABLE  usr" +
                "(id BIGINT UNSIGNED not NULL, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id))";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

        } catch (SQLException sq) {
            sq.printStackTrace();
            System.out.println("Failed to create user table");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }
    }

    public void dropUsersTable() {

    }

    public void saveUser(String name, String lastName, byte age) {

    }

    public void removeUserById(long id) {

    }

    public List<User> getAllUsers() {
        return null;
    }

    public void cleanUsersTable() {

    }
}
