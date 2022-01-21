package controller;

import business.BOFactory;
import business.BOType;
import business.custom.TrainingProgramsBO;
import business.custom.impl.StudentBOImpl;
import business.custom.impl.StudentDetailsBOImpl;
import business.custom.impl.TrainingProgramsBOImpl;
import com.jfoenix.controls.JFXTextField;
import dao.custom.StudentDAO;
import dao.custom.TrainingProgramsDAO;
import dao.custom.impl.TrainingProgramsDAOImpl;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import dto.StudentDTO;
import dto.StudentDetailsDTO;
import dto.TrainingProgramsDTO;
import entity.StudentDetails;
import entity.TrainingPrograms;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import views.tm.StudentDetailsTM;
import views.tm.StudentTM;
import views.tm.TrainingProgramsTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DashboardFormController {

    public Pane pane;

    public FontAwesomeIconView iconClear;
    public FontAwesomeIconView iconDelete;
    public FontAwesomeIconView iconUpdate;

    public FontAwesomeIconView iconProgramClear;
    public FontAwesomeIconView iconProgramDelete;
    public FontAwesomeIconView iconProgramUpdate;
    public ComboBox <String>cmbPId;
    public Label lblPId;

    public TextField txtPName;
    public TextField txtPFee;
    public TableColumn colSId;
    public TableColumn colPName;
    public TableColumn colPFee;

    Stage stage;

    public TextField txtStudentId;
    public Label lblStudentId;
    public TextField txtStudentName;
    public Label lblStudentName;
    public TextField txtStudentAddress;
    public Label lblStudentAddress;
    public TextField txtStudentContact;
    public Label lblStudentContact;
    public Label lblTrainingPrograms;
    public TextField txtStudentAge;

    public Label lblRegister;
    public ImageView imgArrow;
    public TextField txtProgramId;
    public Label lblProgramId;
    public TextField txtProgramName;
    public Label lblProgramName;
    public TextField txtProgramDuration;
    public Label lblProgramDuration;
    public TextField txtProgramFee;
    public Label lblProgramFee;

    public Text lblFirstText;
    public Label lblAdd;
    public Label lblDelete;

    public TableView <StudentDetailsTM>tblJoinCol;
    public TableColumn colStudentId;
    public TableColumn colProgramId;

    public TableView <StudentTM>tblStudentDetails;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colAge;
    public Text lblSecondText;
    public Label lblRemove;

    public TableView <TrainingProgramsTM>tblTrainingPrograms;
    public TableColumn colProgramIds;
    public TableColumn colProgramName;
    public TableColumn colDuration;
    public TableColumn colFee;
    public Label lblClear;
    public AnchorPane dashBoardFormContext;

    StudentBOImpl studentBOImpl = BOFactory.getInstance().getBO(BOType.STUDENT);
    TrainingProgramsBOImpl trainingProgramsBOImpl = BOFactory.getInstance().getBO(BOType.TRAININGPROGRAMS);
    StudentDetailsBOImpl studentDetailsBOImpl = BOFactory.getInstance().getBO(BOType.STUDENTDETAILS);

    public void initialize(){

        findAll();
        findAllProgram();
        findAllStudentDetails();

        tableListener();
        programTableListener();
        StudentDetailsTableListner();

        setCellValueFactory();
        setProgramCellValueFactory();
        setStudentDetailsCellValueFactory();

        try {
            loadProgramIds();
        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbPId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setData(newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        invisibleProgramDetails();
    }

    // add to cmb data to textfield
    private void setData(String itemCode) throws Exception {
        TrainingPrograms i1 = trainingProgramsBOImpl.searchProgram(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            txtPName.setText(i1.getProgramName());
            txtPFee.setText(i1.getFee());
        }
    }

    // load program ids
    private void loadProgramIds() throws Exception {
        try {
            ArrayList<TrainingProgramsDTO> all = (ArrayList<TrainingProgramsDTO>) trainingProgramsBOImpl.findAll();
            for (TrainingProgramsDTO trainingProgramsDTO : all) {
                cmbPId.getItems().add(trainingProgramsDTO.getProgramId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load customer id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // start student table option
    private void tableListener(){
        tblStudentDetails.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });
    }

    private void programTableListener(){
        tblTrainingPrograms.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setProgramData(newValue);
                });
    }

    private void StudentDetailsTableListner(){
        tblJoinCol.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setStudentDetailsData(newValue);
                });
    }

    private void setData(StudentTM tm) {
        try {
            txtStudentId.setText(tm.getId());
            txtStudentName.setText(tm.getName());
            txtStudentAddress.setText(tm.getAddress());
            txtStudentContact.setText(tm.getContact());
            txtStudentAge.setText(tm.getContact());
        }catch (Exception e){

        }
    }

    private void setProgramData(TrainingProgramsTM tmm) {
        try {
            txtProgramId.setText(tmm.getProgramId());
            txtProgramName.setText(tmm.getProgramName());
            txtProgramDuration.setText(tmm.getDuration());
            txtProgramFee.setText(tmm.getFee());
        }catch (Exception e){

        }
    }

    private void setStudentDetailsData(StudentDetailsTM tmmm){
        try {
            txtStudentId.setText(tmmm.getStudentId());
            txtPName.setText(tmmm.getpName());
            txtPFee.setText(tmmm.getpFee());
        }catch (Exception e){

        }
    }

    private void setCellValueFactory(){
        colId.setCellValueFactory(new PropertyValueFactory("Id"));
        colName.setCellValueFactory(new PropertyValueFactory("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory("Contact"));
        colAge.setCellValueFactory(new PropertyValueFactory("Age"));
    }

    private void setProgramCellValueFactory(){
        colProgramIds.setCellValueFactory(new PropertyValueFactory("programId"));
        colProgramName.setCellValueFactory(new PropertyValueFactory("programName"));
        colDuration.setCellValueFactory(new PropertyValueFactory("duration"));
        colFee.setCellValueFactory(new PropertyValueFactory("fee"));
    }

    private void setStudentDetailsCellValueFactory(){
        colSId.setCellValueFactory(new PropertyValueFactory("studentId"));
        colPName.setCellValueFactory(new PropertyValueFactory("pName"));
        colPFee.setCellValueFactory(new PropertyValueFactory("pFee"));
    }

    public void findAll(){
        try {
            ObservableList<StudentTM> tmList = FXCollections.observableArrayList();
            List<StudentDTO> all = studentBOImpl.findAll();
            for(StudentDTO dto : all){
                StudentTM tm = new StudentTM(
                        dto.getId(),
                        dto.getName(),
                        dto.getAddress(),
                        dto.getContact(),
                        dto.getAge()
                );

                tmList.add(tm);
            }
            tblStudentDetails.setItems(tmList);
        } catch (Exception e) {
              new Alert(Alert.AlertType.WARNING,"try again!").show();
        }
    }

    public void findAllProgram(){
        try {
            ObservableList<TrainingProgramsTM> tmmList = FXCollections.observableArrayList();
            List<TrainingProgramsDTO> alls = trainingProgramsBOImpl.findAll();
            for(TrainingProgramsDTO dtoo : alls){
                TrainingProgramsTM tmm = new TrainingProgramsTM(
                        dtoo.getProgramId(),
                        dtoo.getProgramName(),
                        dtoo.getDuration(),
                        dtoo.getFee()
                );

                tmmList.add(tmm);
            }
            tblTrainingPrograms.setItems(tmmList);
        } catch (Exception e) {
              new Alert(Alert.AlertType.WARNING,"try again!").show();
        }
    }

    public void findAllStudentDetails(){
        try {
            ObservableList<StudentDetailsTM> tmmmList = FXCollections.observableArrayList();
            List<StudentDetailsDTO> alls = studentDetailsBOImpl.findAll();
            for(StudentDetailsDTO dtoo : alls){
                StudentDetailsTM tmmm = new StudentDetailsTM(
                        dtoo.getStudentId(),
                        dtoo.getpName(),
                        dtoo.getpFee()
                );

                tmmmList.add(tmmm);
            }
            tblJoinCol.setItems(tmmmList);
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING,"try again!").show();
        }
    }

    public void registerStudentOnAction(MouseEvent mouseEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contact = txtStudentContact.getText();
        String age = txtStudentAge.getText();

        String sId = txtStudentId.getText();
        String pName = txtPName.getText();
        String pFee = txtPFee.getText();

        try {
            if (studentBOImpl.add(new StudentDTO(
                    id,
                    name,
                    address,
                    contact,
                    age
            )));{
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save student?").showAndWait();
                txtStudentId.setText(null);
                txtStudentName.setText(null);
                txtStudentAddress.setText(null);
                txtStudentContact.setText(null);
                txtStudentAge.setText(null);
                txtPName.setText(null);
                txtPFee.setText(null);
                findAll();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again!").showAndWait();
        }

        try {
            if (studentDetailsBOImpl.add(new StudentDetailsDTO(
                    sId,
                    pName,
                    pFee
            )));{
                txtStudentId.setText(null);
                txtPName.setText(null);
                txtPFee.setText(null);
                findAll();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again!").showAndWait();
        }
    }

    public void addProgramOnAction(MouseEvent mouseEvent) {
        String pId = txtProgramId.getText();
        String pName = txtProgramName.getText();
        String pDuration = txtProgramDuration.getText();
        String pFee = txtProgramFee.getText();

        try {
            if (trainingProgramsBOImpl.add(new TrainingProgramsDTO(
                    pId,
                    pName,
                    pDuration,
                    pFee
            )));{
                new Alert(Alert.AlertType.CONFIRMATION, "Do you want to save this program?").showAndWait();
                txtProgramId.setText(null);
                txtProgramName.setText(null);
                txtProgramDuration.setText(null);
                txtProgramFee.setText(null);
                findAll();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "please try again!").showAndWait();
            System.out.println(e);
        }
    }

    public void updateStudentOnAction(MouseEvent mouseEvent) {

        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String contact = txtStudentContact.getText();
        String age = txtStudentAge.getText();
        try {
            if(studentBOImpl.update(new StudentDTO(
                    id,
                    name,
                    address,
                    contact,
                    age
            ))){

                findAll();
                txtStudentId.setText(null);
                txtStudentName.setText(null);
                txtStudentAddress.setText(null);
                txtStudentContact.setText(null);
                txtStudentAge.setText(null);
            }else {
                new Alert(Alert.AlertType.ERROR, "Something happened! please check this").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something happened! please check this").show();
        }
    }

    public void updateTrainingProgramText(MouseEvent mouseEvent) {
        String pId = txtProgramId.getText();
        String pName = txtProgramName.getText();
        String pDuration = txtProgramDuration.getText();
        String pFee = txtProgramFee.getText();
        try {
            if(trainingProgramsBOImpl.update(new TrainingProgramsDTO(
                    pId,
                    pName,
                    pDuration,
                    pFee
            ))){

                findAllProgram();
                txtProgramId.setText(null);
                txtProgramName.setText(null);
                txtProgramDuration.setText(null);
                txtProgramFee.setText(null);
            }else {
                new Alert(Alert.AlertType.ERROR, "Something happened! please check this").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something happened! please check this").show();
        }
    }

    public void deleteStudentOnAction(MouseEvent mouseEvent) throws Exception {
        if (new StudentBOImpl().delete(txtStudentId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    public void deleteTrainingProgramText(MouseEvent mouseEvent) throws Exception {
        if (new TrainingProgramsBOImpl().delete(txtProgramId.getText())){
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }
    // end table option

    // clear text field
    public void clearStudentText(MouseEvent mouseEvent) {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();
        txtStudentAge.clear();
        txtPName.clear();
        txtPFee.clear();
    }

    public void clearTrainingProgramText(MouseEvent mouseEvent) {
        txtProgramId.clear();
        txtProgramName.clear();
        txtProgramDuration.clear();
        txtProgramFee.clear();
    }

    //------------------------------------------------------------------------------------

    // start visible & invisible options
    public void invisibleProgramDetails(){
        lblProgramId.setVisible(false);
        txtProgramId.setVisible(false);

        lblProgramName.setVisible(false);
        txtProgramName.setVisible(false);

        lblProgramDuration.setVisible(false);
        txtProgramDuration.setVisible(false);

        lblProgramFee.setVisible(false);
        txtProgramFee.setVisible(false);

        lblAdd.setVisible(false);
        lblDelete.setVisible(false);
        lblRemove.setVisible(false);
        lblClear.setVisible(false);

        tblJoinCol.setVisible(false);
        tblStudentDetails.setVisible(false);
        tblTrainingPrograms.setVisible(false);

        lblSecondText.setVisible(false);

        iconProgramClear.setVisible(false);
        iconProgramDelete.setVisible(false);
        iconProgramUpdate.setVisible(false);
    }

    public void visibleProgramDetails(MouseEvent mouseEvent) {
        lblProgramId.setVisible(true);
        txtProgramId.setVisible(true);

        lblProgramName.setVisible(true);
        txtProgramName.setVisible(true);

        lblProgramDuration.setVisible(true);
        txtProgramDuration.setVisible(true);

        lblProgramFee.setVisible(true);
        txtProgramFee.setVisible(true);

        lblAdd.setVisible(true);
        lblRegister.setVisible(false);

        lblStudentId.setVisible(false);
        txtStudentId.setVisible(false);

        lblStudentName.setVisible(false);
        txtStudentName.setVisible(false);

        lblStudentAddress.setVisible(false);
        txtStudentAddress.setVisible(false);

        lblStudentContact.setVisible(false);
        txtStudentContact.setVisible(false);

        lblTrainingPrograms.setVisible(false);
        txtStudentAge.setVisible(false);

        lblPId.setVisible(false);
        cmbPId.setVisible(false);

        txtPName.setVisible(false);
        txtPFee.setVisible(false);

        tblJoinCol.setVisible(false);
        lblDelete.setVisible(false);

        lblSecondText.setVisible(false);
        tblStudentDetails.setVisible(false);

        tblTrainingPrograms.setVisible(false);
        lblSecondText.setVisible(false);

        lblRemove.setVisible(false);
        lblClear.setVisible(false);

        lblFirstText.setVisible(true);

        pane.setVisible(true);
        iconClear.setVisible(false);
        iconDelete.setVisible(false);
        iconUpdate.setVisible(false);

        iconProgramClear.setVisible(true);
        iconProgramDelete.setVisible(true);
        iconProgramUpdate.setVisible(true);
    }

    public void visibleRegistationDetails(MouseEvent mouseEvent) {
        lblProgramId.setVisible(false);
        txtProgramId.setVisible(false);

        lblProgramName.setVisible(false);
        txtProgramName.setVisible(false);

        lblProgramDuration.setVisible(false);
        txtProgramDuration.setVisible(false);

        lblProgramFee.setVisible(false);
        txtProgramFee.setVisible(false);

        lblAdd.setVisible(false);
        lblRegister.setVisible(true);

        lblStudentId.setVisible(true);
        txtStudentId.setVisible(true);

        lblStudentName.setVisible(true);
        txtStudentName.setVisible(true);

        lblStudentAddress.setVisible(true);
        txtStudentAddress.setVisible(true);

        lblStudentContact.setVisible(true);
        txtStudentContact.setVisible(true);

        lblTrainingPrograms.setVisible(true);
        txtStudentAge.setVisible(true);

        lblPId.setVisible(true);
        cmbPId.setVisible(true);

        txtPName.setVisible(true);
        txtPFee.setVisible(true);

        tblJoinCol.setVisible(false);

        lblDelete.setVisible(false);
        lblClear.setVisible(false);

        lblSecondText.setVisible(false);
        tblStudentDetails.setVisible(false);

        tblTrainingPrograms.setVisible(false);
        lblSecondText.setVisible(false);

        lblRemove.setVisible(false);

        lblFirstText.setVisible(true);

        pane.setVisible(true);
        iconClear.setVisible(true);
        iconDelete.setVisible(true);
        iconUpdate.setVisible(true);

        iconProgramClear.setVisible(false);
        iconProgramDelete.setVisible(false);
        iconProgramUpdate.setVisible(false);
    }

    public void visibleJoinDetails(MouseEvent mouseEvent) {
        lblProgramId.setVisible(false);
        txtProgramId.setVisible(false);

        lblProgramName.setVisible(false);
        txtProgramName.setVisible(false);

        lblProgramDuration.setVisible(false);
        txtProgramDuration.setVisible(false);

        lblProgramFee.setVisible(false);
        txtProgramFee.setVisible(false);

        lblAdd.setVisible(false);
        lblRegister.setVisible(false);

        lblStudentId.setVisible(false);
        txtStudentId.setVisible(false);

        lblStudentName.setVisible(false);
        txtStudentName.setVisible(false);

        lblStudentAddress.setVisible(false);
        txtStudentAddress.setVisible(false);

        lblStudentContact.setVisible(false);
        txtStudentContact.setVisible(false);

        lblTrainingPrograms.setVisible(false);
        txtStudentAge.setVisible(false);

        lblPId.setVisible(false);
        cmbPId.setVisible(false);

        txtPName.setVisible(false);
        txtPFee.setVisible(false);

        lblSecondText.setVisible(false);
        tblStudentDetails.setVisible(false);

        lblRemove.setVisible(false);
        lblClear.setVisible(false);

        tblTrainingPrograms.setVisible(false);
        lblSecondText.setVisible(false);

        tblJoinCol.setVisible(true);
        lblDelete.setVisible(true);

        lblFirstText.setVisible(true);

        iconClear.setVisible(false);
        iconDelete.setVisible(false);
        iconUpdate.setVisible(false);
        pane.setVisible(false);

        iconProgramClear.setVisible(false);
        iconProgramDelete.setVisible(false);
        iconProgramUpdate.setVisible(false);
    }

    public void visibleJoinStudent(MouseEvent mouseEvent) {
        lblProgramId.setVisible(false);
        txtProgramId.setVisible(false);

        lblProgramName.setVisible(false);
        txtProgramName.setVisible(false);

        lblProgramDuration.setVisible(false);
        txtProgramDuration.setVisible(false);

        lblProgramFee.setVisible(false);
        txtProgramFee.setVisible(false);

        lblAdd.setVisible(false);
        lblRegister.setVisible(false);

        lblStudentId.setVisible(false);
        txtStudentId.setVisible(false);

        lblStudentName.setVisible(false);
        txtStudentName.setVisible(false);

        lblStudentAddress.setVisible(false);
        txtStudentAddress.setVisible(false);

        lblStudentContact.setVisible(false);
        txtStudentContact.setVisible(false);

        lblTrainingPrograms.setVisible(false);
        txtStudentAge.setVisible(false);

        lblPId.setVisible(false);
        cmbPId.setVisible(false);

        txtPName.setVisible(false);
        txtPFee.setVisible(false);

        tblJoinCol.setVisible(false);
        lblDelete.setVisible(false);

        lblFirstText.setVisible(false);

        tblTrainingPrograms.setVisible(false);
        lblSecondText.setVisible(false);

        lblSecondText.setVisible(true);
        tblStudentDetails.setVisible(true);

        lblRemove.setVisible(true);
        lblClear.setVisible(false);

        iconClear.setVisible(false);
        iconDelete.setVisible(false);
        iconUpdate.setVisible(false);
        pane.setVisible(false);

        iconProgramClear.setVisible(false);
        iconProgramDelete.setVisible(false);
        iconProgramUpdate.setVisible(false);
    }

    public void visibleTrainingProgram(MouseEvent mouseEvent) {
        lblProgramId.setVisible(false);
        txtProgramId.setVisible(false);

        lblProgramName.setVisible(false);
        txtProgramName.setVisible(false);

        lblProgramDuration.setVisible(false);
        txtProgramDuration.setVisible(false);

        lblProgramFee.setVisible(false);
        txtProgramFee.setVisible(false);

        lblAdd.setVisible(false);
        lblRegister.setVisible(false);

        lblStudentId.setVisible(false);
        txtStudentId.setVisible(false);

        lblStudentName.setVisible(false);
        txtStudentName.setVisible(false);

        lblStudentAddress.setVisible(false);
        txtStudentAddress.setVisible(false);

        lblStudentContact.setVisible(false);
        txtStudentContact.setVisible(false);

        lblTrainingPrograms.setVisible(false);
        txtStudentAge.setVisible(false);

        lblPId.setVisible(false);
        cmbPId.setVisible(false);

        txtPName.setVisible(false);
        txtPFee.setVisible(false);

        tblJoinCol.setVisible(false);
        lblDelete.setVisible(false);
        lblRemove.setVisible(false);

        lblFirstText.setVisible(false);

        lblSecondText.setVisible(false);
        tblStudentDetails.setVisible(false);

        tblTrainingPrograms.setVisible(true);
        lblSecondText.setVisible(true);
        lblClear.setVisible(true);

        iconClear.setVisible(false);
        iconDelete.setVisible(false);
        iconUpdate.setVisible(false);
        pane.setVisible(false);

        iconProgramClear.setVisible(false);
        iconProgramDelete.setVisible(false);
        iconProgramUpdate.setVisible(false);
    }
    // end visible & invisible options

    // move to login form
    public void openLoginForm(MouseEvent mouseEvent) throws IOException {
        Stage window = (Stage) dashBoardFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../views/Login_Form.fxml"))));
    }

    // set logout option
    public void logOut(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Logout");
        alert.setHeaderText("You're about to logout!");
        alert.setContentText("Do you want to save before exiting ?");
        if(alert.showAndWait().get()== ButtonType.OK) {
            stage = (Stage) dashBoardFormContext.getScene().getWindow();
            stage.close();
        }
    }
}
