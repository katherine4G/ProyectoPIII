package APIs;

/**
 *
 * @author kathe
 */
import Model.University;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.InputStream;

public class UniversityAPI {

    private static final String BASE_URL = "http://localhost:5000";

    // (POST)
    public boolean createUniversity(String jsonBody, String token) {
        String url = BASE_URL + "/university/create";  // URL completa de la API

        HttpURLConnection connection = null;
        OutputStream os = null;
        BufferedReader br = null;

        try {
            // Configurar la conexión HTTP
            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setDoOutput(true);  // Permitimos la escritura de datos

            // Escribir el cuerpo de la solicitud
            os = connection.getOutputStream();
            byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);

            // Obtener el código de respuesta
            int responseCode = connection.getResponseCode();

            // Si la respuesta es 200 (OK) o 201 (Creado), la universidad se ha creado correctamente
            if (responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED) {
                return true;  // Creación exitosa
            } else {
                // En caso de error, leer la respuesta para más detalles
                InputStream errorStream = connection.getErrorStream();
                if (errorStream != null) {
                    br = new BufferedReader(new InputStreamReader(errorStream, StandardCharsets.UTF_8));
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Error al crear universidad: " + response.toString());
                } else {
                    System.out.println("Error desconocido al crear universidad. Código de respuesta: " + responseCode);
                }
                return false;  // Hubo un error en la creación
            }
        } catch (IOException e) {
            // Imprimir la excepción si ocurre un error de IO
            e.printStackTrace();
            return false;  // En caso de error de red o IO
        } finally {
            // Cerrar los flujos de salida y entrada
            try {
                if (os != null) os.close();
                if (br != null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
         }
    }
    
    


    public List<University> getAllUniversities() throws IOException {
        List<University> universities = new ArrayList<>();
        URL url = new URL(BASE_URL + "/university/showAll");

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

                Gson gson = new Gson();
                universities = gson.fromJson(response.toString(), new TypeToken<List<University>>() {}.getType());
            }
        } else {
            System.out.println("Error al obtener universidades: " + responseCode);
        }

        return universities;
    }

}
