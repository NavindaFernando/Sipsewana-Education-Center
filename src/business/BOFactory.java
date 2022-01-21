package business;

import business.custom.impl.StudentBOImpl;
import business.custom.impl.StudentDetailsBOImpl;
import business.custom.impl.TrainingProgramsBOImpl;

public class BOFactory {
    private static business.BOFactory boFactory;

    private BOFactory(){}

    public static business.BOFactory getInstance(){
        return (null == boFactory) ? boFactory = new business.BOFactory() : boFactory;
    }

    public <T extends SuperBO> T getBO(BOType boType){
        switch (boType){
            case STUDENT:
                return (T) new StudentBOImpl();
            case TRAININGPROGRAMS:
                return (T) new TrainingProgramsBOImpl();
            case STUDENTDETAILS:
                return (T) new StudentDetailsBOImpl();
            default:
                return null;
        }
    }
}
