package APIs;

import Model.Enrollment_professor;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.Thread.BaseAPI;
import utils.TokenManager;

/**
 * 
 * @author kathe
 */
public class Enrollment_professorAPI implements BaseAPI<Enrollment_professor>, APIClient {

    private static final String BASE_URL = "http://localhost:5000/enrollment_professor/"; 


    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<Enrollment_professor> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", Enrollment_professor.class, token);
    }

    @Override
    public Enrollment_professor getById(int enroll_studentId, String token) throws IOException {
        String url = BASE_URL + "showID?enroll_studentId=" + enroll_studentId;
        return HttpHelper.sendGetRequestById(url, Enrollment_professor.class, token);
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

    public List<Enrollment_professor> show_all_professor_enrollments_with_details() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "show_all_professor_enrollments_with_details", Enrollment_professor.class, token);
    }

   
     @Override
    public <T> T getById(String id, String token) throws IOException {return null;}
    @Override
    public List<Enrollment_professor> getLoadData(int page, int pageSize) throws IOException { return null; }
   
    public List<Enrollment_professor> getLoadData() throws IOException {
        List<Enrollment_professor> data = show_all_professor_enrollments_with_details();
        // Verificar si se está obteniendo datos
        System.out.println("Datos recibidos: " + data);
        return data;
    }

//    public List<Enrollment_professor> getFacultiesByUniversity(int universityId) throws IOException {
//        String token = TokenManager.getInstance().getToken();
//        return HttpHelper.sendGetRequest( BASE_URL + "byUniversity/" + universityId, Enrollment_professor.class, token);
//    }

}
