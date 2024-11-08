
package Model;

/**
 *
 * @author kathe
 */
public class Student extends User {
    private String matricula;
    


    public Student(String matricula, String id_User, String name, String lastName, String email, String password, int rol) {
        super(id_User, name, lastName, email, password, rol);
        this.matricula = matricula;
    }
    

    // Método para inscribirse en un curso
    public void inscribirseCurso(Course curso) {
        System.out.println("El estudiante " +  nameUser + " se ha inscrito en el curso " + curso.getNombreCurso());
    }

//    public void mostrarInfo() {
//        System.out.println("Estudiante: " + name + ", Email: " + email + ", Matrícula: " + matricula);
//    }

    // Getters y setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public void showInfo() {
      System.out.println("Estudiante: " + nameUser + ", Email: " + email + ", Matrícula: " + matricula);
       
    }
}
