package util;

import entity.Student;
import entity.StudentDetails;
import entity.TrainingPrograms;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FactoryConfiguration {
    private static FactoryConfiguration factoryConfiguration;
    private SessionFactory sessionFactory;

    private FactoryConfiguration(){
        /*Configuration configure = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(TrainingPrograms.class);
        sessionFactory = configure.buildSessionFactory();*/
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("hibernate.properties"));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Configuration configuration = new Configuration().addProperties(properties)
                .addAnnotatedClass(Student.class).addAnnotatedClass(TrainingPrograms.class).addAnnotatedClass(StudentDetails.class);
        sessionFactory = configuration.buildSessionFactory();
    }

    public static FactoryConfiguration getInstance(){
        return (factoryConfiguration == null) ? factoryConfiguration = new FactoryConfiguration() : factoryConfiguration;
    }

    public Session getSession(){
        return sessionFactory.openSession();
    }
}
