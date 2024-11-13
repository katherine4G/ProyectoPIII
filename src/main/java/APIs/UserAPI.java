
package APIs;
import Model.User;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

/**
 *
 * @author kathe
 */

import Model.User;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

public class UserAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/auth/";

    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "register", jsonBody, token);
    }

    @Override
    public List<User> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "users", User.class, token);
    }

    @Override
    public <T> T getById(String id, String token) throws IOException {
        return (T) HttpHelper.sendGetRequestById(BASE_URL+"user/" + id, User.class, token);
    }

    @Override
    public boolean delete(String jsonBody, String token) {
        String userId = jsonBody;  
        String url = BASE_URL + "user/delete/" + userId;
        return HttpHelper.sendDeleteRequest(url, token, null);
    }

    @Override
    public boolean update(int id, String jsonBody, String token) {
        String url = BASE_URL + "update";
        return HttpHelper.sendPutRequest(url, jsonBody, token);
    }

    @Override
    public <T> T getById(int id, String token) throws IOException {return null;}

}
