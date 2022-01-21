package dao.custom;

import dao.SuperDAO;
import entity.TrainingPrograms;

import java.io.IOException;

public interface TrainingProgramsDAO extends SuperDAO<TrainingPrograms, String> {
    public TrainingPrograms find(String p_id) throws IOException;
}
