package utils.Thread;

import java.io.IOException;
import java.util.List;

/**
 *
 * @author kathe
 * @param <T>
 */
public interface BaseAPI<T> {
    List<T> getLoadData() throws IOException; 
    List<T> getLoadData(int page, int pageSize) throws IOException;
}
