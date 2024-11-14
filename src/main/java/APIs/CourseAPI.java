package APIs;

import Model.Course;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

/**
 *
 * @author kathe
 */
public class CourseAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/course/";

    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<Course> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", Course.class, token);
    }

    @Override
    public <T> T getById(String NRC, String token) throws IOException {
        return (T) HttpHelper.sendGetRequestById(BASE_URL + "showID?NRC=" + NRC, Course.class, token);      
    }
    
    @Override
    public Course getById(int departmentId, String token) throws IOException { return null;}

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

}
