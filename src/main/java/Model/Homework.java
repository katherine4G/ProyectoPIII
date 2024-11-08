package Model;


public class Homework {
    private Student estudiante;
    private String archivo;
    private Assignment asignacion;
    private Calification calificacion;

    // Constructor
    public Homework(Student estudiante, String archivo, Assignment asignacion) {
        this.estudiante = estudiante;
        this.archivo = archivo;
        this.asignacion = asignacion;
    }

    // Getters y setters
    public Student getEstudiante() {
        return estudiante;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public Assignment getAsignacion() {
        return asignacion;
    }

    public Calification getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Calification calificacion) {
        this.calificacion = calificacion;
    }
}
