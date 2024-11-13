
package APIs;

/**
 *
 * @author kathe
 */
import Model.Faculty;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

public class FacultyAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/faculty/";  // URL base de la API de Facultades
   // private static final Gson gson = new Gson();


    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<Faculty> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", Faculty.class, token);
    }

    @Override
    public Faculty getById(int universityId, String token) throws IOException {
        String url = BASE_URL + "showID?universityId=" + universityId;
        return HttpHelper.sendGetRequestById(url, Faculty.class, token);
    }

    @Override
    public boolean delete(String jsonBody, String token) {
        String url = BASE_URL + "delete";
        return HttpHelper.sendDeleteRequest(url, token, jsonBody); 
    }

    @Override
    public boolean update(int id, String jsonBody, String token) {
        String url = BASE_URL + "update";
        return HttpHelper.sendPutRequest(url, jsonBody, token);
    }
    public List<Faculty> getAllWithUniversity() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAllWithUniversity", Faculty.class, token);
    }

//    // (POST) Crear una facultad
//    public boolean createFaculty(String jsonBody, String token) {
//        String url = BASE_URL + "create";
//        return sendPostRequest(url, jsonBody, token);
//    }
//
//    // (GET) Obtener todas las facultades
//    public List<Faculty> getAllFaculties() throws IOException {
//        List<Faculty> faculties = new ArrayList<>();
//        URL url = new URL(BASE_URL + "showAll");
//
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                faculties = gson.fromJson(response.toString(), new TypeToken<List<Faculty>>() {}.getType());
//            }
//        } else {
//            System.out.println("Error al obtener facultades: " + responseCode);
//        }
//        return faculties;
//    }
//
//    // (GET) Mostrar facultad por ID
//    public Faculty showFacultyById(int facultyId) throws IOException {
//        String url = BASE_URL + "show/" + facultyId;
//        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//        connection.setRequestMethod("GET");
//        connection.setRequestProperty("Accept", "application/json");
//
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
//                StringBuilder response = new StringBuilder();
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine.trim());
//                }
//                return gson.fromJson(response.toString(), Faculty.class);
//            }
//        } else {
//            System.out.println("Error al obtener facultad: " + responseCode);
//            return null;
//        }
//    }
//
//    // (DELETE) Eliminar facultad por ID
//    public boolean deleteFaculty(int facultyId, String token) {
//        String url = BASE_URL + "delete/" + facultyId;
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("DELETE");
//            connection.setRequestProperty("Authorization", "Bearer " + token);
//
//            int responseCode = connection.getResponseCode();
//            return responseCode == HttpURLConnection.HTTP_OK;
//        } catch (IOException e) {
//            System.out.println("Error al eliminar facultad: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // (PUT) Actualizar una facultad
//    public boolean updateFaculty(int facultyId, String jsonBody, String token) {
//        String url = BASE_URL + "update/" + facultyId;
//        return sendPutRequest(url, jsonBody, token);
//    }
//
//    // Método helper para solicitudes POST
//    private boolean sendPostRequest(String url, String jsonBody, String token) {
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("POST");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Authorization", "Bearer " + token);
//            connection.setDoOutput(true);
//
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            int responseCode = connection.getResponseCode();
//            return responseCode == HttpURLConnection.HTTP_OK || responseCode == HttpURLConnection.HTTP_CREATED;
//        } catch (IOException e) {
//            System.out.println("Error en solicitud POST: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // Método helper para solicitudes PUT
//    private boolean sendPutRequest(String url, String jsonBody, String token) {
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("PUT");
//            connection.setRequestProperty("Content-Type", "application/json");
//            connection.setRequestProperty("Authorization", "Bearer " + token);
//            connection.setDoOutput(true);
//
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            int responseCode = connection.getResponseCode();
//            return responseCode == HttpURLConnection.HTTP_OK;
//        } catch (IOException e) {
//            System.out.println("Error en solicitud PUT: " + e.getMessage());
//            return false;
//        }
//    }
}
