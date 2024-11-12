package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;

public class InterStudentController implements Initializable {

    @FXML
    private AnchorPane first_view;
    @FXML
    private Button btn_Exit;
    @FXML
    private Button btm_MatricularCourse;
    @FXML
    private Button btn_viewCourses;
    @FXML
    private Button btn_viewAssignaments;
    @FXML
    private AnchorPane MatricCourse_form;
    @FXML
    private AnchorPane viewCourse_form;
    @FXML
    private AnchorPane viewAssignaments_form;
    @FXML
    private Button btn_viewProfile;
    @FXML
    private AnchorPane perfil_form;
    @FXML
    private Label label_InfoUser;
    @FXML
    private TableView<?> tableView_availableCorses;
    @FXML
    private TableColumn<?, ?> col_matric_NRC;
    @FXML
    private TableColumn<?, ?> col_matric_code;
    @FXML
    private TableColumn<?, ?> col_matric_course;
    @FXML
    private TableColumn<?, ?> col_matric_professor;
    @FXML
    private Button btn_matric_back;
    @FXML
    private Button btn_matric_next;
    @FXML
    private Button btn_matric_selectAnCourse;
    @FXML
    private ListView<?> ListView_currentCourseSelect;
    @FXML
    private Label label_status_inform;
    @FXML
    private TableView<?> TableView_viewCourses;
    @FXML
    private TableColumn<?, ?> col_viewCourse_NRC;
    @FXML
    private TableColumn<?, ?> col_viewCourse_code;
    @FXML
    private TableColumn<?, ?> col_viewCourse_courseName;
    @FXML
    private TableColumn<?, ?> col_viewCourse_professor;
    @FXML
    private Button btn_viewCourses_back;
    @FXML
    private Button btn_viewCourses_next;
    @FXML
    private TableView<?> tableView_assignaments;
    @FXML
    private TableColumn<?, ?> col_viewAssig_course;
    @FXML
    private TableColumn<?, ?> col_viewAssig_professor;
    @FXML
    private TableColumn<?, ?> col_viewAssig_title;
    @FXML
    private TableColumn<?, ?> col_viewAssig_description;
    @FXML
    private TableColumn<?, ?> col_viewAssig_deadLine;
    @FXML
    private TableColumn<?, ?> col_viewAssig_archive;
    @FXML
    private TableColumn<?, ?> col_viewAssig_status;
    @FXML
    private Button btn_viewAssign_deleteSubmission;
    @FXML
    private Button btn_viewAssign_editSubmission;
    @FXML
    private Button btn_viewAssign_addSubmission;
    @FXML
    private Button btn_viewAssign_viewCalification;
    @FXML
    private Button btn_viewAssign_saveChanges;
    @FXML
    private TextField txt_viewAssign_titleHomeWork;
    @FXML
    private Button btn_viewAssign_addArchive;
    @FXML
    private TextArea txt_viewAssign_descriptionHomeWork;
    @FXML
    private Label label_viewAssign_nameArchive;
    @FXML
    private Label label_commentProfe;
    @FXML
    private Label label_notaObtenida;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Exit_LoggOut(ActionEvent event) throws IOException {
        App.setRoot("main"); 
        App.getStage().setWidth(624); 
        App.getStage().setHeight(512); 
        App.getStage().setResizable(false); 
    }

    @FXML
    private void matricularCourse(ActionEvent event) {
        switchForm( event);
    }

    @FXML
    private void viewCourses(ActionEvent event) {
        switchForm( event);
    }

    @FXML
    private void viewAssignaments(ActionEvent event) {
        switchForm( event);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_viewProfile) {
            perfil_form.setVisible(true);
            MatricCourse_form.setVisible(false);
            viewCourse_form.setVisible(false);
            viewAssignaments_form.setVisible(false);           
        }else if (event.getSource() == btm_MatricularCourse) {
            perfil_form.setVisible(false);
            MatricCourse_form.setVisible(true);
            viewCourse_form.setVisible(false);
            viewAssignaments_form.setVisible(false);           
        }else if (event.getSource() == btn_viewCourses)
        { perfil_form.setVisible(false);
            MatricCourse_form.setVisible(false);
            viewCourse_form.setVisible(true);
            viewAssignaments_form.setVisible(false);      
        }else if (event.getSource() == btn_viewAssignaments)
        { perfil_form.setVisible(false);
            MatricCourse_form.setVisible(false);
            viewCourse_form.setVisible(false);
            viewAssignaments_form.setVisible(true);   
        }
    }

    @FXML
    private void viewProfile(ActionEvent event) {
    }

    @FXML
    private void viewAssign_deleteSubmission(ActionEvent event) {
    }

    @FXML
    private void viewAssign_editSubmission(ActionEvent event) {
    }

    @FXML
    private void viewAssign_addSubmission(ActionEvent event) {
    }

    @FXML
    private void viewAssign_viewCalification(ActionEvent event) {
    }

    @FXML
    private void btn_viewAssign_saveChanges(ActionEvent event) {
    }
}
