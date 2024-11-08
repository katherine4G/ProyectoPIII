package Model;

import java.util.ArrayList;
import java.util.List;

public class Assignment {
    private String titulo;
    private String fechaEntrega;
    private Course curso;
    private List<Homework> trabajos;

    // Constructor
    public Assignment(String titulo, String fechaEntrega, Course curso) {
        this.titulo = titulo;
        this.fechaEntrega = fechaEntrega;
        this.curso = curso;
        this.trabajos = new ArrayList<>();
    }

    // Método para agregar un trabajo
    public void agregarTrabajo(Homework trabajo) {
        trabajos.add(trabajo);
        System.out.println("Trabajo subido por " + trabajo.getEstudiante().getName() + " en la asignación " + titulo);
    }

    // Getters y setters
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Course getCurso() {
        return curso;
    }
}
