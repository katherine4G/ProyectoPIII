
package Model;

/**
 *
 * @author kathe
 */
import java.util.ArrayList;
import java.util.List;

public class Course {
    private String nombreCurso;
    private Professor professor;
    private List<Student> estudiantes;
    private List<Assignment> asignaciones;

    // Constructor
    public Course(String nombreCurso, Professor profesor) {
        this.nombreCurso = nombreCurso;
        this.professor = profesor;
        this.estudiantes = new ArrayList<>();
        this.asignaciones = new ArrayList<>();
    }

    // Método para añadir un estudiante al curso
    public void agregarEstudiante(Student estudiante) {
        estudiantes.add(estudiante);
        System.out.println("Estudiante " + estudiante.getName() + " se ha inscrito en el curso " + nombreCurso);
    }

    // Método para añadir una asignación al curso
    public void crearAsignacion(String titulo, String fechaEntrega) {
        Assignment asignacion = new Assignment(titulo, fechaEntrega, this);
        asignaciones.add(asignacion);
        System.out.println("Asignación " + titulo + " ha sido creada para el curso " + nombreCurso);
    }

    // Getters y setters
    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public Professor getProfesor() {
        return professor;
    }

    public void setProfesor(Professor profesor) {
        this.professor = profesor;
    }

    public List<Student> getEstudiantes() {
        return estudiantes;
    }
}
