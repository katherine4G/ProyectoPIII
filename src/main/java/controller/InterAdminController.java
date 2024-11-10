package controller;

import APIs.UniversityAPI;
import Model.Faculty;
import Model.University;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;

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

    private UniversityAPI universityAPI = new UniversityAPI();

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
        loadUniversitiesForComboBox_faculty();

        // AnimatiosUtils.applyHoverAnimations(rootPane);
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
            // Si la universidad se creó con éxito, recargar la lista de universidades
            loadUniversities();
            showSuccessMessage("Universidad Creada", "La universidad ha sido creada exitosamente.");
            clear();
        } else {
            // Manejar error
            showErrorMessage("Error", "No se pudo crear la universidad.");
        }
    }

    @FXML
    private void university_edit_inside(ActionEvent event) throws IOException {
         University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
    if (selectedUniversity == null) {
        showErrorMessage("Error", "Selecciona una universidad para editar");
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
        showSuccessMessage("Universidad Actualizada", "La universidad ha sido actualizada exitosamente.");
    } else {
        showErrorMessage("Error", "No se pudo actualizar la universidad.");
    }
    }

    @FXML
    private void university_delete_inside(ActionEvent event) throws IOException {
        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
    if (selectedUniversity == null) {
        showErrorMessage("Error", "Selecciona una universidad para eliminar");
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
            showSuccessMessage("Universidad Eliminada", "La universidad ha sido eliminada exitosamente.");
        } else {
            showErrorMessage("Error", "No se pudo eliminar la universidad.");
        }
    }
        
    }

    private void showTableView_university() {
        col_universityName.setCellValueFactory(new PropertyValueFactory<>("universityName"));
        col_universityCountry.setCellValueFactory(new PropertyValueFactory<>("uniCountry"));
        col_universitySede.setCellValueFactory(new PropertyValueFactory<>("uniSede"));
        col_universityAdress.setCellValueFactory(new PropertyValueFactory<>("uniAdress"));
    }

//      public void loadUniversities() {
//        try {
//            List<University> universityList = universityAPI.getAllUniversities();
//            ObservableList<University> observableUniversityList = FXCollections.observableArrayList(universityList);
//
//            TableViewUniversity.setItems(observableUniversityList);
//        } catch (IOException e) { 
//            showErrorMessage("Error", "No se pudieron cargar las universidades.");
//        }
//    }
//    @FXML
//    private void university_create_iside(ActionEvent event) throws IOException {
//        String name = txt_university_name.getText();
//        String country = txt_university_country.getText();
//        String sede = txt_university_sede.getText();
//        String address = txt_university_address.getText();
//        String token = "123";  
//
//        String jsonBody = String.format(
//            "{ \"universityName\": \"%s\", \"uniCountry\": \"%s\", \"uniSede\": \"%s\", \"uniAdress\": \"%s\" }", 
//            name, country, sede, address);
//
//        UniversityAPI api = new UniversityAPI();
//        boolean success = api.createUniversity(jsonBody, token);
//
//        if (success) {
//            showSuccessMessage("Universidad creada", "La universidad ha sido creada exitosamente.");
//            clear();
//            loadUniversities(); 
//        } else {
//            showErrorMessage("Error", "Hubo un problema al crear la universidad. Inténtalo de nuevo.");
//        }
//    }
//    @FXML
//    private void university_edit_inside(ActionEvent event) {
//        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
//        if (selectedUniversity == null) {
//            showErrorMessage("Error", "Por favor, selecciona una universidad para editar.");
//            return;
//        }
//        String updatedName = txt_university_name.getText();
//        String updatedCountry = txt_university_country.getText();
//        String updatedSede = txt_university_sede.getText();
//        String updatedAddress = txt_university_address.getText();
//        String token = "123"; // Reemplaza con el token actual del usuario
//
//        String jsonBody = String.format(
//            "{ \"universityName\": \"%s\", \"uniCountry\": \"%s\", \"uniSede\": \"%s\", \"uniAdress\": \"%s\" }",
//            updatedName, updatedCountry, updatedSede, updatedAddress
//        );
//
//        boolean success = universityAPI.updateUniversity(selectedUniversity.getUniversityId(), jsonBody, token);
//
//        if (success) {
//            showSuccessMessage("Universidad actualizada", "La universidad ha sido actualizada exitosamente.");
//            loadUniversities(); // Recargar la tabla
//        } else {
//            showErrorMessage("Error", "Hubo un problema al actualizar la universidad.");
//        }
//    }
//    @FXML
//    private void university_delete_inside(ActionEvent event) {
//        University selectedUniversity = TableViewUniversity.getSelectionModel().getSelectedItem();
//        if (selectedUniversity == null) {
//            showErrorMessage("Error", "Por favor, selecciona una universidad para eliminar.");
//            return;
//        }
//
//        String token = "123"; // Reemplaza con el token actual del usuario
//        boolean success = universityAPI.deleteUniversity(selectedUniversity.getUniversityId(), token);
//
//        if (success) {
//            showSuccessMessage("Universidad eliminada", "La universidad ha sido eliminada exitosamente.");
//            loadUniversities(); // Recargar la tabla
//        } else {
//            showErrorMessage("Error", "Hubo un problema al eliminar la universidad.");
//        }
//    }
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

    private void loadUniversitiesForComboBox_faculty() {
        try {
            // Obtenemos la lista de universidades desde la API
            List<University> universityList = universityAPI.getAll();

            // Ordenamos la lista por nombre, país y sede usando un Comparator
            universityList.sort(Comparator.comparing(University::getUniversityName)
                    .thenComparing(University::getUniCountry)
                    .thenComparing(University::getUniSede));

            // Creamos una lista de detalles para mostrar en el ComboBox
            List<String> universityDetails = new ArrayList<>();
            for (University university : universityList) {
                String universityInfo = String.format("%s, %s, %s",
                        university.getUniversityName(),
                        university.getUniCountry(),
                        university.getUniSede());
                universityDetails.add(universityInfo);
            }

            // Asignamos los detalles al ComboBox
            comboBox_faculty_AllUniversities.setItems(FXCollections.observableArrayList(universityDetails));

        } catch (IOException e) {
            showErrorMessage("Error", "No se pudieron cargar las universidades.");
        }
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

    ///////////////////////Professors//////////////////////////////////
    @FXML
    private void createTeacher(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }

    ///////////////////////Students//////////////////////////////////
    @FXML
    private void createStudent(ActionEvent event) { //para moverse entre ventanas(anchors Pane)
        switchForm(event);
    }

    @FXML
    private void Exit_LoggOut(ActionEvent event) throws IOException {
        App.setRoot("main");
        App.getStage().setWidth(624);
        App.getStage().setHeight(512);
        App.getStage().setResizable(false); // 
    }

    public void switchForm(ActionEvent event) {

        if (event.getSource() == btn_createUniversity) {

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

    private void clear() {
        txt_university_name.setText(" ");
        txt_university_country.setText(" ");
        txt_university_sede.setText(" ");
        txt_university_address.setText(" ");

    }

    private boolean confirmAction(String action, University university) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(action + " Universidad");
        alert.setHeaderText("¿Estás seguro de que deseas " + action.toLowerCase() + " esta universidad?");
        alert.setContentText("Nombre: " + university.getUniversityName() + "\n"
                + "País: " + university.getUniCountry() + "\n"
                + "Sede: " + university.getUniSede() + "\n"
                + "Dirección: " + university.getUniAdress());
        return alert.showAndWait().orElse(ButtonType.CANCEL) == ButtonType.OK;
    }

}//fin clase
