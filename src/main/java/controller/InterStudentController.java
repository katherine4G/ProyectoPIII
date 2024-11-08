package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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

        if (event.getSource() == btm_MatricularCourse) {

            MatricCourse_form.setVisible(true);
            viewCourse_form.setVisible(false);
            viewAssignaments_form.setVisible(false);           
          


        }else if (event.getSource() == btn_viewCourses)
        {
            MatricCourse_form.setVisible(false);
            viewCourse_form.setVisible(true);
            viewAssignaments_form.setVisible(false);  
           
            
        }else if (event.getSource() == btn_viewAssignaments)
        { 
            MatricCourse_form.setVisible(false);
            viewCourse_form.setVisible(false);
            viewAssignaments_form.setVisible(true);  
       
           
        }
    }
}
