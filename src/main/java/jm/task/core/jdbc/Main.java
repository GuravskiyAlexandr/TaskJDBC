package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Util util = new Util();

        Connection connection = util.connectionDataBase();
        UserDao userDao = new UserDaoJDBCImpl(connection);
        UserService userService = new UserServiceImpl(userDao);
        userService.createUsersTable();

    }
}
