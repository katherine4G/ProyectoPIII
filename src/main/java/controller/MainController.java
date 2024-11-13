package controller;

import Manage.UserSession;
import Model.User;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;
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
         get_userId( event);
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
        String userId = txt_userId.getText();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:5000/auth/user/" + userId)) 
                    .header("Content-Type", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                String responseBody = response.body();
                JSONObject jsonResponse = new JSONObject(responseBody);
                String nameUser = jsonResponse.optString("nameUser");
                String lastName = jsonResponse.optString("lastName");
                String email = jsonResponse.optString("email");

                User user = new User();
                user.setIdUser(userId);
                user.setNameUser(nameUser);
                user.setLastName(lastName);
                user.setEmail(email);

                UserSession.setCurrentUser(user);

            } else { System.out.println("Error al obtener los detalles del usuario: " + response.statusCode()); }

        } catch (IOException | InterruptedException | JSONException e) { System.out.println("Error en la solicitud HTTP.");}
    }


//    @FXML
//    private void get_userId(ActionEvent event) {
//        String userId = txt_userId.getText(); 
//        System.out.println("ID de usuario ingresado: " + userId); 
//
//        try {
//
//             String token = TokenManager.getInstance().getToken();
//            UserAPI userApi = new UserAPI();
//            User user = userApi.getById(userId, token);
//
//            if (user != null) { UserSession.setCurrentUser(user);
//            } else {System.out.println("Usuario no encontrado."); }
//
//        } catch (IOException e) {
//            System.out.println("Error al obtener los detalles del usuario: " + e.getMessage());
//        }
//    }

}
