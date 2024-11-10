package APIs;

import Model.University;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;

public class UniversityAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/university/";
    private static final String TOKEN = "123"; // Token provisional

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
        String url = BASE_URL + "showID?universityId=" + universityId + "&token=" + TOKEN;
        return HttpHelper.sendGetRequestById(url, University.class);
    }

    @Override
    public boolean delete(int universityId, String token) {
        JsonObject jsonBody = new JsonObject();
        jsonBody.addProperty("universityId", universityId);
        jsonBody.addProperty("token", TOKEN);
        
        return HttpHelper.sendDeleteRequest(BASE_URL + "delete", jsonBody.toString());
    }

    @Override
    public boolean update(int universityId, String jsonBody, String token) {
        JsonObject updateJson = new Gson().fromJson(jsonBody, JsonObject.class);
        updateJson.addProperty("universityId", universityId);
        updateJson.addProperty("token", TOKEN);
        
        return HttpHelper.sendPutRequest(BASE_URL + "update", updateJson.toString(), token);
    }
}