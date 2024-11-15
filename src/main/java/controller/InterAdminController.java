package controller;

import APIs.CourseAPI;
import APIs.DepartmentAPI;
import APIs.FacultyAPI;
import APIs.UniversityAPI;
import Manage.UserSession;
import Model.Course;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Pagination;
import org.json.JSONObject;
import utils.MessagesToUser;
import utils.Thread.BaseAPI;
import utils.Thread.ClearFieldsThread;
import utils.Thread.LoadDataThread;
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
    private TableView<Course> tableView_course;
    @FXML
    private TableColumn<Course, String> col_course_NRC;
    @FXML
    private TableColumn<Course, String> col_course_codigo;
    @FXML
    private TableColumn<Course, String> col_course_courseName;
    @FXML
    private TableColumn<Course, String> col_course_departmentName;
    @FXML
    private TableColumn<Course, String>col_course_facultyName;
    @FXML
    private TableColumn<Course, String> col_course_universityName;
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
    @FXML
    private Button btn_University_seccionarTabla;
    @FXML
    private Button btn_faculty_seccionarTabla;
    @FXML
    private Button btn_facu_getPrevTable;
    @FXML
    private Button btn_facu_getNextTable;
    @FXML
    private Label label_faculty_curentPage;
    @FXML
    private Button btn_univ_PrevTable;
    @FXML
    private Button btn_univ_NextTable;
    @FXML
    private Label label_university_curentPage;
    @FXML
    private Pagination Pagination_Department;
    @FXML
    private Pagination Pagination_Course;
    @FXML
    private Pagination Pagination_Professor;
    @FXML
    private Pagination Pagination_Student;
    @FXML
    private Button btn_faculty_seccionarTabla1;
    @FXML
    private Button btn_faculty_seccionarTabla2;
    @FXML
    private Button btn_faculty_seccionarTabla3;
    @FXML
    private Button btn_faculty_seccionarTabla31;
       
    MessagesToUser message = new MessagesToUser();   
    
    String token = TokenManager.getInstance().getToken();    
    UniversityAPI universityAPI = new UniversityAPI();
    FacultyAPI facultyAPI = new FacultyAPI();
    DepartmentAPI departmentAPI =new DepartmentAPI();
    CourseAPI courseAPI =new CourseAPI();

    private Map<String, Integer> universityMap = new HashMap<>();
    private Map<String, Integer> facultyMap = new HashMap<>();
    private Map<String, Integer> departmentMap = new HashMap<>();
    private Map<String, Integer> courseMap = new HashMap<>();
    
    // Almacena selecciones de los comboBox según sea el caso
    Map<String, List<String>> facultiesByUniversityMap = new HashMap<>();
    
    private int currentPage = 1;
    private final int pageSize = 10;
    private LoadDataThread<Faculty> loadDataThread;
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
        TokenManager.getInstance().clearToken(); // se cierra el token al cerrar sesión
        App.setRoot("main");
        App.getStage().setWidth(624);
        App.getStage().setHeight(512);
        App.getStage().setResizable(false);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_myProfile){myProfile_form.setVisible(true);University_form.setVisible(false); Faculty_form.setVisible(false); Department_form.setVisible(false); Course_form.setVisible(false); Teacher_form.setVisible(false);  Student_form.setVisible(false); viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createUniversity) { myProfile_form.setVisible(false); University_form.setVisible(true); Faculty_form.setVisible(false); Department_form.setVisible(false); Course_form.setVisible(false); Teacher_form.setVisible(false);Student_form.setVisible(false);viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createFaculty) { myProfile_form.setVisible(false); University_form.setVisible(false); Faculty_form.setVisible(true);  Department_form.setVisible(false);Course_form.setVisible(false);Teacher_form.setVisible(false);Student_form.setVisible(false);viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createDepartment) { myProfile_form.setVisible(false); University_form.setVisible(false); Faculty_form.setVisible(false);  Department_form.setVisible(true);Course_form.setVisible(false);Teacher_form.setVisible(false); Student_form.setVisible(false); viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createCourses) {myProfile_form.setVisible(false); University_form.setVisible(false);Faculty_form.setVisible(false);Department_form.setVisible(false);Course_form.setVisible(true);Teacher_form.setVisible(false);Student_form.setVisible(false);viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createTeacher) {myProfile_form.setVisible(false);University_form.setVisible(false); Faculty_form.setVisible(false); Department_form.setVisible(false); Course_form.setVisible(false); Teacher_form.setVisible(true); Student_form.setVisible(false); viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_createStudent) {myProfile_form.setVisible(false); University_form.setVisible(false);Faculty_form.setVisible(false); Department_form.setVisible(false);Course_form.setVisible(false); Teacher_form.setVisible(false); Student_form.setVisible(true);viewSolis_form.setVisible(false);}
        else if (event.getSource() == btn_viewSolis) {myProfile_form.setVisible(false); University_form.setVisible(false); Faculty_form.setVisible(false); Department_form.setVisible(false); Course_form.setVisible(false); Teacher_form.setVisible(false); Student_form.setVisible(false);viewSolis_form.setVisible(true);}
        clearAllFields();
    }

    private void clearAllFields() {
         ClearFieldsThread clearThread = new ClearFieldsThread(rootPane);
         clearThread.start();
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
        showTableView_university();
    }
    public void loadUniversities() throws IOException {
       LoadDataThread<University> loadDataThread = new LoadDataThread<>(TableViewUniversity, universityAPI, message);
       loadDataThread.start(); 

    }
    
    private void handleUniversityOperation(String action, JSONObject data, int universityId) throws IOException {
        UniversityAPI universityAPI = new UniversityAPI();
        boolean success = false;
        if (null != action) switch (action) {
                case "crea" -> success = universityAPI.create(data.toString(), token);
                case "actualiza" -> success = universityAPI.update(universityId, data.toString(), token);
                case "elimina" -> success = universityAPI.delete(data.toString(), token);
                default -> {
            }
         }

        if (success) {
            loadUniversities();
            message.showSuccessMessage("Universidad " + action + "da", "La universidad ha sido " + action + "da exitosamente.");
            clearAllFields();
        } else {
            message.showErrorMessage("Error", "No se pudo " + action + "r la universidad.");
        }
    }

    @FXML
    private void university_create_iside(ActionEvent event) throws IOException {
          String universityName = txt_university_name.getText();
          String country = txt_university_country.getText();
          String sede = txt_university_sede.getText();
          String address = txt_university_address.getText();

          JSONObject json = new JSONObject();
          json.put("universityName", universityName);
          json.put("country", country);
          json.put("sede", sede);
          json.put("address", address);

          handleUniversityOperation("crea", json, -1); 
    }

    @FXML
    private void university_edit_inside(ActionEvent event) throws IOException {
          University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
          if (selectedUniversity == null) {message.showErrorMessage("Error", "Selecciona una universidad para editar");return;}

          int universityId = selectedUniversity.getUniversityId();
          String universityName = txt_university_name.getText();
          String country = txt_university_country.getText();
          String sede = txt_university_sede.getText();
          String address = txt_university_address.getText();

          JSONObject json = new JSONObject();
          json.put("universityId", universityId);
          json.put("newName", universityName);
          json.put("newCountry", country);
          json.put("newSede", sede);
          json.put("newAddress", address);

          handleUniversityOperation("actualiza", json, universityId);
    }

    @FXML
    private void university_delete_inside(ActionEvent event) throws IOException {
          University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
          if (selectedUniversity == null) {
              message.showErrorMessage("Error", "Selecciona una universidad para eliminar");
              return;
          }

          int universityId = selectedUniversity.getUniversityId();
          JSONObject json = new JSONObject();
          json.put("universityId", universityId);

          boolean confirmed = confirmAction("Eliminar", selectedUniversity);
          if (confirmed) {
              handleUniversityOperation("elimina", json, universityId);
          }
    }

    private void showTableView_university() {
        col_universityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_universityCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_universitySede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_universityAdress.setCellValueFactory(new PropertyValueFactory<>("uniAdress"));
    }
    
    @FXML
    private void university_seleccionarTabla(ActionEvent event) {
        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();

        if (selectedUniversity != null) {
            txt_university_name.setText(selectedUniversity.getUniversityName());
            txt_university_country.setText(selectedUniversity.getUniCountry());
            txt_university_sede.setText(selectedUniversity.getUniSede());
            txt_university_address.setText(selectedUniversity.getUniAdress());
        } else {
            txt_university_name.clear();
            txt_university_country.clear();
            txt_university_sede.clear();
            txt_university_address.clear();
        }
    }
    public boolean confirmAction(String action, University university) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Universidad");
        alert.setHeaderText("¿Estás seguro de que deseas " + action.toLowerCase() + " esta universidad?");
        alert.setContentText("Nombre: " + university.getUniversityName() + "\n"+ "País: " + university.getUniCountry() + "\n"+ "Sede: " + university.getUniSede() + "\n"+ "Dirección: " + university.getUniAdress());
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

    ///////////////////////////Facultys////////////////////////////
    @FXML
    private void createFaculty(ActionEvent event) throws IOException { //para moverse entre ventanas(anchos Pane) para crear facultades
        switchForm(event);
        loadFaculties();
        showTableView_faculty();
        loadUniversitiesForComboBox_faculty(comboBox_faculty_AllUniversities);
    }

    @FXML
    private void faculty_create_inside(ActionEvent event) throws IOException {
        String name = txt_faculty_name.getText();
        String type = txt_faculty_type.getText();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        Integer universityId = universityMap.get(selectedUniversity); 

        String json = createFacultyJsonCrear(name, type, universityId); 

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
        if (selectedFaculty == null) { message.showErrorMessage("Error", "Selecciona una facultad para editar"); return;}

        int facultyId = selectedFaculty.getFacultyId();
        String facultyName = txt_faculty_name.getText();
        String facultyType = txt_faculty_type.getText();
        String selectedUniversity = comboBox_faculty_AllUniversities.getSelectionModel().getSelectedItem();
        
        Integer universityId = universityMap.get(selectedUniversity);

        if (universityId == null) { message.showErrorMessage("Error", "Por favor, selecciona una universidad."); return;}

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
//        LoadDataThread<Faculty> loadDataThread = new LoadDataThread<>(TableViewFaculty, facultyAPI, message);
//       loadDataThread.start(); 
        BaseAPI<Faculty> facultyAPI = new FacultyAPI();
        loadDataThread = new LoadDataThread<>(TableViewFaculty, facultyAPI, message, currentPage, pageSize);

//        btn_facu_getPrevTable.setOnAction(event -> goToPreviousPage());
//        btn_facu_getNextTable.setOnAction(event -> goToNextPage());

        loadDataThread.setPage(currentPage);//
        loadDataThread.start();
    }

    private String createFacultyJsonCrear(String facultyName, String facultyType, int universityId) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("facultyName", facultyName);
        jsonBody.addProperty("facultyType", facultyType);
        jsonBody.addProperty("universityId", universityId);  
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
        alert.setContentText("¿Estás seguro de que deseas " + action.toLowerCase() + " la facultad " + faculty.getFacultyName() + "'?");
        ButtonType buttonConfirm = new ButtonType("Confirmar");
        ButtonType buttonCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonConfirm;
    }
    private void loadUniversitiesForComboBox_faculty(ComboBox<String> comboBox) throws IOException {
      try {
          List<University> universityList = universityAPI.getAll();
          List<String> universityDetails = new ArrayList<>(); // Lista para almacenar los detalles completos

          for (University university : universityList) {
              // Crear una cadena con el nombre de la universidad, el país y la sede
              String universityDetail = university.getUniversityName() 
                                        + " (" + university.getUniCountry() + ", " + university.getUniSede() + ")";

              // Agregar al ComboBox la cadena concatenada
              universityDetails.add(universityDetail);

              // Asociar el nombre de la universidad con su ID para futuras referencias
              universityMap.put(universityDetail, university.getUniversityId());

              // Crear una lista para almacenar las facultades de esta universidad
              List<String> faculties = new ArrayList<>();
              for (Faculty faculty : facultyAPI.getFacultiesByUniversity(university.getUniversityId())) { // Obtener facultades por universidad
                  String facultyDetail = faculty.getFacultyName();
                  faculties.add(facultyDetail);
                  facultyMap.put(facultyDetail, faculty.getFacultyId());
              }
              // Asignar la lista de facultades al mapa de universidades
              facultiesByUniversityMap.put(universityDetail, faculties);
          }

          // Establecer los detalles de las universidades (nombre, país, sede) en el ComboBox
          comboBox.setItems(FXCollections.observableArrayList(universityDetails));
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


    ////////////////////// Departments ///////////////////////////
    @FXML
    private void createDepartment(ActionEvent event) throws IOException {         
        switchForm(event);
        showTableView_department();
        loadDepartments();
        loadUniversitiesForComboBox_faculty(comboBox_department_showUniversities);
       // loadFacultiesForComboBox_department(comboBox_department_showFacultyByUniversity);
        setupUniversityComboBoxListener(); 
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
        } else {message.showErrorMessage("Error", "No se pudo actualizar el departamento.");}
    }

    @FXML
    private void department_delete_inside(ActionEvent event) throws IOException {
        Department selectedDepartment = TableViewDepartment.getSelectionModel().getSelectedItem();
        if (selectedDepartment == null) {message.showErrorMessage("Error", "Selecciona un departamento para eliminar");return;}
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
            } else {message.showErrorMessage("Error", "No se pudo eliminar el departamento.");}
        }
    }

    public void loadDepartments() throws IOException {
       LoadDataThread<Department> loadDataThread = new LoadDataThread<>(TableViewDepartment, departmentAPI, message);
       loadDataThread.start(); 
    }

    private boolean confirmAction(String action, Department department) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Departamento");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas " + action.toLowerCase() + " el departamento '" + department.getNameDepartment() + "'?");
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
                String universityInfo = String.format("%s, %s",faculty.getFacultyName(),faculty.getFacultyType()); 
                facultyDetails.add(universityInfo);
                facultyMap.put(universityInfo, faculty.getFacultyId()); 
            }
            nameComboBox.setItems(FXCollections.observableArrayList(facultyDetails));
        } catch (IOException e) {
            message.showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
    }
//private void loadUniversitiesForComboBox_faculty(ComboBox<String> comboBox) throws IOException {
//    List<University> universityList = universityAPI.getAll();
//    List<String> universityNames = new ArrayList<>();
//    
//    for (University university : universityList) {
//        universityNames.add(university.getUniversityName());
//        universityMap.put(university.getUniversityName(), university.getUniversityId());
//
//        // Agrega las facultades a `facultiesByUniversityMap`
//        List<String> faculties = new ArrayList<>();
//        for (Faculty faculty : university.getFaculties()) {
//            String facultyDetail = faculty.getFacultyName();
//            faculties.add(facultyDetail);
//            facultyMap.put(facultyDetail, faculty.getFacultyId());
//        }
//        facultiesByUniversityMap.put(university.getUniversityName(), faculties);
//    }
//
//    comboBox.setItems(FXCollections.observableArrayList(universityNames));
//}
    private void setupUniversityComboBoxListener() {
        comboBox_department_showUniversities.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                List<String> faculties = facultiesByUniversityMap.getOrDefault(newValue, new ArrayList<>());
                comboBox_department_showFacultyByUniversity.setItems(FXCollections.observableArrayList(faculties));}});
    }
       ///////////////////////Courses//////////////////////////////////
    @FXML
    private void createCourses(ActionEvent event) throws IOException {  //para moverse entre ventanas(anchors Pane)
        switchForm(event);
        loadCourses();
        showTableView_course();
        loadUniversitiesForComboBox_faculty(comboBox_Courses_showUniversities);
        loadFacultiesForComboBox_department(comboBox_Course_showFacultyByUniversity);
        setupUniversityComboBoxListener(); 
        loadDepaForComboBox__course(comboBox_Course_showDepartmentByFacultyByUni);
    }
    @FXML
    private void course_create_inside(ActionEvent event) throws IOException {
        String NRC = txt_course_NRC.getText();
        String codeCourse = txt_course_code.getText();
        String nameCourse = txt_course_nameCourse.getText();
        String selectedDepa = comboBox_Course_showDepartmentByFacultyByUni.getSelectionModel().getSelectedItem();
        Integer idDepartment = departmentMap.get(selectedDepa);

        if (codeCourse == null || codeCourse.isEmpty()) {message.showErrorMessage("Error", "El campo Código del Curso no puede estar vacío."); return; }
        if (nameCourse == null || nameCourse.isEmpty()) { message.showErrorMessage("Error", "El campo Nombre del Curso no puede estar vacío.");return;}
        if (idDepartment == null) {message.showErrorMessage("Error", "Por favor, selecciona un departamento.");return;}
        warningsCourse(NRC);

        JsonObject json = new JsonObject();
        json.addProperty("NRC", NRC);
        json.addProperty("codeCourse", codeCourse);
        json.addProperty("nameCourse", nameCourse);
        json.addProperty("idDepartment", idDepartment);

        boolean success = new CourseAPI().create(json.toString(), token);
        if (success) {
            loadCourses();
            message.showSuccessMessage("Curso Creado", "El curso ha sido creado exitosamente.");
            clearAllFields();
        } else { message.showErrorMessage("Error", "No se pudo crear el curso.");}
    }

    @FXML
    private void course_edit_inside(ActionEvent event) throws IOException {
        Course selectedCourse = (Course) tableView_course.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) { message.showErrorMessage("Error", "Selecciona un curso para editar"); return; }

        String NRC = selectedCourse.getNRC(); 
        String newNRC = txt_course_NRC.getText();
        String codeCourse = txt_course_code.getText();
        String nameCourse = txt_course_nameCourse.getText();
        String selectedDepa = comboBox_Courses_showUniversities.getSelectionModel().getSelectedItem();
        Integer idDepartment = departmentMap.get(selectedDepa);

        warningsCourse(newNRC);
        JsonObject json = new JsonObject();
        json.addProperty("NRC", newNRC);
        json.addProperty("codeCourse", codeCourse);
        json.addProperty("nameCourse", nameCourse);
        json.addProperty("idDepartment", idDepartment);

        boolean success = new CourseAPI().update(NRC, json.toString(), token); // Actualizamos usando el NRC como ID

        if (success) {
            loadCourses();
            message.showSuccessMessage("Curso Actualizado", "El curso ha sido actualizado exitosamente.");
            clearAllFields();
        } else { message.showErrorMessage("Error", "No se pudo actualizar el curso.");}
    }
    @FXML
    private void course_delete_inside(ActionEvent event) throws IOException {
        Course selectedCourse = (Course) tableView_course.getSelectionModel().getSelectedItem();
        if (selectedCourse == null) { message.showErrorMessage("Error", "Selecciona un curso para eliminar");return; }
        String NRC = selectedCourse.getNRC(); 
        boolean confirmed = confirmAction_course("Eliminar", selectedCourse);

        if (confirmed) {
            boolean success = new CourseAPI().delete(NRC, token); 

            if (success) {
                loadCourses();
                message.showSuccessMessage("Curso Eliminado", "El curso ha sido eliminado exitosamente.");
                clearAllFields();
            } else {
                message.showErrorMessage("Error", "No se pudo eliminar el curso.");
            }
        }
    }
    public void loadCourses() throws IOException {
        LoadDataThread<Course> loadDataThread = new LoadDataThread<>(tableView_course, courseAPI, message);
       loadDataThread.start(); 
    }
    private void showTableView_course() {
        col_course_NRC.setCellValueFactory(new PropertyValueFactory<>("NRC"));
        col_course_codigo.setCellValueFactory(new PropertyValueFactory<>("codeCourse"));
        col_course_courseName.setCellValueFactory(new PropertyValueFactory<>("nameCourse"));
        col_course_departmentName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment() != null ? cellData.getValue().getDepartment().getNameDepartment() : "No asignado"));
        col_course_facultyName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment() != null && cellData.getValue().getDepartment().getFaculty() != null? cellData.getValue().getDepartment().getFaculty().getFacultyName() : "No asignado"));
        col_course_universityName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDepartment() != null && cellData.getValue().getDepartment().getFaculty() != null&& cellData.getValue().getDepartment().getFaculty().getUniversity() != null? cellData.getValue().getDepartment().getFaculty().getUniversity().getUniversityName() : "No asignado"));
    }
    private void loadDepaForComboBox__course(ComboBox<String> nameComboBox) throws IOException {
        try {
            List<Department> depaList = new DepartmentAPI().showAllWithFaculty(); 
            depaList.sort(Comparator.comparing(Department::getNameDepartment));
            List<String> departmentDetails = new ArrayList<>();

            for (Department department : depaList) {
                String info = String.format("%s, %s", department.getNameDepartment(), department.getFaculty() != null ? department.getFaculty().getFacultyName() : " f ");
                departmentMap.put(info, department.getIdDepartment());
                departmentDetails.add(info);
            }
            nameComboBox.setItems(FXCollections.observableArrayList(departmentDetails));

        } catch (IOException e) {
            message.showErrorMessage("Error", "No se pudieron cargar los departamentos.");
        }
    }
      private boolean confirmAction_course(String action, Course course) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Curso");
        alert.setHeaderText(null);
        alert.setContentText("¿Estás seguro de que deseas " + action.toLowerCase() + " el curso "+course.getNameCourse() +"\n NRC:'"+course.getNRC() +"\n código" +course.getCodeCourse()+ "'?");
        ButtonType buttonConfirm = new ButtonType("Confirmar");
        ButtonType buttonCancel = new ButtonType("Cancelar", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(buttonConfirm, buttonCancel);
        Optional<ButtonType> result = alert.showAndWait();
        return result.isPresent() && result.get() == buttonConfirm;
    }
    private void course_seleccionarTabla(ActionEvent event) {
        Course selectedCourse = (Course) tableView_course.getSelectionModel().getSelectedItem();
        if (selectedCourse != null) {
            txt_course_code.setText(selectedCourse.getCodeCourse());
            txt_course_nameCourse.setText(selectedCourse.getNameCourse());
            txt_course_NRC.setText(selectedCourse.getNRC());
            
            if (selectedCourse.getDepartment() != null) {
                comboBox_Course_showDepartmentByFacultyByUni.getSelectionModel().select(String.format("%s, %s", selectedCourse.getDepartment().getNameDepartment(),selectedCourse.getDepartment().getFaculty() != null  ? selectedCourse.getDepartment().getFaculty().getFacultyName(): "No Faculty"));
            } else {
                comboBox_Course_showDepartmentByFacultyByUni.getSelectionModel().clearSelection();
            }
        } else {
            clearAllFields();
            comboBox_Course_showDepartmentByFacultyByUni.getSelectionModel().clearSelection();
        }
    }
    private void warningsCourse(String NRC){
        if (NRC == null || NRC.isEmpty()) {message.showErrorMessage("Error", "El campo NRC no puede estar vacío."); return;}
        if (!NRC.matches("[0-9]+")) {message.showErrorMessage("Error", "El NRC solo puede contener números.");return;}
        if (NRC.length() > 5) {message.showErrorMessage("Error", "El NRC no puede tener más de 5 dígitos.");return;}
        if (NRC.length() < 0) {message.showErrorMessage("Error", "El NRC no puede tener menos de 5 dígitos.");return;}
    }
    


    @FXML
    private void facu_getPrevTable(ActionEvent event) {
    }

    @FXML
    private void facu_getNextTable(ActionEvent event) {
        
    }


}//fin clase
