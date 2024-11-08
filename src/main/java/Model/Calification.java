
package Model;


public class Calification {
    private double nota;
    private String comentario;

    // Constructor
    public Calification(double nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
    }

    // Getters y setters
    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
