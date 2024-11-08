
package Manage;

import Model.User;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class UserManager {
    private String apiUrl;

    public UserManager(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public void registerUser(User user) {
        try {
            URL url = new URL(apiUrl + "/register");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                user.getEmail(), user.getPassword()
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Usuario registrado con éxito.");
            } else {
                System.out.println("Error al registrar usuario: " + responseCode);
            }
        } catch (IOException e) {
        }
    }

    public void loginUser(String email, String password) {
        try {
            URL url = new URL(apiUrl + "/login");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String jsonInputString = String.format(
                "{\"username\": \"%s\", \"password\": \"%s\"}",
                email, password
            );

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                System.out.println("Inicio de sesión exitoso: " + response.toString());
            } else {
                System.out.println("Error al iniciar sesión: " + responseCode);
            }
        } catch (IOException e) {
        }
    }
}