package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import pack.universityplatform.App;

/**
 * FXML Controller class
 *
 * @author kathe
 */
public class SingInController implements Initializable {

    @FXML
    private Button btn_createAccount;
    @FXML
    private Button btn_back;
    @FXML
    private ComboBox<String> comboBoxRol;
    @FXML
    private TextField txt_user3;
    @FXML
    private TextField txt_user32;
    @FXML
    private TextField txt_user33;
    @FXML
    private TextField txt_user34;
    @FXML
    private TextField txt_user35;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         comboBoxRol.getItems().addAll("Estudiante", "Profesor","Administrativo");
    }    

    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        stageMain();
        
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        stageMain();
        
    }
    
    private void stageMain() throws IOException{
        App.setRoot("main"); 
        App.getStage().setWidth(624); 
        App.getStage().setHeight(512); 
        App.getStage().setResizable(false); 
    }
    
}
