package entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class StudentDetails implements SuperEntity{
    @Id
    private String studentId;
    private String pName;
    private String pFee;

    public StudentDetails() {

    }

    public StudentDetails(String studentId, String pName, String pFee) {
        this.setStudentId(studentId);
        this.setpName(pName);
        this.setpFee(pFee);
    }


    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpFee() {
        return pFee;
    }

    public void setpFee(String pFee) {
        this.pFee = pFee;
    }

    @Override
    public String toString() {
        return "StudentDetails{" +
                "studentId='" + studentId + '\'' +
                ", pName='" + pName + '\'' +
                ", pFee='" + pFee + '\'' +
                '}';
    }
}
