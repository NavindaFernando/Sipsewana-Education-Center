package business.custom.impl;

import business.custom.StudentDetailsBO;
import dao.DAOFactory;
import dao.DAOType;
import dao.custom.StudentDAO;
import dao.custom.StudentDetailsDAO;
import dto.StudentDTO;
import dto.StudentDetailsDTO;
import entity.Student;
import entity.StudentDetails;

import java.util.ArrayList;
import java.util.List;

public class StudentDetailsBOImpl implements StudentDetailsBO {
    StudentDetailsDAO studentDetailsDAO = DAOFactory.getInstance().getDAO(DAOType.STUDNETDETAILS);

    public boolean add(StudentDetailsDTO studentDetailsDTO) throws Exception {
        return studentDetailsDAO.add(new StudentDetails(
                studentDetailsDTO.getStudentId(),
                studentDetailsDTO.getpName(),
                studentDetailsDTO.getpFee()
        ));
    }

    public List<StudentDetailsDTO> findAll() throws Exception {
        List<StudentDetails> all = studentDetailsDAO.findAll();
        ArrayList<StudentDetailsDTO> dtoList = new ArrayList<>();

        StudentDetailsDTO studentDetailsDTO = null;

        for (StudentDetails studentDetails : all) {
            dtoList.add(new StudentDetailsDTO(
                    studentDetails.getStudentId(),
                    studentDetails.getpName(),
                    studentDetails.getpFee()
            ));
        }
        return dtoList;
    }


    public boolean delete(String id) throws Exception {
        return studentDetailsDAO.delete(id);
    }

    public boolean update(StudentDetailsDTO studentDetailsDTO) throws Exception {
        return studentDetailsDAO.update(new StudentDetails(
                studentDetailsDTO.getStudentId(),
                studentDetailsDTO.getpName(),
                studentDetailsDTO.getpFee()
        ));
    }
}
