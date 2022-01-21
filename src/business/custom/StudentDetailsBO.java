package business.custom;

import business.SuperBO;
import dto.StudentDTO;
import dto.StudentDetailsDTO;

import java.util.List;

public interface StudentDetailsBO extends SuperBO {
    public boolean add(StudentDetailsDTO studentDetailsDTO) throws Exception;

    public List<StudentDetailsDTO> findAll() throws Exception;

    public boolean delete(String id) throws Exception;

    public boolean update(StudentDetailsDTO studentDetailsDTO) throws Exception;
}
