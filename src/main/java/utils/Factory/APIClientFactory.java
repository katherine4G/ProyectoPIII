/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
            case "university" -> {return new UniversityAPI();}
         // case "faculty" -> {return new FacultyAPI();}
 
            // tdos los casos  department, Course.....
                
            default -> throw new IllegalArgumentException("API Client no disponible para este recurso: " + resource);
        }
    }
}
