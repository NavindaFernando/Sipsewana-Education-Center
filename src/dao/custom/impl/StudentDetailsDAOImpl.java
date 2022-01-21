package dao.custom.impl;

import dao.custom.StudentDetailsDAO;
import entity.Student;
import entity.StudentDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.util.List;

public class StudentDetailsDAOImpl implements StudentDetailsDAO {

    @Override
    public boolean add(StudentDetails entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(StudentDetails entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();

        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        return true;
    }

    @Override
    public boolean delete(String s) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        StudentDetails students = session.get(StudentDetails.class, s);

        session.delete(students);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public StudentDetails find(String s) throws Exception {
        return null;
    }

    @Override
    public List<StudentDetails> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<StudentDetails> list = null;

        Query students = session.createQuery("from StudentDetails");
        list = students.list();

        transaction.commit();

        session.close();
        return list;
    }
}
