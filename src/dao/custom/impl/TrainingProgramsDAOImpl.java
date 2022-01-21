package dao.custom.impl;

import dao.custom.TrainingProgramsDAO;
import entity.TrainingPrograms;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.util.List;

public class TrainingProgramsDAOImpl implements TrainingProgramsDAO {

    @Override
    public boolean add(TrainingPrograms entity) throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(TrainingPrograms entity) throws Exception {
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

        TrainingPrograms trainingPrograms = session.get(TrainingPrograms.class, s);
        session.delete(trainingPrograms);

        transaction.commit();
        session.close();
        return true;
    }



    @Override
    public List<TrainingPrograms> findAll() throws Exception {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        List<TrainingPrograms> list = null;

        Query trainingPrograms = session.createQuery("from TrainingPrograms");
        list = trainingPrograms.list();

        transaction.commit();

        session.close();
        return list;
    }

    @Override
    public TrainingPrograms find(String p_id) throws IOException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction=session.beginTransaction();
        TrainingPrograms pro=session.get(TrainingPrograms.class,p_id);
        transaction.commit();
        session.close();
        return pro;
    }
}
