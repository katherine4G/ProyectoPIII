package controller;

import APIs.UniversityAPI;
import Model.Faculty;
import Model.University;
import animations.AnimatiosUtils;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import utils.MessagesToUser;

public class InterAdminController implements Initializable {

    @FXML
    private AnchorPane rootPane;
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
    @FXML
    private TableView<Faculty> TableViewFaculty;
    @FXML
    private TableColumn<Faculty, String> col_Faculty_uniName;
    @FXML
    private TableColumn<Faculty, String> col_Faculty_uniCountry;
    @FXML
    private TableColumn<Faculty, String> col_Faculty_uniSede;
    @FXML
    private TableColumn<Faculty, String> col_Faculty_facultyName;
    @FXML
    private TableColumn<Faculty, String> col_Faculty_facultyType;
    @FXML
    private TextField txt_faculty_name;
    @FXML
    private TextField txt_faculty_type;
    @FXML
    private Button btn_faculty_delete_inside;
    @FXML
    private Button btn_faculty_edit_inside;
    @FXML
    private Button btn_Faculty_create_inside;
    @FXML
    private TableView<?> TableViewDepartment;
    @FXML
    private TableColumn<?, ?> col_Department_uniName;
    @FXML
    private TableColumn<?, ?> col_Department_uniCountry;
    @FXML
    private TableColumn<?, ?> col_Department_uniSede;
    @FXML
    private TableColumn<?, ?> col_Department_facultyName;
    @FXML
    private TableColumn<?, ?> col_Department_facultyType;
    @FXML
    private TableColumn<?, ?> col_Department_nameDepart;
    @FXML
    private TextField txt_department_name;
    @FXML
    private Button btn_department_delete_inside;
    @FXML
    private Button btn_department_edit_inside;
    @FXML
    private Button btn_department_create_inside;
    @FXML
    private ComboBox<String> comboBox_faculty_AllUniversities;
    @FXML
    private ComboBox<String> comboBox_department_showUniversities;
    @FXML
    private ComboBox<String> comboBox_department_showFacultyByUniversity;
    @FXML
    private ComboBox<String> comboBox_Courses_showUniversities;
    @FXML
    private ComboBox<String> comboBox_Course_showFacultyByUniversity;
    @FXML
    private ComboBox<String> comboBox_Course_showDepartmentByFacultyByUni;
    @FXML
    private TextField txt_course_NRC;
    @FXML
    private Button btn_course_delete_inside;
    @FXML
    private Button btn_course_edit_inside;
    @FXML
    private Button btn_course_create_inside;
    @FXML
    private TextField txt_course_code;
    @FXML
    private TextField txt_course_nameCourse;
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
    private TableView<?> tableView_professors;
    @FXML
    private TableColumn<?, ?> col_profe_id;
    @FXML
    private TableColumn<?, ?> col_profe_name;
    @FXML
    private TableColumn<?, ?> col_profe_lastName;
    @FXML
    private TableColumn<?, ?> col_profe_email;
    @FXML
    private TableColumn<?, ?> col_profe_course;
    @FXML
    private TableColumn<?, ?> col_profe_university;
    @FXML
    private TableColumn<?, ?> col_profe_faculty;
    @FXML
    private TableColumn<?, ?> col_profe_department;
    @FXML
    private TextField txt_profe_serchBy;
    @FXML
    private ComboBox<?> comboBox_profe_showCourses;
    @FXML
    private ComboBox<?> comboBox_profe_serchByVariable;
    @FXML
    private ComboBox<String> comboBox_profe_showUniversities;
    @FXML
    private ComboBox<?> comboBox_profe_showFacultyByUniversity;
    @FXML
    private ComboBox<?> comboBox_profe_showDepartmentByFacultyByUni;
    @FXML
    private ComboBox<String> comboBox_student_showUniversities;
    @FXML
    private ComboBox<?> comboBox_student_showFacultyByUniversity;
    @FXML
    private ComboBox<?> comboBox_student_showDepartmentByFacultyByUni1;
    @FXML
    private ComboBox<String> comboBox_student_showCourses1;
    @FXML
    private ComboBox<?> comboBox_student_serchByVariable;
    @FXML
    private TextField txt_profesor_id;
    @FXML
    private Button btn_profe_delete_inside;
    @FXML
    private Button btn_profe_edit_inside;
    @FXML
    private Button btn_profe_create_inside;
    @FXML
    private TextField txt_profesor_names;
    @FXML
    private TextField txt_profesor_email;
    @FXML
    private TextField txt_profesor_lastName;
    @FXML
    private AnchorPane AnchorPane_allStudents;
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
    private TableColumn<?, ?> col_students_nameUniversity;
    @FXML
    private TableColumn<?, ?> col_students_universitySede;
    @FXML
    private Button btn_student_viewStudentCourse;
    @FXML
    private TextField txt_student_serchBy;
    @FXML
    private AnchorPane AnchorPane_students_viewCourses_of_anStudent;
    @FXML
    private Button btn_students_volver_ToViewAllStudents;
    @FXML
    private Label txt_student_selectToViewCourses;
    @FXML
    private ListView<?> ListView_student_courses;
    @FXML
    private TextField txt_student_id;
    @FXML
    private Button btn_student_delete_inside;
    @FXML
    private Button btn_student_edit_inside;
    @FXML
    private Button btn_student_create_inside;
    @FXML
    private TextField txt_student_names;
    @FXML
    private TextField txt_student_email;
    @FXML
    private TextField txt_student_lastName;
    @FXML
    private Button btn_viewSolis;
    @FXML
    private Button btn_myProfile;
    @FXML
    private AnchorPane myProfile_form;
    @FXML
    private AnchorPane viewSolis_form;
    
    private UniversityAPI universityAPI = new UniversityAPI();
    
    private MessagesToUser message =new MessagesToUser();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ///universidad///
        showTableView_university();
        TableViewUniversity.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                txt_university_name.setText(newSelection.getUniversityName());
                txt_university_country.setText(newSelection.getUniCountry());
                txt_university_sede.setText(newSelection.getUniSede());
                txt_university_address.setText(newSelection.getUniAdress());
            }
        });
        ///facultad///
        showTableView_faculty();
        try {
            refreshUniversities();
         
        } catch (IOException ex) { Logger.getLogger(InterAdminController.class.getName()).log(Level.SEVERE, null, ex);}
         AnimatiosUtils.applyHoverAnimations(rootPane);
    }
    
    @FXML
    private void admin_myProfile(ActionEvent event) {
        switchForm(event);
        
    }

/////////////////////////////Universities////////////////////////////////////
    @FXML
    private void createUniversity(ActionEvent event) throws IOException { //para moverse entre ventanas(anchors Pane) para crear uni
        switchForm(event);
        loadUniversities();

    }

    public void loadUniversities() throws IOException {
        UniversityAPI universityAPI = new UniversityAPI();
        List<University> universities = universityAPI.getAll();

        ObservableList<University> universityList = FXCollections.observableArrayList(universities);
        TableViewUniversity.setItems(universityList);
    }

    @FXML
    private void university_create_iside(ActionEvent event) throws IOException {
        // Obtener datos del formulario
        String universityName = txt_university_name.getText();
        String country = txt_university_country.getText();
        String sede = txt_university_sede.getText();
        String address = txt_university_address.getText();

        // Crear el JSON con los datos
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("universityName", universityName);
        jsonBody.addProperty("uniCountry", country);
        jsonBody.addProperty("uniSede", sede);
        jsonBody.addProperty("uniAdress", address);

        // Llamar al método de la API para crear la universidad
        UniversityAPI universityAPI = new UniversityAPI();
        String json = jsonBody.toString();
        String token = "123"; // Token provisional
        boolean success = universityAPI.create(json, token);

        if (success) {
            loadUniversities();
            message.showSuccessMessage("Universidad Creada", "La universidad ha sido creada exitosamente.");
            clear();
            refreshUniversities();
            
        } else {
            // Manejar error
            message.showErrorMessage("Error", "No se pudo crear la universidad.");
        }
    }

    @FXML
    private void university_edit_inside(ActionEvent event) throws IOException {
         University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
        if (selectedUniversity == null) {
            message.showErrorMessage("Error", "Selecciona una universidad para editar");
            return;
        }

        // Obtener datos actualizados
        String universityName = txt_university_name.getText();
        String country = txt_university_country.getText();
        String sede = txt_university_sede.getText();
        String address = txt_university_address.getText();

        // Crear JSON para actualización
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("universityName", universityName);
        jsonBody.addProperty("uniCountry", country);
        jsonBody.addProperty("uniSede", sede);
        jsonBody.addProperty("uniAdress", address);

        boolean success = universityAPI.update(
            selectedUniversity.getUniversityId(), 
            jsonBody.toString(), 
            "123"
        );

        if (success) {
            loadUniversities();
            message.showSuccessMessage("Universidad Actualizada", "La universidad ha sido actualizada exitosamente.");
            clear();
            refreshUniversities();
        } else {
            message.showErrorMessage("Error", "No se pudo actualizar la universidad.");
        }
    }

    @FXML
    private void university_delete_inside(ActionEvent event) throws IOException {
        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
        if (selectedUniversity == null) {
            message.showErrorMessage("Error", "Selecciona una universidad para eliminar");
            return;
        }

        boolean confirmed = confirmAction("Eliminar", selectedUniversity);
        if (confirmed) {
            boolean success = universityAPI.delete(
                selectedUniversity.getUniversityId(), 
                "123"
            );

            if (success) {
                loadUniversities();
                message.showSuccessMessage("Universidad Eliminada", "La universidad ha sido eliminada exitosamente.");
                clear();
                refreshUniversities();
            } else {
                message.showErrorMessage("Error", "No se pudo eliminar la universidad.");
            }
        }
        
    }

    private void showTableView_university() {
        col_universityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_universityCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_universitySede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_universityAdress.setCellValueFactory(new PropertyValueFactory<>("uniAdress"));
    }

///////////////////////////Facultys////////////////////////////
    @FXML
    private void createFaculty(ActionEvent event) { //para moverse entre ventanas(anchos Pane) para crear facultades
        switchForm(event);
    }

    @FXML
    private void faculty_create_inside(ActionEvent event) {
        String name = txt_faculty_name.getText();
        String type = txt_faculty_type.getText();
        String token = "123";

    }

    @FXML
    private void faculty_delete_inside(ActionEvent event) {

    }

    @FXML
    private void faculty_edit_inside(ActionEvent event) {
    }

    private void showTableView_faculty() {
        col_Faculty_uniName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_Faculty_uniCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_Faculty_uniSede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_Faculty_facultyName.setCellValueFactory(new PropertyValueFactory<>("facultyName"));
        col_Faculty_facultyType.setCellValueFactory(new PropertyValueFactory<>("facultyType"));

    }

//////////////////////Departments///////////////////////////
    @FXML
    private void createDepartment(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }

    @FXML
    private void department_delete_inside(ActionEvent event) {
    }

    @FXML
    private void department_edit_inside(ActionEvent event) {
    }

    @FXML
    private void department_create_inside(ActionEvent event) {
    }

    ///////////////////////Courses//////////////////////////////////
    @FXML
    private void createCourses(ActionEvent event) {  //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }
       @FXML
    private void course_delete_inside(ActionEvent event) {
    }

    @FXML
    private void course_edit_inside(ActionEvent event) {
    }

    @FXML
    private void course_create_inside(ActionEvent event) {
    }

    ///////////////////////Professors//////////////////////////////////
    @FXML
    private void createTeacher(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }
    
    @FXML
    private void profe_serchBy(ActionEvent event) {
    }

    @FXML
    private void profe_serchByVariable(ActionEvent event) {
    }

    @FXML
    private void profe_delete_inside(ActionEvent event) {
    }

    @FXML
    private void profe_edit_inside(ActionEvent event) {
    }

    @FXML
    private void profe_create_inside(ActionEvent event) {
    }

    ///////////////////////Students//////////////////////////////////
    @FXML
    private void createStudent(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }
      @FXML
    private void student_viewStudentCourse(ActionEvent event) {
        switchForm_student_course(event);
    }

    @FXML
    private void student_serchBy(ActionEvent event) {
    }

    @FXML
    private void student_serchByVariable(ActionEvent event) {
    }

    @FXML
    private void students_btnBack_ToViewAll(ActionEvent event) {
        switchForm_student_course(event);
    }

    @FXML
    private void student_delete_inside(ActionEvent event) {
    }

    @FXML
    private void student_edit_inside(ActionEvent event) {
    }

    @FXML
    private void student_create_inside(ActionEvent event) {
    }
    /////////////////////Solicitudes/////////////////////////
    @FXML
    private void admin_viewSolis(ActionEvent event) {
        switchForm(event);
    }

    /////////////////////Config/////////////////////////
    @FXML
    private void Exit_LoggOut(ActionEvent event) throws IOException {
        App.setRoot("main");
        App.getStage().setWidth(624);
        App.getStage().setHeight(512);
        App.getStage().setResizable(false); // 
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_myProfile) {
            myProfile_form.setVisible(true);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            
        } else if (event.getSource() == btn_createUniversity) {

            University_form.setVisible(true);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);

        } else if (event.getSource() == btn_createFaculty) {

            University_form.setVisible(false);
            Faculty_form.setVisible(true);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);

        } else if (event.getSource() == btn_createDepartment) {

            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(true);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
        } else if (event.getSource() == btn_createCourses) {

            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(true);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
        } else if (event.getSource() == btn_createTeacher) {
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(true);
            Student_form.setVisible(false);
        } else if (event.getSource() == btn_createStudent) {

            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(true);
        } else if (event.getSource() == btn_viewSolis) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(true);
        }
    }
    
    public boolean confirmAction(String action, University university) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Universidad");
        alert.setHeaderText("¿Estás seguro de que deseas " + action.toLowerCase() + " esta universidad?");
        alert.setContentText("Nombre: " + university.getUniversityName() + "\n"
                + "País: " + university.getUniCountry() + "\n"
                + "Sede: " + university.getUniSede() + "\n"
                + "Dirección: " + university.getUniAdress());
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }
    
    private void clear() {
        txt_university_name.setText(" ");
        txt_university_country.setText(" ");
        txt_university_sede.setText(" ");
        txt_university_address.setText(" ");

    }

    public void switchForm_student_course(ActionEvent event) {

        if (event.getSource() == btn_student_viewStudentCourse) {
            AnchorPane_allStudents.setVisible(false);
            AnchorPane_students_viewCourses_of_anStudent.setVisible(true);

        } else if (event.getSource() == btn_students_volver_ToViewAllStudents) {

            AnchorPane_allStudents.setVisible(true);
            AnchorPane_students_viewCourses_of_anStudent.setVisible(false);
        }
     }

    private void loadUniversitiesForComboBox_faculty(ComboBox<String> nameComboBox) throws IOException {
        try {
            List<University> universityList = universityAPI.getAll();

            universityList.sort(Comparator.comparing(University::getUniversityName));

            List<String> universityDetails = new ArrayList<>();
            for (University university : universityList) {
                String universityInfo = String.format("%s, %s, %s",
                        university.getUniversityName(),
                        university.getUniCountry(),
                        university.getUniSede());
                universityDetails.add(universityInfo);
            }

          //  comboBox_faculty_AllUniversities.setItems(FXCollections.observableArrayList(universityDetails));
            nameComboBox.setItems(FXCollections.observableArrayList(universityDetails));

        } catch (IOException e) {
            message.showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
    }
    private void refreshUniversities() throws IOException{
        loadUniversitiesForComboBox_faculty(comboBox_faculty_AllUniversities);
        loadUniversitiesForComboBox_faculty(comboBox_department_showUniversities);
        loadUniversitiesForComboBox_faculty(comboBox_Courses_showUniversities);
        loadUniversitiesForComboBox_faculty(comboBox_profe_showUniversities); 
        loadUniversitiesForComboBox_faculty(comboBox_student_showUniversities);

    }
    
}//fin clase
