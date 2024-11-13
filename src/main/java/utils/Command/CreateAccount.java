package utils.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import javafx.scene.control.Alert;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author kathe
 */
public class CreateAccount implements Command {

    private String userId;
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private String role;

    public CreateAccount(String userId, String userName, String userLastName, String userEmail, String userPassword, String role) {
        this.userId = userId;
        this.userName = userName;
        this.userLastName = userLastName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
    }

    @Override
    public void execute() {
        if (userId.isEmpty() || userName.isEmpty() || userLastName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty() || role == null) {
            showAlert(Alert.AlertType.WARNING, "Form Error!", "Please fill in all fields.");
            return;
        }

        JSONObject registerJson = new JSONObject();
        registerJson.put("id_user", userId);
        registerJson.put("nameUser", userName);
        registerJson.put("lastName", userLastName);
        registerJson.put("email", userEmail);
        registerJson.put("password", userPassword);
        registerJson.put("role_id", getRoleId(role));  

        System.out.println("Registration Request: " + registerJson.toString());

        // Hacer la solicitud POST al servidor para crear la cuenta
        try {
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

            // respuesta
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                // Procesar la respuesta
                String responseMessage = response.toString();
                JSONObject responseObject = new JSONObject(responseMessage);

                if (responseObject.has("msg")) {
                    String message = responseObject.getString("msg");
                    showAlert(Alert.AlertType.INFORMATION, "Account Created", message);
                }

            } else {
                showAlert(Alert.AlertType.ERROR, "Registration Failed", "Failed to create user account.");
            }
        } catch (IOException | JSONException e) {
            showAlert(Alert.AlertType.ERROR, "Registration Failed", "Error occurred during registration: " + e.getMessage());
        }
    }

    private int getRoleId(String role) {
        return switch (role) {
            case "Estudiante" -> 3;
            case "Profesor" -> 2;
            case "Administrativo" -> 1;
            default -> -1;
        }; 
    }

    private void showAlert(Alert.AlertType type, String title, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
