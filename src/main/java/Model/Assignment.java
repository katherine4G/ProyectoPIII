package Model;

/**
 *
 * @author kathe
 */
public class Assignment {
    private int assignmentId;
    private String NRC;  // Relacionado con el curso
    private String idProfessor;  // Relacionado con el profesor
    private String title;  // Título de la asignación
    private String description;  // Descripción de la tarea
    private String dueDate;  // Fecha límite para entregar
    private int groupId;  // Relacionado con el grupo

    // Constructor vacío y con parámetros
    public Assignment() {}

    public Assignment(int assignmentId, String NRC, String idProfessor, String title, String description, String dueDate, int groupId) {
        this.assignmentId = assignmentId;
        this.NRC = NRC;
        this.idProfessor = idProfessor;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.groupId = groupId;
    }

    // Getters y Setters
    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", NRC='" + NRC + '\'' +
                ", idProfessor='" + idProfessor + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", groupId=" + groupId +
                '}';
    }
}
