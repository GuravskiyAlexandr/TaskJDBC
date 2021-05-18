package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.HibernateUtil;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Connection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.WARNING);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        UserDao userDao = new UserDaoHibernateImpl(sessionFactory);
        userDao.createUsersTable();
        userDao.saveUser("Gy", "GYFrO", (byte) 9);
        userDao.saveUser("Gyd", "GYrFO", (byte) 49);
        userDao.saveUser("Gys", "GYfFO", (byte) 39);
        userDao.saveUser("Gye", "GYFdO", (byte) 29);
        userDao.removeUserById(1);
        List<User> users = userDao.getAllUsers();
        System.out.println(users);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();

        if (sessionFactory != null) {
            sessionFactory.close();
        }

    }


    private static void startApp() throws ClassNotFoundException {
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
