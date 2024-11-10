
package APIs;

/**
 *
 * @author kathe
 */
import Model.Faculty;
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

public class FacultyAPI {

    private static final String BASE_URL = "http://localhost:5000/faculty/";  // URL base de la API de Facultades
    private static final Gson gson = new Gson();

    // (POST) Crear una facultad
    public boolean createFaculty(String jsonBody, String token) {
        String url = BASE_URL + "create";
        return sendPostRequest(url, jsonBody, token);
    }

    // (GET) Obtener todas las facultades
    public List<Faculty> getAllFaculties() throws IOException {
        List<Faculty> faculties = new ArrayList<>();
        URL url = new URL(BASE_URL + "showAll");

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                faculties = gson.fromJson(response.toString(), new TypeToken<List<Faculty>>() {}.getType());
            }
        } else {
            System.out.println("Error al obtener facultades: " + responseCode);
        }
        return faculties;
    }

    // (GET) Mostrar facultad por ID
    public Faculty showFacultyById(int facultyId) throws IOException {
        String url = BASE_URL + "show/" + facultyId;
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                return gson.fromJson(response.toString(), Faculty.class);
            }
        } else {
            System.out.println("Error al obtener facultad: " + responseCode);
            return null;
        }
    }

    // (DELETE) Eliminar facultad por ID
    public boolean deleteFaculty(int facultyId, String token) {
        String url = BASE_URL + "delete/" + facultyId;
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("DELETE");
            connection.setRequestProperty("Authorization", "Bearer " + token);

            int responseCode = connection.getResponseCode();
            return responseCode == HttpURLConnection.HTTP_OK;
        } catch (IOException e) {
            System.out.println("Error al eliminar facultad: " + e.getMessage());
            return false;
        }
    }

    // (PUT) Actualizar una facultad
    public boolean updateFaculty(int facultyId, String jsonBody, String token) {
        String url = BASE_URL + "update/" + facultyId;
        return sendPutRequest(url, jsonBody, token);
    }

    // Método helper para solicitudes POST
    private boolean sendPostRequest(String url, String jsonBody, String token) {
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

    // Método helper para solicitudes PUT
    private boolean sendPutRequest(String url, String jsonBody, String token) {
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
