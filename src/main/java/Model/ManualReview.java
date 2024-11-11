package Model;

/**
 *
 * @author kathe
 */
public class ManualReview {
    private int reviewId;
    private int submissionId;  // Relacionado con la entrega de la tarea
    private String idProfessor;  // Relacionado con el profesor que revisa
    private double grade;  // Calificación manual
    private String comments;  // Comentarios manuales
    private String reviewDate;  // Fecha de la revisión

    // Constructor vacío y con parámetros
    public ManualReview() {}

    public ManualReview(int reviewId, int submissionId, String idProfessor, double grade, String comments, String reviewDate) {
        this.reviewId = reviewId;
        this.submissionId = submissionId;
        this.idProfessor = idProfessor;
        this.grade = grade;
        this.comments = comments;
        this.reviewDate = reviewDate;
    }

    // Getters y Setters
    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
        this.idProfessor = idProfessor;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    @Override
    public String toString() {
        return "ManualReview{" +
                "reviewId=" + reviewId +
                ", submissionId=" + submissionId +
                ", idProfessor='" + idProfessor + '\'' +
                ", grade=" + grade +
                ", comments='" + comments + '\'' +
                ", reviewDate='" + reviewDate + '\'' +
                '}';
    }
}
