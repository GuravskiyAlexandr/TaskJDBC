package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.query.Query;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory factory;

    public UserDaoHibernateImpl() {
    }

    public UserDaoHibernateImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void createUsersTable() {
        String createTblHib = "create table IF NOT EXISTS hibernate_sequence (next_val bigint) engine=MyISAM";
        String createTblUser = "create table IF NOT EXISTS" +
                " usr (" +
                " id bigint not null," +
                " age tinyint, " +
                " lastName varchar(255)," +
                " name varchar(255)," +
                " primary key (id)) " +
                " engine=MyISAM";
        String insertHib = "insert into hibernate_sequence values ( 1 )";
        updateTable(createTblHib, createTblUser, insertHib);

    }

    @Override
    public void dropUsersTable() {
        String dropTableHib = "drop table if exists hibernate_sequence";
        String dropTableUser = "drop table if exists usr";
        updateTable(dropTableHib, dropTableUser, null);
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (SQLGrammarException e) {
            e.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
            System.out.println("Save failed!");
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.createQuery("DELETE FROM User WHERE id = :id")
                    .setParameter("id", id).executeUpdate();

            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
            System.out.println("Save failed!");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = factory.openSession()) {
            return (List<User>) session.createQuery("FROM User ").list();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("Failed get Users");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.createQuery("delete from User ").executeUpdate();
            session.getTransaction().commit();
        } catch (HibernateException e) {
            assert session != null;
            session.getTransaction().rollback();
            e.printStackTrace();
            System.out.println("Failed delete all Users");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    private void updateTable(String tableHib, String TableUser, String insertHib) {
        Session session = null;
        try {
            session = factory.openSession();
            session.beginTransaction();
            session.createNativeQuery(tableHib).executeUpdate();
            session.createNativeQuery(TableUser).addEntity(User.class).executeUpdate();
            if (insertHib != null) {
                session.createNativeQuery(insertHib).executeUpdate();
            }
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            assert session != null;
            session.beginTransaction().rollback();
            System.out.println("Query create table failed!");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
