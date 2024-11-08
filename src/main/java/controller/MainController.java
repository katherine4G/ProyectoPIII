
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pack.universityplatform.App;


public class MainController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxRol;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btn_next;
    @FXML
    private TextField txt_user;
    @FXML
    private TextField txt_password;
    @FXML
    private Button btn_createAccount;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxRol.getItems().addAll("Estudiante", "Profesor","Administrativo");
        
    } 


    @FXML
    private void getNextPage(ActionEvent event) throws IOException {
        
         String userType = comboBoxRol.getValue();

        // Validar los campos de entrada
        if ( userType == null) {
            showAlert(AlertType.WARNING, "Form Error!", "Please fill in all fields.");
            return;
        }
        
        loadUserDashboard(userType);
        
    }

    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        App.setRoot("SingIn");
    
    }
    private void loadUserDashboard(String userType) throws IOException {
        switch (userType) {
            case "Estudiante" -> {
                App.setRoot("InterStudent");
                App.getStage().setWidth(870); 
                App.getStage().setHeight(650); 
                App.getStage().setResizable(true); 
               // App.getStage().setMaximized(true);  // Pantalla completa solo para estudiante
            }
            case "Profesor" -> {
                App.setRoot("InterTeacher");
               // App.getStage().setMaximized(true);  // Pantalla completa solo para profesor
                App.getStage().setWidth(850); // Establecer el ancho deseado
                App.getStage().setHeight(660); // Establecer la altura deseada
                App.getStage().setResizable(true); // 
            }
            case "Administrativo" -> {
                App.setRoot("InterAdmin");
                //App.getStage().setMaximized(true); // Pantalla completa solo para administrativo
                App.getStage().setWidth(850); // Establecer el ancho deseado
                App.getStage().setHeight(660); // Establecer la altura deseada
                App.getStage().setResizable(true); //
            }
            default -> throw new IllegalArgumentException("Unexpected value: " + userType);
        }
    }


     private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    
}
