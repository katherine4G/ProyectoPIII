package utils.Factory;

import APIs.CourseAPI;
import APIs.DepartmentAPI;
import APIs.Enrollment_professorAPI;
import APIs.Enrollment_studentAPI;
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
            case "department" -> {return new DepartmentAPI();} 
            case "course" -> {return new CourseAPI();}
            case "enrollment_professor" -> {return new Enrollment_professorAPI();}
            case "enrollment_student" -> {return new Enrollment_studentAPI();}
            // case "studentGroup" -> {return new StudentGroup();}            
            // case "assignment" -> {return new AssignmentAPI();}
            // case "submission" -> {return new Submission();} 
            // case "registrationRequest" -> {return new RegistrationRequestAPI();} 
            // case "autoCorrection" -> {return new AutoCorrectionAPI();} 
            // case "manualReview" -> {return new ManualReview();}                   

                
            default -> throw new IllegalArgumentException("API Client no disponible para: " + resource);
        }
    }
}
