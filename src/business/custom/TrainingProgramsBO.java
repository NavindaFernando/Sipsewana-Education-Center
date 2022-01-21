package business.custom;

import business.SuperBO;
import dto.TrainingProgramsDTO;
import entity.TrainingPrograms;

import java.io.IOException;
import java.util.List;

public interface TrainingProgramsBO extends SuperBO {
    public boolean add(TrainingProgramsDTO trainingProgramsDTO) throws Exception;

    public List<TrainingProgramsDTO> findAll() throws Exception;

    public boolean delete(String s) throws Exception;

    public boolean update(TrainingProgramsDTO trainingProgramsDTO) throws Exception;

    TrainingPrograms searchProgram(String itemCode) throws Exception;
}
