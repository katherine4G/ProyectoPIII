package utils.Factory;

import java.io.IOException;
import java.util.List;

public interface APIClient {
    boolean create(String jsonBody, String token);
    <T> List<T> getAll() throws IOException;
    <T> T getById(int id, String token) throws IOException;
    <T> T getById(String id, String token) throws IOException;
    boolean delete(String jsonBody, String token); 
    boolean update(int id, String jsonBody, String token);
}