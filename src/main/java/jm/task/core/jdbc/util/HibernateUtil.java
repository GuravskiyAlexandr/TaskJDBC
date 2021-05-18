package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


import java.util.HashMap;
import java.util.Map;


public class HibernateUtil {
    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected static SessionFactory buildSessionFactory(){
        Map<String, String> conf = new HashMap<>();
        conf.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        conf.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/task_core");
        conf.put("hibernate.connection.username", "root");
        conf.put("hibernate.connection.password", "root");
        conf.put("hibernate.connection.pool_size", "2");
        conf.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
        conf.put("hibernate.show_sql", "true");


        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().applySettings(conf)
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            StandardServiceRegistryBuilder.destroy( registry );
        }
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
