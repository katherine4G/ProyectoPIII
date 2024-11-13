package utils.Factory;

import APIs.FacultyAPI;
import APIs.UniversityAPI;

/**
 *
 * @author kathe
 */
public class APIClientFactory {

    public static APIClient createClient(String resource) {
        switch (resource.toLowerCase()) {
            //case "user"  -> {return new UserAPI();}
            case "university" -> {return new UniversityAPI();}
            case "faculty" -> {return new FacultyAPI();}
 
            // tdos los casos  department, Course.....
                
            default -> throw new IllegalArgumentException("API Client no disponible para este recurso: " + resource);
        }
    }
}
