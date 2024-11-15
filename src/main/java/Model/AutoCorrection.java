package Model;

public final class AutoCorrection {
    private int autoCorrectionId;
    private Submission submissionId;
    private double grade; // Nota generada automáticamente
    private String comments; // Comentarios generados automáticamente

    public AutoCorrection() {}

    public AutoCorrection(int autoCorrectionId, Submission submissionId, double grade, String comments) {
        this.autoCorrectionId = autoCorrectionId;
        this.submissionId = submissionId;
        this.setGrade(grade);
        this.comments = comments;
    }
    public int getAutoCorrectionId() {
        return autoCorrectionId;
    }

    public void setAutoCorrectionId(int autoCorrectionId) {
        this.autoCorrectionId = autoCorrectionId;
    }

    public Submission getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(Submission submissionId) {
        this.submissionId = submissionId;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        if (grade < 0 || grade > 100) {
            throw new IllegalArgumentException("calificación inválida");
        }
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
