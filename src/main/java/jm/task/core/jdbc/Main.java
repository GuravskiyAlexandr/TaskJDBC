package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        getNewConnection().dropUsersTable();
        getNewConnection().createUsersTable();
        getNewConnection().saveUser("Sy", "SyMY", (byte) 13);
        getNewConnection().saveUser("My", "MYSu", (byte) 13);
        getNewConnection().saveUser("Do", "MYSu", (byte) 13);
        getNewConnection().saveUser("GY", "MYSu", (byte) 13);
        List<User> users = getNewConnection().getAllUsers();
        System.out.println(users);
        getNewConnection().cleanUsersTable();
        getNewConnection().dropUsersTable();
    }

    private static UserService getNewConnection() throws ClassNotFoundException {
        Connection connection = Util.connectionDataBase();
        UserDao userDao = new UserDaoJDBCImpl(connection);
        return new UserServiceImpl(userDao);
    }
}
