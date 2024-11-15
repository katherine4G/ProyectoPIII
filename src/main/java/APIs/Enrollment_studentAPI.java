package APIs;

import Model.Enrollment_student;
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
public class Enrollment_studentAPI implements BaseAPI<Enrollment_student>, APIClient{

      private static final String BASE_URL = "http://localhost:5000/enrollment_student/"; 


    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<Enrollment_student> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", Enrollment_student.class, token);
    }

    @Override
    public Enrollment_student getById(int universityId, String token) throws IOException {
        String url = BASE_URL + "showID?universityId=" + universityId;
        return HttpHelper.sendGetRequestById(url, Enrollment_student.class, token);
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

    public List<Enrollment_student> show_all_professor_enrollments_with_details() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "show_all_professor_enrollments_with_details", Enrollment_student.class, token);
    }

   
     @Override
    public <T> T getById(String id, String token) throws IOException {return null;}

    @Override
    public List<Enrollment_student> getLoadData(int page, int pageSize) throws IOException {
        return null; }
    @Override
    public List<Enrollment_student> getLoadData() throws IOException {
        return show_all_professor_enrollments_with_details();
    }
    public List<Enrollment_student> getFacultiesByUniversity(int universityId) throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest( BASE_URL + "byUniversity/" + universityId, Enrollment_student.class, token);
    }
    
}
