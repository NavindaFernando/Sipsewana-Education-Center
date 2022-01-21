package business.custom.impl;

import business.custom.TrainingProgramsBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.StudentDAO;
import dao.custom.TrainingProgramsDAO;

import dto.StudentDTO;
import dto.TrainingProgramsDTO;

import entity.Student;
import entity.TrainingPrograms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TrainingProgramsBOImpl implements TrainingProgramsBO {

    TrainingProgramsDAO trainingProgramsDAO = DAOFactory.getInstance().getDAO(DAOType.TRAININGPROGRAMS);

    @Override
    public boolean add(TrainingProgramsDTO trainingProgramsDTO) throws Exception {
        return trainingProgramsDAO.add(new TrainingPrograms(
                trainingProgramsDTO.getProgramId(),
                trainingProgramsDTO.getProgramName(),
                trainingProgramsDTO.getDuration(),
                trainingProgramsDTO.getFee()
        ));
    }

    @Override
    public List<TrainingProgramsDTO> findAll() throws Exception {
        List<TrainingPrograms> all = trainingProgramsDAO.findAll();
        ArrayList<TrainingProgramsDTO> dtoList = new ArrayList<>();

        TrainingProgramsDTO trainingProgramsDTO = null;

        for (TrainingPrograms trainingPrograms : all) {
            dtoList.add(new TrainingProgramsDTO(
                    trainingPrograms.getProgramId(),
                    trainingPrograms.getProgramName(),
                    trainingPrograms.getDuration(),
                    trainingPrograms.getFee()
            ));
        }
        return dtoList;
    }

    @Override
    public boolean delete(String id) throws Exception {
        return trainingProgramsDAO.delete(id);
    }

    @Override
    public boolean update(TrainingProgramsDTO trainingProgramsDTO) throws Exception {
        return trainingProgramsDAO.update(new TrainingPrograms(
                trainingProgramsDTO.getProgramId(),
                trainingProgramsDTO.getProgramName(),
                trainingProgramsDTO.getDuration(),
                trainingProgramsDTO.getFee()
        ));
    }

    @Override
    public TrainingPrograms searchProgram(String itemCode) throws Exception {
        return trainingProgramsDAO.find(itemCode);
    }


}

