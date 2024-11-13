package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import pack.universityplatform.App;
import utils.Command.CommandInvoker;
import utils.Command.CreateAccount;


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
    private ComboBox<String> comboBoxRole;
    @FXML
    private TextField txt_userName;
    @FXML
    private TextField txt_userLastName;
    @FXML
    private TextField txt_userEmail;
    @FXML
    private TextField txt_userPassword;
    @FXML
    private TextField txt_userId;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBoxRole.getItems().addAll("Estudiante", "Profesor", "Administrativo");
    }

    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        String userId = txt_userId.getText();
        String userName = txt_userName.getText();
        String userLastName = txt_userLastName.getText();
        String userEmail = txt_userEmail.getText();
        String userPassword = txt_userPassword.getText();
        String role = comboBoxRole.getValue(); 

        CreateAccount createAccountCommand = new CreateAccount(userId, userName, userLastName, userEmail, userPassword, role);

        CommandInvoker invoker = new CommandInvoker();
        invoker.setCommand(createAccountCommand);
        invoker.executeCommand();
    }

    @FXML
    private void backToMain(ActionEvent event) throws IOException {
        stageMain();
    }

    private void stageMain() throws IOException {
        App.setRoot("main");
        App.getStage().setWidth(624);
        App.getStage().setHeight(512);
        App.getStage().setResizable(false);
    }
}


