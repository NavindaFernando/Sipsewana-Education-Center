package dao;

import dao.custom.impl.StudentDAOImpl;
import dao.custom.impl.StudentDetailsDAOImpl;
import dao.custom.impl.TrainingProgramsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return (null == daoFactory) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public <T extends SuperDAO>T getDAO(dao.DAOType daoType){
        switch (daoType){
            case STUDENT:
                return (T) new StudentDAOImpl();
            case TRAININGPROGRAMS:
                return (T) new TrainingProgramsDAOImpl();
            case STUDNETDETAILS:
                return (T) new StudentDetailsDAOImpl();
            default:
                return null;
        }
    }
}
