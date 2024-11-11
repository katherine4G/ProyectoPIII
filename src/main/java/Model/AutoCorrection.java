package Model;

/**
 *
 * @author kathe
 */
public class AutoCorrection {
    private int autoCorrectionId;
    private int submissionId;  // Relacionado con la entrega de la tarea
    private double grade;  // Nota generada automáticamente
    private String comments;  // Comentarios generados automáticamente

    public AutoCorrection() {}

    public AutoCorrection(int autoCorrectionId, int submissionId, double grade, String comments) {
        this.autoCorrectionId = autoCorrectionId;
        this.submissionId = submissionId;
        this.grade = grade;
        this.comments = comments;
    }

    // Getters y Setters
    public int getAutoCorrectionId() {
        return autoCorrectionId;
    }

    public void setAutoCorrectionId(int autoCorrectionId) {
        this.autoCorrectionId = autoCorrectionId;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
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

    @Override
    public String toString() {
        return "AutoCorrection{" +
                "autoCorrectionId=" + autoCorrectionId +
                ", submissionId=" + submissionId +
                ", grade=" + grade +
                ", comments='" + comments + '\'' +
                '}';
    }
}
