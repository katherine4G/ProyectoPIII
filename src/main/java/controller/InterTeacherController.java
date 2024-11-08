/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;

/**
 * FXML Controller class
 *
 * @author kathe
 */
public class InterTeacherController implements Initializable {

    @FXML
    private AnchorPane CreateCourse_form;
    @FXML
    private AnchorPane asignStudentToCourse_form;
    @FXML
    private AnchorPane NewAsignation_form;
     @FXML
    private Button btn_createCourse;   
    @FXML
    private Button btn_studentToCourse;
    @FXML
    private Button btn_createAssignation;
    @FXML
    private Button btn_Exit;
    @FXML
    private Label label_userName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void createCourse(ActionEvent event) {
        switchForm(event);
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
        App.setRoot("main"); // Cambiar a la pantalla principal
        App.getStage().setWidth(624); // Establecer el ancho deseado
        App.getStage().setHeight(512); // Establecer la altura deseada
        App.getStage().setResizable(false); // 
    }
        
    public void switchForm(ActionEvent event) {

        if (event.getSource() == btn_createCourse) {

            CreateCourse_form.setVisible(true);
            asignStudentToCourse_form.setVisible(false);
            NewAsignation_form.setVisible(false);           
          

        }else if (event.getSource() == btn_studentToCourse)
        {
            CreateCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(true);
            NewAsignation_form.setVisible(false);  
           
            
        }else if (event.getSource() == btn_createAssignation)
        { 
            CreateCourse_form.setVisible(false);
            asignStudentToCourse_form.setVisible(false);
            NewAsignation_form.setVisible(true);  
       
           
        }
    }
    
}
