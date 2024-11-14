package controller;

import APIs.DepartmentAPI;
import APIs.FacultyAPI;
import APIs.UniversityAPI;
import APIs.UserAPI;
import Manage.UserSession;
import Model.Department;
import Model.Faculty;
import Model.University;
import Model.User;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
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
import java.util.Map;
import java.util.Optional;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import org.json.JSONObject;
import utils.MessagesToUser;
import utils.TokenManager;

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
    private TableView<Department> TableViewDepartment;
    @FXML
    private TableColumn<Department, String> col_Department_uniName;
    @FXML
    private TableColumn<Department, String> col_Department_uniCountry;
    @FXML
    private TableColumn<Department, String> col_Department_uniSede;
    @FXML
    private TableColumn<Department, String> col_Department_facultyName;
    @FXML
    private TableColumn<Department, String> col_Department_facultyType;
    @FXML
    private TableColumn<Department, String>col_Department_nameDepart;
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
    @FXML
    private Label label_InfoUser;

    UniversityAPI universityAPI = new UniversityAPI();
    FacultyAPI facultyAPI = new FacultyAPI();
    
    private MessagesToUser message = new MessagesToUser();
    
    private Map<String, Integer> universityMap = new HashMap<>();
    private Map<String, Integer> facultyMap = new HashMap<>();

    String token = TokenManager.getInstance().getToken();
    @FXML
    private Button btn_University_seccionarTabla;
    @FXML
    private Button btn_faculty_seccionarTabla;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // Configuración para universidades
        showTableView_university();

        // Configuración para facultades
        showTableView_faculty();

        showTableView_department();
    }

    ////////////////////// Departments ///////////////////////////
    @FXML
    private void createDepartment(ActionEvent event) throws IOException {      
        switchForm(event);
        loadDepartments();
        loadUniversitiesForComboBox_faculty(comboBox_department_showUniversities);
        loadFacultiesForComboBox_department(comboBox_department_showFacultyByUniversity);
    }

    @FXML
    private void department_create_inside(ActionEvent event) throws IOException {
        String nameDepartment = txt_department_name.getText();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        String selectedFaculty = comboBox_department_showFacultyByUniversity.getSelectionModel().getSelectedItem();

        Integer universityId = universityMap.get(selectedUniversity);
        Integer facultyId = facultyMap.get(selectedFaculty);

        JsonObject json = new JsonObject();  
        json.addProperty("nameDepartment", nameDepartment);
        json.addProperty("facultyId", facultyId);
        json.addProperty("universityId", universityId);
        
        boolean success = new DepartmentAPI().create(json.toString(), token);

        if (success) {
            loadDepartments();
            message.showSuccessMessage("Departamento Creado", "El departamento ha sido creado exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo crear el departamento.");
        }
    }

    @FXML
    private void department_edit_inside(ActionEvent event) throws IOException {
        Department selectedDepartment = TableViewDepartment.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            message.showErrorMessage("Error", "Selecciona un departamento para editar");
            return;
        }

        int idDepartment = selectedDepartment.getIdDepartment();
        String departmentName = txt_department_name.getText();
        String selectedFaculty = comboBox_department_showFacultyByUniversity.getSelectionModel().getSelectedItem();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        
        Integer facultyId = facultyMap.get(selectedFaculty);
        Integer universityId = universityMap.get(selectedUniversity);

        if (facultyId == null) {
            message.showErrorMessage("Error", "Por favor, selecciona una facultad.");
            return;
        }

        JsonObject json = new JsonObject();  
        json.addProperty("idDepartment", idDepartment);
        json.addProperty("nameDepartment", departmentName);
        json.addProperty("facultyId", facultyId);
        json.addProperty("universityId", universityId);

        boolean success = new DepartmentAPI().update(idDepartment, json.toString(), token);

        if (success) {
            loadDepartments();
            message.showSuccessMessage("Departamento Actualizado", "El departamento ha sido actualizado exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo actualizar el departamento.");
        }
    }

    @FXML
    private void department_delete_inside(ActionEvent event) throws IOException {
        Department selectedDepartment = TableViewDepartment.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {
            message.showErrorMessage("Error", "Selecciona un departamento para eliminar");
            return;
        }

        int idDepartment = selectedDepartment.getIdDepartment();
        boolean confirmed = confirmAction("Eliminar", selectedDepartment);

        if (confirmed) {
            JsonObject json = new JsonObject();
            json.addProperty("idDepartment", idDepartment);

            boolean success = new DepartmentAPI().delete(json.toString(), token);

            if (success) {
                loadDepartments();
                message.showSuccessMessage("Departamento Eliminado", "El departamento ha sido eliminado exitosamente.");
                clearAllFields();
            } else {
                message.showErrorMessage("Error", "No se pudo eliminar el departamento.");
            }
        }
    }

    public void loadDepartments() throws IOException {
        List<Department> departments = new DepartmentAPI().showAllWithFacultyAndUniversity();
        ObservableList<Department> departmentList = FXCollections.observableArrayList(departments);
        TableViewDepartment.setItems(departmentList);
    }


    private boolean confirmAction(String action, Department department) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Departamento");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas " + action.toLowerCase() + " el departamento '" 
                             + department.getNameDepartment() + "'?");

        ButtonType buttonConfirm = new ButtonType("Confirmar");
        ButtonType buttonCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonConfirm;
    }

    private void showTableView_department() {
        col_Department_nameDepart.setCellValueFactory(new PropertyValueFactory<>("nameDepartment"));       
        col_Department_facultyName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFaculty() != null ? cellData.getValue().getFaculty().getFacultyName() : "No asignada"));
        col_Department_facultyType.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFaculty() != null  ? cellData.getValue().getFaculty().getFacultyType() : "No asignada"));
        col_Department_uniName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFaculty() != null  && cellData.getValue().getFaculty().getUniversity() != null ? cellData.getValue().getFaculty().getUniversity().getUniversityName() : "No asignada"));
        col_Department_uniCountry.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getFaculty() != null  && cellData.getValue().getFaculty().getUniversity() != null ? cellData.getValue().getFaculty().getUniversity().getUniCountry() : "No asignada"));
        col_Department_uniSede.setCellValueFactory(cellData ->  new SimpleStringProperty(cellData.getValue().getFaculty() != null && cellData.getValue().getFaculty().getUniversity() != null ? cellData.getValue().getFaculty().getUniversity().getUniSede() : "No asignada"));
    }

    private void loadFacultiesForComboBox_department(ComboBox<String> nameComboBox) throws IOException {
          try {
            List<Faculty> facultyList = facultyAPI.getAll();
            facultyList.sort(Comparator.comparing(Faculty::getFacultyName));
            List<String> facultyDetails = new ArrayList<>();
            for (Faculty faculty : facultyList) {
                String universityInfo = String.format("%s, %s",
                        faculty.getFacultyName(),
                        faculty.getFacultyType());
                facultyDetails.add(universityInfo);
                facultyMap.put(universityInfo, faculty.getFacultyId());  // Guardar el ID en el mapa
            }
            nameComboBox.setItems(FXCollections.observableArrayList(facultyDetails));
        } catch (IOException e) {
            message.showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
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
        TokenManager.getInstance().clearToken(); // Limpia el token al cerrar sesión
        App.setRoot("main");
        App.getStage().setWidth(624);
        App.getStage().setHeight(512);
        App.getStage().setResizable(false);
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
            viewSolis_form.setVisible(false);

        } else if (event.getSource() == btn_createUniversity) {
            myProfile_form.setVisible(false);
            University_form.setVisible(true);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(false);
            

        } else if (event.getSource() == btn_createFaculty) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(true);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(false);

        } else if (event.getSource() == btn_createDepartment) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(true);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(false);
            
        } else if (event.getSource() == btn_createCourses) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(true);
            Teacher_form.setVisible(false);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(false);
           
        } else if (event.getSource() == btn_createTeacher) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(true);
            Student_form.setVisible(false);
            viewSolis_form.setVisible(false);
        
        } else if (event.getSource() == btn_createStudent) {
            myProfile_form.setVisible(false);
            University_form.setVisible(false);
            Faculty_form.setVisible(false);
            Department_form.setVisible(false);
            Course_form.setVisible(false);
            Teacher_form.setVisible(false);
            Student_form.setVisible(true);
            viewSolis_form.setVisible(false);
           
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
        clearAllFields();
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

    private void clearAllFields() {
        rootPane.getChildren().forEach(node -> {
            if (node instanceof TextField textField) {
                textField.clear();
            }
        });
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
   //////////////////////Profile User///////////////////////////
    @FXML
    private void admin_myProfile(ActionEvent event) throws IOException {
        switchForm(event);
        User user = UserSession.getCurrentUser();

        if (user != null) {
            String u = user.getIdUser();  
            if (u != null && !u.isEmpty()) { label_InfoUser.setText(user.toString());}
            else {label_InfoUser.setText("ID de usuario vacío.");}
            
        } else {label_InfoUser.setText("No hay usuario en sesión.");}
    }

/////////////////////////////Universities////////////////////////////////////
    @FXML
    private void createUniversity(ActionEvent event) throws IOException { //para moverse entre ventanas(anchors Pane) para crear uni
        switchForm(event);
        loadUniversities();
       // AnimatiosUtils.applyHoverAnimations(rootPane);
    }

    public void loadUniversities() throws IOException {
        UniversityAPI universityAPI = new UniversityAPI();
        List<University> universities = universityAPI.getAll();

        ObservableList<University> universityList = FXCollections.observableArrayList(universities);
        TableViewUniversity.setItems(universityList);
    }
    @FXML
    private void university_create_iside(ActionEvent event) throws IOException {

        String universityName = txt_university_name.getText();
        String country = txt_university_country.getText();
        String sede = txt_university_sede.getText();
        String address = txt_university_address.getText();

        String json = createUniversityJsonCrear(universityName, country, sede, address);

        UniversityAPI universityAPI = new UniversityAPI();
        String token = TokenManager.getInstance().getToken(); 
        boolean success = universityAPI.create(json, token);

        if (success) {
            loadUniversities();
            message.showSuccessMessage("Universidad Creada", "La universidad ha sido creada exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo crear la universidad.");
        }
        loadUniversities();

    }

    @FXML
    private void university_edit_inside(ActionEvent event) throws IOException {
        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
        if (selectedUniversity == null) {
            message.showErrorMessage("Error", "Selecciona una universidad para editar");
            return;
        }
        int universityId = selectedUniversity.getUniversityId(); 
        String universityName = txt_university_name.getText();
        String country = txt_university_country.getText();
        String sede = txt_university_sede.getText();
        String address = txt_university_address.getText();

        // Crear JSON para la actualización
        JSONObject json = new JSONObject();
        json.put("universityId", universityId);
        json.put("newName", universityName);
        json.put("newCountry", country);
        json.put("newSede", sede);
        json.put("newAddress", address);

        UniversityAPI universityAPI = new UniversityAPI();
        boolean success = universityAPI.update(universityId, json.toString(), token); 

        if (success) {
            loadUniversities();
            message.showSuccessMessage("Universidad Actualizada", "La universidad ha sido actualizada exitosamente.");
            clearAllFields();
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

        int universityId = selectedUniversity.getUniversityId();

        boolean confirmed = confirmAction("Eliminar", selectedUniversity);
        if (confirmed) {
            UniversityAPI universityAPI = new UniversityAPI();
            JSONObject json = new JSONObject();
            json.put("universityId", universityId);

            boolean success = universityAPI.delete(json.toString(), token);
            if (success) {
                loadUniversities();
                message.showSuccessMessage("Universidad Eliminada", "La universidad ha sido eliminada exitosamente.");
                clearAllFields();
            } else {
                message.showErrorMessage("Error", "No se pudo eliminar la universidad.");
            }
        }
    }

    private String createUniversityJsonCrear(String universityName, String country, String sede, String address) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("universityName", universityName);
        jsonBody.addProperty("uniCountry", country);
        jsonBody.addProperty("uniSede", sede);
        jsonBody.addProperty("uniAdress", address);
        return jsonBody.toString();
    }

    private void showTableView_university() {
        col_universityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_universityCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_universitySede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_universityAdress.setCellValueFactory(new PropertyValueFactory<>("uniAdress"));
    }
    
    @FXML
    private void university_seleccionarTabla(ActionEvent event) {
        // Obtén el elemento seleccionado en la tabla de universidades al presionar el botón
        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();

        if (selectedUniversity != null) {
            txt_university_name.setText(selectedUniversity.getUniversityName());
            txt_university_country.setText(selectedUniversity.getUniCountry());
            txt_university_sede.setText(selectedUniversity.getUniSede());
            txt_university_address.setText(selectedUniversity.getUniAdress());
        } else {
            // Limpiar los campos si no hay selección
            txt_university_name.clear();
            txt_university_country.clear();
            txt_university_sede.clear();
            txt_university_address.clear();
        }
    }
     
    ///////////////////////////Facultys////////////////////////////
    @FXML
    private void createFaculty(ActionEvent event) throws IOException { //para moverse entre ventanas(anchos Pane) para crear facultades
        loadFaculties();
        switchForm(event);
        loadUniversitiesForComboBox_faculty(comboBox_faculty_AllUniversities);
    }

    @FXML
    private void faculty_create_inside(ActionEvent event) throws IOException {
        String name = txt_faculty_name.getText();
        String type = txt_faculty_type.getText();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        Integer universityId = universityMap.get(selectedUniversity); 

//        if (universityId == null) {
//            message.showErrorMessage("Error", "Por favor, selecciona una universidad.");
//            return;
//        }
        String json = createFacultyJsonCrear(name, type, universityId);  // Pasar el universityId

        boolean success = facultyAPI.create(json, token);

        if (success) {
            loadFaculties();
            message.showSuccessMessage("Facultad Creada", "La facultad ha sido creada exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo crear la facultad.");
        }
    }
    @FXML
    private void faculty_edit_inside(ActionEvent event) throws IOException {
        Faculty selectedFaculty = TableViewFaculty.getSelectionModel().getSelectedItem();
        if (selectedFaculty == null) {
            message.showErrorMessage("Error", "Selecciona una facultad para editar");
            return;
        }

        int facultyId = selectedFaculty.getFacultyId();
        String facultyName = txt_faculty_name.getText();
        String facultyType = txt_faculty_type.getText();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        
        Integer universityId = universityMap.get(selectedUniversity);

        if (universityId == null) {
            message.showErrorMessage("Error", "Por favor, selecciona una universidad.");
            return;
        }

        JSONObject json = new JSONObject();
        json.put("facultyId", facultyId);
        json.put("facultyName", facultyName); 
        json.put("facultyType", facultyType); 
        json.put("universityId", universityId); 

        boolean success = facultyAPI.update(facultyId, json.toString(), token);
        if (success) {
            loadFaculties();
            message.showSuccessMessage("Facultad Actualizada", "La facultad ha sido actualizada exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo actualizar la facultad.");
        }
    }

    @FXML
    private void faculty_delete_inside(ActionEvent event) throws IOException {
        Faculty selectedFaculty = TableViewFaculty.getSelectionModel().getSelectedItem();
        if (selectedFaculty == null) {
            message.showErrorMessage("Error", "Selecciona una facultad para eliminar");
            return;
        }

        int facultyId = selectedFaculty.getFacultyId();
        boolean confirmed = confirmAction("Eliminar", selectedFaculty);
        if (confirmed) {
            JSONObject json = new JSONObject();
            json.put("facultyId", facultyId);

            boolean success = facultyAPI.delete(json.toString(), token);
            if (success) {loadFaculties();
                message.showSuccessMessage("Facultad Eliminada", "La facultad ha sido eliminada exitosamente.");
                clearAllFields();
            } else {message.showErrorMessage("Error", "No se pudo eliminar la facultad.");}
        }
    }


    public void loadFaculties() throws IOException {   
        List<Faculty> faculties = facultyAPI.getAllWithUniversity(); 
        ObservableList<Faculty> facultyList = FXCollections.observableArrayList(faculties);
        TableViewFaculty.setItems(facultyList);

        faculties.forEach(faculty -> System.out.println(faculty));
    }

    private String createFacultyJsonCrear(String facultyName, String facultyType, int universityId) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("facultyName", facultyName);
        jsonBody.addProperty("facultyType", facultyType);
        jsonBody.addProperty("universityId", universityId);  // Agregar el ID de la universidad
        return jsonBody.toString();
    }

    private void showTableView_faculty() {
        col_Faculty_facultyName.setCellValueFactory(new PropertyValueFactory<>("facultyName"));
        col_Faculty_facultyType.setCellValueFactory(new PropertyValueFactory<>("facultyType"));
        
        col_Faculty_uniName.setCellValueFactory(cellData -> new SimpleStringProperty( cellData.getValue().getUniversity() != null ? cellData.getValue().getUniversity().getUniversityName() : "No asignada") );
        col_Faculty_uniCountry.setCellValueFactory(cellData ->new SimpleStringProperty(cellData.getValue().getUniversity() != null? cellData.getValue().getUniversity().getUniCountry():"No asignada"));
        col_Faculty_uniSede.setCellValueFactory(cellData ->  new SimpleStringProperty( cellData.getValue().getUniversity() != null ? cellData.getValue().getUniversity().getUniSede(): "No asignada"));
    }

    private boolean confirmAction(String action, Faculty faculty) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Facultad");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas " + action.toLowerCase() + " la facultad '" 
                             + faculty.getFacultyName() + "'?");

        ButtonType buttonConfirm = new ButtonType("Confirmar");
        ButtonType buttonCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);

        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonConfirm;
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
                universityMap.put(universityInfo, university.getUniversityId());  // Guardar el ID en el mapa
            }

            nameComboBox.setItems(FXCollections.observableArrayList(universityDetails));
        } catch (IOException e) {
            message.showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
    }


    @FXML
    private void faculty_seleccionarTabla(ActionEvent event) {
        Faculty selectedFaculty = TableViewFaculty.getSelectionModel().getSelectedItem();

        if (selectedFaculty != null) {
            txt_faculty_name.setText(selectedFaculty.getFacultyName());
            txt_faculty_type.setText(selectedFaculty.getFacultyType());

            if (selectedFaculty.getUniversity() != null) {
                comboBox_faculty_AllUniversities.getSelectionModel().select(
                        String.format("%s, %s, %s", 
                            selectedFaculty.getUniversity().getUniversityName(),
                            selectedFaculty.getUniversity().getUniCountry(),
                            selectedFaculty.getUniversity().getUniSede()));
            } else {
                comboBox_faculty_AllUniversities.getSelectionModel().clearSelection();
            }
        } else {
            txt_faculty_name.clear();
            txt_faculty_type.clear();
            comboBox_faculty_AllUniversities.getSelectionModel().clearSelection();
        }
    }



}//fin clase
