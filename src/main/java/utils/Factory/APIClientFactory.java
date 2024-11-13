package utils.Factory;

import APIs.FacultyAPI;
import APIs.UniversityAPI;
import APIs.UserAPI;

/**
 *
 * @author kathe
 */
public class APIClientFactory {

    public static APIClient createClient(String resource) {
        switch (resource.toLowerCase()) {
            case "user"  -> {return new UserAPI();}
            case "university" -> {return new UniversityAPI();}
            case "faculty" -> {return new FacultyAPI();}
            //...
                
            default -> throw new IllegalArgumentException("API Client no disponible para: " + resource);
        }
    }
}
