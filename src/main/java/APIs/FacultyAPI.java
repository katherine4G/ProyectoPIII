
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
import utils.Thread.BaseAPI;
import utils.TokenManager;
public class FacultyAPI implements BaseAPI<Faculty>, APIClient {

    private static final String BASE_URL = "http://localhost:5000/faculty/"; 


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

    public List<Faculty> getPaginated(int page, int perPage, String token) throws IOException {
        String url = BASE_URL + "showAllWithUniversity?page=" + page + "&per_page=" + perPage;
        return HttpHelper.sendGetRequest(url, Faculty.class, token);
    }

     @Override
    public <T> T getById(String id, String token) throws IOException {return null;}

    @Override
    public List<Faculty> getLoadData(int page, int pageSize) throws IOException {
        return null; }
    @Override
    public List<Faculty> getLoadData() throws IOException {
        return getAllWithUniversity();
    }
    public List<Faculty> getFacultiesByUniversity(int universityId) throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest( BASE_URL + "byUniversity/" + universityId, Faculty.class, token);
    }


}