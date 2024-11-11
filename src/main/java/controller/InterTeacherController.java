package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;

/**
 * FXML Controller class

* Initializes the controller class.
 * @author kathe
 */
public class InterTeacherController implements Initializable {

    @FXML
    private AnchorPane asignStudentToCourse_form;
    @FXML
    private AnchorPane NewAsignation_form;
    @FXML
    private Button btn_studentToCourse;
    @FXML
    private Button btn_createAssignation;
    @FXML
    private Button btn_Exit;
    @FXML
    private Label label_userName;
    @FXML
    private Button btn_viewMyCourse;
    @FXML
    private Button btn_registerToCourse;
    @FXML
    private Button btn_viewProfile;
    @FXML
    private AnchorPane perfil_form;
    @FXML
    private AnchorPane viewMyCourses_form;
    @FXML
    private AnchorPane registerCourse_form;
    @FXML
    private TableView<?> TableView_students;
    @FXML
    private TableColumn<?, ?> col_students_id;
    @FXML
    private TableColumn<?, ?> col_students_name;
    @FXML
    private TableColumn<?, ?> col_students_lastName;
    @FXML
    private TableColumn<?, ?> col_students_email;
    @FXML
    private TableView<?> tableView_course;
    @FXML
    private TableColumn<?, ?> col_course_NRC;
    @FXML
    private TableColumn<?, ?> col_course_codigo;
    @FXML
    private TableColumn<?, ?> col_course_courseName;
    @FXML
    private TableColumn<?, ?> col_course_departmentName;
    @FXML
    private TableColumn<?, ?> col_course_facultyName;
    @FXML
    private TableColumn<?, ?> col_course_universityName;
    @FXML
    private TextField txt_student_serchBy;
    @FXML
    private ComboBox<String> comboBox_student_serchByVariable;
    @FXML
    private Button btn_student_delete_inside;
    @FXML
    private Button btn_student_create_inside;
    @FXML
    private TextField txt_student_id;
    @FXML
    private Button btn_student_delete_inside1;
    @FXML
    private Button btn_student_edit_inside;
    @FXML
    private Button btn_student_create_inside1;
    @FXML
    private TextField txt_student_names;
    @FXML
    private Button btn_revisarAssignation;
    @FXML
    private AnchorPane revisarAsignattions_form;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AsignStudentToCourse(ActionEvent event) {
         switchForm(event);
    }

    @FXML
    private void createAssignation(ActionEvent event) {
         switchForm(event);
    }

    @FXML
    private void Exit_LoggOut(ActionEvent event) throws IOException {
        App.setRoot("main"); 
        App.getStage().setWidth(624); 
        App.getStage().setHeight(512); 
        App.getStage().setResizable(false); 
    }
        
    public void switchForm(ActionEvent event) {

        if (event.getSource() == btn_viewProfile) {
            perfil_form.setVisible(true);
            viewMyCourses_form.setVisible(false);
            registerCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(false);       
            NewAsignation_form.setVisible(false);  
            revisarAsignattions_form.setVisible(false);  
          
        }else if (event.getSource() == btn_viewMyCourse){
            perfil_form.setVisible(false);
            viewMyCourses_form.setVisible(true);
            registerCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(false);       
            NewAsignation_form.setVisible(false);
            revisarAsignattions_form.setVisible(false);  
  
        }else if (event.getSource() == btn_registerToCourse){
            perfil_form.setVisible(false);
            viewMyCourses_form.setVisible(false);
            registerCourse_form.setVisible(true);
            asignStudentToCourse_form.setVisible(false);       
            NewAsignation_form.setVisible(false);
            revisarAsignattions_form.setVisible(false);  
  
        }else if (event.getSource() == btn_studentToCourse){
            perfil_form.setVisible(false);
            viewMyCourses_form.setVisible(false);
            registerCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(true);       
            NewAsignation_form.setVisible(false);
            revisarAsignattions_form.setVisible(false);  
  
        }else if (event.getSource() == btn_createAssignation){
            perfil_form.setVisible(false);
            viewMyCourses_form.setVisible(false);
            registerCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(false);       
            NewAsignation_form.setVisible(true);
            revisarAsignattions_form.setVisible(false);
            
        }else if (event.getSource() == btn_revisarAssignation){         
            perfil_form.setVisible(false);
            viewMyCourses_form.setVisible(false);
            registerCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(false);       
            NewAsignation_form.setVisible(false);
            revisarAsignattions_form.setVisible(true);  
        }
    }

    @FXML
    private void view_myCourses(ActionEvent event) {
        switchForm(event);
    }

    @FXML
    private void registerToCourse(ActionEvent event) {
        switchForm(event);
    }

    @FXML
    private void viewProfile(ActionEvent event) {
        switchForm(event);
    }

    @FXML
    private void revisarAssignation(ActionEvent event) {
        switchForm(event);
    }
    
    @FXML
    private void student_serchBy(ActionEvent event) {
    }

    @FXML
    private void student_serchByVariable(ActionEvent event) {
    }

    @FXML
    private void student_delete_inside(ActionEvent event) {
    }

    @FXML
    private void student_create_inside(ActionEvent event) {
    }

    @FXML
    private void student_edit_inside(ActionEvent event) {
    }

    
}
