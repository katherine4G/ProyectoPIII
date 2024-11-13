package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import pack.universityplatform.App;
import utils.Command.CommandInvoker;
import utils.Command.Login;

public class MainController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxRol;
    @FXML
    private AnchorPane an;
    @FXML
    private Button btn_next;
    @FXML
    private Button btn_createAccount;
    @FXML
    private PasswordField PassTxt_password;
    @FXML
    private TextField txt_userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxRol.getItems().addAll("Estudiante", "Profesor", "Administrativo");

    }

    @FXML
    private void getNextPage(ActionEvent event) throws IOException {
        String userId = txt_userId.getText();
        String password = PassTxt_password.getText();
        String selectedRole = comboBoxRol.getValue();

        Login loginCommand = new Login(userId, password, selectedRole);

        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(loginCommand);
        invoker.executeCommand();
    }


    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        App.setRoot("SingIn");

    }



    @FXML
    private void get_passwordUser(ActionEvent event) {

    }

    @FXML
    private void get_userId(ActionEvent event) {

    }


}
