
package utils.Factory;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author kathe
 */
public interface APIClient {
    boolean create(String jsonBody, String token);
    <T> List<T> getAll() throws IOException;
    <T> T getById(int id) throws IOException;
    boolean delete(int id, String token);
    boolean update(int id, String jsonBody, String token);
}
