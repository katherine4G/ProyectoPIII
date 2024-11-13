package utils.Factory;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class HttpHelper {

    public static boolean sendPostRequest(String url, String jsonBody, String token) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED;
        } catch (IOException e) {
            System.out.println("Error en solicitud POST: " + e.getMessage());
            return false;
        }
    }
public static <T> List<T> sendGetRequest(String url, Class<T> clazz, String token) throws IOException {
    List<T> result = new ArrayList<>();
    HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
    connection.setRequestMethod("GET");
    connection.setRequestProperty("Accept", "application/json");
    connection.setRequestProperty("Authorization", "Bearer " + token);

    int responseCode = connection.getResponseCode();
    if (responseCode == HttpURLConnection.HTTP_OK) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }

            // Usamos Gson para deserializar a la lista de facultades
            result = new Gson().fromJson(response.toString(),
                    TypeToken.getParameterized(List.class, clazz).getType());
        }
    } else {
        throw new IOException("Error al obtener datos: " + responseCode);
    }
    return result;
}

//    public static <T> List<T> sendGetRequest(String url, Class<T> clazz, String token) throws IOException {
//        List<T> result = new ArrayList<>();
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//        connection.setRequestProperty("Authorization", "Bearer " + token);
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//
//                result = new Gson().fromJson(response.toString(),
//                        TypeToken.getParameterized(List.class, clazz).getType());
//            }
//        } else {
//            throw new IOException("Error al obtener datos: " + responseCode);
//        }
//        return result;
//    }

    public static <T> T sendGetRequestById(String url, Class<T> clazz, String token) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Authorization", "Bearer " + token);

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return new Gson().fromJson(response.toString(), clazz);
            }
        } else {
            System.out.println("Error al obtener el recurso: " + responseCode);
            return null;
        }
    }

    public static boolean sendDeleteRequest(String url, String token, String jsonBody) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_NO_CONTENT;
        } catch (IOException e) {
            System.out.println("Error en solicitud DELETE: " + e.getMessage());
            return false;
        }
    }

    public static boolean sendPutRequest(String url, String jsonBody, String token) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setDoOutput(true);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            System.out.println("Error en solicitud PUT: " + e.getMessage());
            return false;
        }
    }
}
