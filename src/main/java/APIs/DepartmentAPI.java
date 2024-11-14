/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package APIs;

import Model.Department;
import Model.Faculty;
import java.io.IOException;
import java.util.List;
import utils.Factory.APIClient;
import utils.Factory.HttpHelper;
import utils.TokenManager;

/**
 *
 * @author kathe
 */
public class DepartmentAPI implements APIClient {

    private static final String BASE_URL = "http://localhost:5000/department/";

    @Override
    public boolean create(String jsonBody, String token) {
        return HttpHelper.sendPostRequest(BASE_URL + "create", jsonBody, token);
    }

    @Override
    public List<Department> getAll() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAll", Department.class, token);
    }

    public List<Department> showAllWithFaculty() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAllWithFaculty", Department.class, token);
    }

    public List<Department> showAllWithFacultyAndUniversity() throws IOException {
        String token = TokenManager.getInstance().getToken();
        return HttpHelper.sendGetRequest(BASE_URL + "showAllWithFacultyAndUniversity", Department.class, token);
    }

    @Override
    public Department getById(int departmentId, String token) throws IOException {
        String url = BASE_URL + "showID?departmentId=" + departmentId;
        return HttpHelper.sendGetRequestById(url, Department.class, token);
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
    public <T> T getById(String id, String token) throws IOException { return null;}
}
