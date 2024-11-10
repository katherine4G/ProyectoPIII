package APIs;

import Model.University;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;

public class UniversityAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/university/";

    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<University> getAll() throws IOException {
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", University.class);
    }

    @Override
    public University getById(int universityId) throws IOException {
        return HttpHelper.sendGetRequestById(BASE_URL + "show/" + universityId, University.class);
    }

    @Override
    public boolean delete(int universityId, String token) {
        return HttpHelper.sendDeleteRequest(BASE_URL + "delete/" + universityId, token);
    }

    @Override
    public boolean update(int universityId, String jsonBody, String token) {
        return HttpHelper.sendPutRequest(BASE_URL + "update/" + universityId, jsonBody, token);
    }
}


// anterior
//public class UniversityAPI {
//
//    private static final String BASE_URL = "http://localhost:5000/university/";
//    private static final Gson gson = new Gson();
//
//    // (POST) Crear una universidad
//    public boolean createUniversity(String jsonBody, String token) {
//        String url = BASE_URL + "create";
//        return sendPostRequest(url, jsonBody, token);
//    }
//
//    // (GET) Obtener todas las universidades
//    public List<University> getAllUniversities() throws IOException {
//        List<University> universities = new ArrayList<>();
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
//                universities = gson.fromJson(response.toString(), new TypeToken<List<University>>() {}.getType());
//            }
//        } else {
//            System.out.println("Error al obtener universidades: " + responseCode);
//        }
//        return universities;
//    }
//
//    // (GET) Mostrar universidad por ID
//    public University showUniversityById(int universityId) throws IOException {
//        String url = BASE_URL + "show/" + universityId;
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
//                return gson.fromJson(response.toString(), University.class);
//            }
//        } else {
//            System.out.println("Error al obtener universidad: " + responseCode);
//            return null;
//        }
//    }
//
//    // (DELETE) Eliminar universidad por ID
//    public boolean deleteUniversity(int universityId, String token) {
//        String url = BASE_URL + "delete/" + universityId;
//        try {
//            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
//            connection.setRequestMethod("DELETE");
//            connection.setRequestProperty("Authorization", "Bearer " + token);
//
//            int responseCode = connection.getResponseCode();
//            return responseCode == HttpURLConnection.HTTP_OK;
//        } catch (IOException e) {
//            System.out.println("Error al eliminar universidad: " + e.getMessage());
//            return false;
//        }
//    }
//
//    // (PUT) Actualizar una universidad
//    public boolean updateUniversity(int universityId, String jsonBody, String token) {
//        String url = BASE_URL + "update/" + universityId;
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
//}
