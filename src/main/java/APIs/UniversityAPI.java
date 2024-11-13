package APIs;

import Model.University;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

public class UniversityAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/university/";

    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<University> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", University.class, token);
    }

    @Override
    public University getById(int universityId, String token) throws IOException {
        String url = BASE_URL + "showID?universityId=" + universityId;
        return HttpHelper.sendGetRequestById(url, University.class, token);
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
 
    @Override
    public <T> T getById(String id, String token) throws IOException {return null;}
}
