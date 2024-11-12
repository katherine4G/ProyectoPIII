
package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.json.JSONException;
import org.json.JSONObject;
import pack.universityplatform.App;
import utils.TokenManager;

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
        comboBoxRol.getItems().addAll("Estudiante", "Profesor","Administrativo");
        
    } 
    @FXML
    private void getNextPage(ActionEvent event) throws IOException {
        String userId = txt_userId.getText();
        String password = PassTxt_password.getText();

        if (userId.isEmpty() || password.isEmpty()) {
            showAlert(AlertType.WARNING, "Form Error!", "Please fill in all fields.");
            return;
        }

        // Crear el JSON con los datos de login
        JSONObject loginJson = new JSONObject();
        loginJson.put("password", password);
        loginJson.put("id_user", userId);
        

        System.out.println("Login Request: " + loginJson.toString());

        // Hacer la solicitud POST al servidor para iniciar sesión
        URL url = new URL("http://localhost:5000/auth/login"); // URL de tu endpoint de login
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Enviar los datos
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = loginJson.toString().getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Leer la respuesta
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // Respuesta exitosa, procesar el token
            StringBuilder response = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
            }

            // Procesar la respuesta (por ejemplo, obtener el token)
            String responseMessage = response.toString();
            try {
                JSONObject responseObject = new JSONObject(responseMessage);

                // Verificar si el campo token y role_id existen
                if (responseObject.has("token") && responseObject.has("role_id")) {
                    String token = responseObject.getString("token"); // Suponiendo que el token está en esta propiedad

                    // Guardar el token utilizando el Singleton
                    TokenManager.getInstance().setToken(token); // Guardamos el token

                    // Obtener el rol del usuario desde la respuesta
                    int roleId = responseObject.getInt("role_id");

                    // Redirigir según el rol
                    loadUserDashboard(roleId);
                } else {
                    showAlert(AlertType.ERROR, "Login Failed", "Unexpected response format from server.");
                }

            } catch (JSONException e) {
                showAlert(AlertType.ERROR, "Login Failed", "Error processing server response: " + e.getMessage());
            }
        } else {
            showAlert(AlertType.ERROR, "Login Failed", "Invalid user ID or password.");
        }
    }



    @FXML
    private void createAccount(ActionEvent event) throws IOException {
        App.setRoot("SingIn");
    
    }

     private void showAlert(AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }


    @FXML
    private void get_passwordUser(ActionEvent event) {
        
    }
    @FXML
    private void get_userId(ActionEvent event) {
        
    }

    private void loadUserDashboard(int roleId) throws IOException {
        switch (roleId) {
            case 1 -> 
                App.setRoot("InterAdmin");
            case 2 -> 
                App.setRoot("InterTeacher");
            case 3 -> 
                App.setRoot("InterStudent");
            default -> {
                showAlert(AlertType.WARNING, "Role Error", "Invalid role for user.");
                return;
            }
        }

        App.getStage().setWidth(900); 
        App.getStage().setHeight(800); 
        App.getStage().setResizable(true); 
    } 
}
//    @FXML
//    private void getNextPage(ActionEvent event) throws IOException {
//        
//         String userType = comboBoxRol.getValue();
//
//        if ( userType == null) {
//            showAlert(AlertType.WARNING, "Form Error!", "Please fill in all fields.");
//            return;
//        }
//        
//        loadUserDashboard(userType);    
//    }
//
//    private void loadUserDashboard(String userType) throws IOException {
//        
//        switch (userType) {
//            case "Estudiante" -> {
//                App.setRoot("InterStudent");
//                App.getStage().setWidth(900); 
//                App.getStage().setHeight(800); 
//                App.getStage().setResizable(true); 
//               // App.getStage().setMaximized(true);  // Pantalla completa solo para estudiante
//            }
//            case "Profesor" -> {
//                App.setRoot("InterTeacher");
//               // App.getStage().setMaximized(true);  // Pantalla completa solo para profesor
//                App.getStage().setWidth(900); // Establecer el ancho deseado
//                App.getStage().setHeight(800); // Establecer la altura deseada
//                App.getStage().setResizable(true); // 
//            }
//            case "Administrativo" -> {
//                App.setRoot("InterAdmin");
//                //App.getStage().setMaximized(true); // Pantalla completa solo para administrativo
//                App.getStage().setWidth(900); // Establecer el ancho deseado
//                App.getStage().setHeight(800); // Establecer la altura deseada
//                App.getStage().setResizable(true); //
//            }
//            default -> throw new IllegalArgumentException("Unexpected value: " + userType);
//        }
//    }