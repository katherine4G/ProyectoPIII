package Model;

import java.sql.Timestamp;

/**
 * Representación de la tabla 'ManualReview' en la base de datos.
 * 
 * CREATE TABLE ManualReview (
 *    reviewId INT AUTO_INCREMENT PRIMARY KEY,
 *    submissionId INT,                      -- Referencia a la tarea entregada
 *    id_professor VARCHAR(50),              -- Referencia al profesor que revisó
 *    grade DECIMAL(5,2),                    -- Calificación manual
 *    comments TEXT,                          -- Comentarios manuales
 *    reviewDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Fecha de la revisión
 *    FOREIGN KEY (submissionId) REFERENCES Submission(submissionId) ON DELETE CASCADE,
 *    FOREIGN KEY (id_professor) REFERENCES User(id_user) ON DELETE CASCADE
 * );
 * 
 * @author kathe
 */
public class ManualReview {
    private int reviewId;
    private Submission submissionId;   
    private User id_professor;  
    private double grade;   
    private String comments;            // Comentarios del profesor
    private Timestamp reviewDate;       // Fecha de la revisión

    // Constructor
    public ManualReview(int reviewId, Submission submissionId, User id_professor, double grade, String comments, Timestamp reviewDate) {
        this.reviewId = reviewId;
        this.submissionId = submissionId;
        this.id_professor = id_professor;
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

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    public User getId_professor() {
        return id_professor;
    }

    public void setId_professor(User id_professor) {
        this.id_professor = id_professor;
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

    public Timestamp getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Timestamp reviewDate) {
        this.reviewDate = reviewDate;
    }

    // Método toString() para representar el objeto en formato legible
    @Override
    public String toString() {
        return "ManualReview{" +
                "reviewId=" + reviewId +
                ", submissionId=" + submissionId +
                ", id_professor=" + id_professor +
                ", grade=" + grade +
                ", comments='" + comments + '\'' +
                ", reviewDate=" + reviewDate +
                '}';
    }
}
