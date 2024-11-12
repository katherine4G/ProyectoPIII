package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
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
        // Obtener los datos del formulario
        String userId = txt_userId.getText();
        String userName = txt_userName.getText();
        String userLastName = txt_userLastName.getText();
        String userEmail = txt_userEmail.getText();
        String userPassword = txt_userPassword.getText();
        String role = comboBoxRole.getValue(); // Obtener el rol seleccionado

        // Validación simple
        if (userId.isEmpty() || userName.isEmpty() || userLastName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || role == null) {
            showAlert(AlertType.WARNING, "Form Error!", "Please fill in all fields.");
            return;
        }

        // Crear el JSON con los datos del formulario
        JSONObject registerJson = new JSONObject();
        registerJson.put("id_user", userId);
        registerJson.put("nameUser", userName);
        registerJson.put("lastName", userLastName);
        registerJson.put("email", userEmail);
        registerJson.put("password", userPassword);
        registerJson.put("role_id", getRoleId(role));  // Aquí se obtiene el ID del rol, basado en la selección

        System.out.println("Registration Request: " + registerJson.toString());

        // Hacer la solicitud POST al servidor para crear la cuenta
        URL url = new URL("http://localhost:5000/auth/register"); // Endpoint para registro
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Enviar los datos
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = registerJson.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Leer la respuesta
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
            // Respuesta exitosa, procesar la respuesta
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Procesar la respuesta (por ejemplo, mostrar mensaje de éxito)
            String responseMessage = response.toString();
            JSONObject responseObject = new JSONObject(responseMessage);

            if (responseObject.has("msg")) {
                String message = responseObject.getString("msg");
                showAlert(AlertType.INFORMATION, "Account Created", message);
                stageMain();  // Redirigir a la pantalla principal después de crear la cuenta
            }

        } else {
            showAlert(AlertType.ERROR, "Registration Failed", "Failed to create user account.");
        }
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

    private int getRoleId(String role) {
        switch (role) {
            case "Estudiante":
                return 3;  // ID de rol para Estudiante
            case "Profesor":
                return 2;  // ID de rol para Profesor
            case "Administrativo":
                return 1;  // ID de rol para Administrativo
            default:
                return -1;  // Si el rol es inválido
        }
    }

    private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

