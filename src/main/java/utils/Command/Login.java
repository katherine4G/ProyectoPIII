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
import pack.universityplatform.App;
import utils.TokenManager;

/**
 *
 * @author kathe
 */
public class Login implements Command {

    private String userId;
    private String password;
    private String selectedRole;

    public Login(String userId, String password, String selectedRole) {
        this.userId = userId;
        this.password = password;
        this.selectedRole = selectedRole;
    }

    @Override
    public void execute() {
        if (userId.isEmpty() || password.isEmpty() || selectedRole == null) {
            showAlert(Alert.AlertType.WARNING, "Form Error!", "Please fill in all fields.");
            return;
        }

        JSONObject loginJson = new JSONObject();
        loginJson.put("password", password);
        loginJson.put("id_user", userId);

        System.out.println("Login Request: " + loginJson.toString());

        try {
            URL url = new URL("http://localhost:5000/auth/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = loginJson.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder response = new StringBuilder();
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                }

                String responseMessage = response.toString();
                try {
                    JSONObject responseObject = new JSONObject(responseMessage);

                    if (responseObject.has("access_token") && responseObject.has("role_id")) {
                        String token = responseObject.getString("access_token");

                        TokenManager.getInstance().setToken(token);

                        int roleId = responseObject.getInt("role_id");

                        if (roleId == getRoleIdFromComboBox(selectedRole)) {
                            loadUserDashboard(roleId);
                        } else {
                            showAlert(Alert.AlertType.ERROR, "Role Mismatch", "The selected role does not match the user's role.");
                        }
                    } else {
                        showAlert(Alert.AlertType.ERROR, "Login Failed", "Unexpected response format from server.");
                    }
                } catch (JSONException e) {
                    showAlert(Alert.AlertType.ERROR, "Login Failed", "Error processing server response: " + e.getMessage());
                }
            } else {
                showAlert(Alert.AlertType.ERROR, "Login Failed", "Invalid user ID or password.");
            }
        } catch (IOException e) {
            showAlert(Alert.AlertType.ERROR, "Login Failed", "Error occurred during login: " + e.getMessage());
        }
    }

    private void loadUserDashboard(int roleId) throws IOException {
        switch (roleId) {
            case 1 -> App.setRoot("InterAdmin");
            case 2 -> App.setRoot("InterTeacher");
            case 3 -> App.setRoot("InterStudent");
            default -> {
                showAlert(Alert.AlertType.WARNING, "Role Error", "Invalid role for user.");
                return;
            }
        }
        App.getStage().setWidth(1000);
        App.getStage().setHeight(800);
        App.getStage().setResizable(true);
    }

    private int getRoleIdFromComboBox(String role) {
        return switch (role) {
            case "Administrativo" -> 1;
            case "Profesor" -> 2;
            case "Estudiante" -> 3;
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
