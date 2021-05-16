package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    public UserDaoJDBCImpl() {
    }

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public void createUsersTable() {
        String SQL = "CREATE TABLE IF NOT EXISTS  usr" +
                "(id BIGINT UNSIGNED not NULL AUTO_INCREMENT, " +
                " name VARCHAR(50), " +
                " lastName VARCHAR (50), " +
                " age TINYINT not NULL, " +
                " PRIMARY KEY (id))";
        String err = "Failed to create user table";
        updateDatabase(err, SQL);
    }

    public void dropUsersTable() {
        String SQL = "DROP TABLE IF EXISTS usr";
        String err = "Failed to drop user table";
        updateDatabase(err, SQL);
    }

    public void saveUser(String name, String lastName, byte age) {
        PreparedStatement preparedStatement = null;
        String SQL = "INSERT INTO  usr (name, lastName, age) values (?, ?, ?)";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed create user");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removeUserById(long id) {
        PreparedStatement preparedStatement = null;
        String SQL = "DELETE  FROM usr WHERE id =?";
        try {
            preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed delete user with "+ id);
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        String SQL = "SELECT id, name, lastName, age FROM usr";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Failed get all user");
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return users;
    }

    public void cleanUsersTable() {
        String SQL = "TRUNCATE TABLE usr";
        String err = "Failed clean users table";
        updateDatabase(err,SQL );
    }

    private void updateDatabase(String err, String SQL) {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(SQL);

        } catch (SQLException sq) {
            sq.printStackTrace();
            System.out.println(err);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }
}
