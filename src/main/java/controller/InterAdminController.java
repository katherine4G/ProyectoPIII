package controller;

import APIs.UniversityAPI;
import Model.University;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;
import java.util.List;


public class InterAdminController implements Initializable {

    @FXML
    private Label label_userName;
    @FXML
    private Button btn_Exit;
    @FXML
    private Button btn_createStudent;
    @FXML
    private Button btn_createTeacher;
    @FXML
    private Button btn_createUniversity;
    @FXML
    private Button btn_createFaculty;
    @FXML
    private Button btn_createCourses;
    @FXML
    private Button btn_createDepartment;
    @FXML
    private AnchorPane University_form;
    @FXML
    private AnchorPane Faculty_form;
    @FXML
    private AnchorPane Department_form;
    @FXML
    private AnchorPane Course_form;
    @FXML
    private AnchorPane Teacher_form;
    @FXML
    private AnchorPane Student_form;
    @FXML
    private TextField txt_university_name;
    @FXML
    private TextField txt_university_country;
    @FXML
    private TextField txt_university_sede;
    @FXML
    private TextField txt_university_address;
    @FXML
    private TableView<University> TableViewUniversity;
    @FXML
    private TableColumn<University, Integer> col_universityNumber;
    @FXML
    private TableColumn<University, String> col_universityName;
    @FXML
    private TableColumn<University, String> col_universityCountry;
    @FXML
    private TableColumn<University, String> col_universitySede;
    @FXML
    private TableColumn<University, String> col_universityAdress;
    @FXML
    private Button btn_University_create_inside;
    @FXML
    private Button btn_University_edit_inside;
    @FXML
    private Button btn_University_delete_inside;
    
  //  private ObservableList<University> universityList = FXCollections.observableArrayList();
     private UniversityAPI universityAPI = new UniversityAPI();





    @Override
    public void initialize(URL url, ResourceBundle rb) {
      showTableView_university();  
        
    }    

    @FXML
    private void Exit_LoggOut(ActionEvent event) throws IOException {
        App.setRoot("main"); 
        App.getStage().setWidth(624); 
        App.getStage().setHeight(512); 
        App.getStage().setResizable(false); // 
    }

    @FXML
    private void createStudent(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }

    @FXML
    private void createTeacher(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }
/////////////////////////////////////////////////////////////////
    @FXML
    private void createUniversity(ActionEvent event) { //para moverse entre ventanas(anchors Pane) para crear uni
        switchForm(event);
        loadUniversities(); 

    }
    // Método para mostrar columnas en la tabla
    private void showTableView_university() {
        col_universityNumber.setCellValueFactory(new PropertyValueFactory<>("universityId"));
        col_universityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_universityCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_universitySede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_universityAdress.setCellValueFactory(new PropertyValueFactory<>("uniAdress"));         
    }

    public void loadUniversities() {
        try {
            List<University> universityList = universityAPI.getAllUniversities();
            ObservableList<University> observableUniversityList = FXCollections.observableArrayList(universityList);

            TableViewUniversity.setItems(observableUniversityList);
        } catch (IOException e) { 
            showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
    }

    @FXML
    private void university_create_iside(ActionEvent event) throws IOException {
        String name = txt_university_name.getText();
        String country = txt_university_country.getText();
        String sede = txt_university_sede.getText();
        String address = txt_university_address.getText();
        String token = "123";  

        String jsonBody = String.format(
            "{ \"universityName\": \"%s\", \"uniCountry\": \"%s\", \"uniSede\": \"%s\", \"uniAdress\": \"%s\" }", 
            name, country, sede, address);

        UniversityAPI api = new UniversityAPI();
        boolean success = api.createUniversity(jsonBody, token);

        if (success) {
            showSuccessMessage("Universidad creada", "La universidad ha sido creada exitosamente.");
            clear();
            loadUniversities(); 
        } else {
            showErrorMessage("Error", "Hubo un problema al crear la universidad. Inténtalo de nuevo.");
        }
    }


    @FXML
    private void university_edit_inside(ActionEvent event) {
    }

    @FXML
    private void university_delete_inside(ActionEvent event) {
    }
    
/////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void createFaculty(ActionEvent event) { //para moverse entre ventanas(anchos Pane) para crear facultades
        switchForm(event);
    }

    @FXML
    private void createCourses(ActionEvent event) {
        switchForm(event);
    }

    @FXML
    private void createDepartment(ActionEvent event) {
        switchForm(event);
    }
    
     public void switchForm(ActionEvent event) {

        if (event.getSource() == btn_createUniversity) {

            University_form.setVisible(true);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);           
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);


        }else if (event.getSource() == btn_createFaculty)
        {
       
            University_form.setVisible(false);
            Faculty_form.setVisible(true);
            Department_form.setVisible(false);           
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            
        }else if (event.getSource() == btn_createDepartment)
        {
       
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(true);           
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
        }else if (event.getSource() == btn_createCourses)
        {
       
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);           
            Course_form.setVisible(true);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
        }else if (event.getSource() == btn_createTeacher)
        {      
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);           
            Course_form.setVisible(false);
            Teacher_form.setVisible(true);
            Student_form.setVisible(false);
        }else if (event.getSource() == btn_createStudent)
        {
       
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);           
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(true);
        }
    }
    private void showSuccessMessage(String title, String content) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    private void showErrorMessage(String title, String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    private void clear(){
        txt_university_name.setText(" ");
        txt_university_country.setText(" ");
        txt_university_sede.setText(" ");
        txt_university_address.setText(" ");
        
        
    }
  
}
