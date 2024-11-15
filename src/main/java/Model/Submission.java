package Model;
import com.google.protobuf.Timestamp;
import java.math.BigDecimal;

/**
 * Representación de la tabla 'Submission' en la base de datos.
 * 
 * CREATE TABLE Submission (
 *  submissionId INT AUTO_INCREMENT PRIMARY KEY,
 *  assignmentId INT,                            
 *  id_student VARCHAR(50),                               
 *  submission_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  
 *  file_path VARCHAR(255),                       
 *  grade DECIMAL(5,2),                          
 *  comments TEXT,                                
 *  taskReviewed BOOLEAN DEFAULT FALSE,          
 *  taskSubmitted BOOLEAN DEFAULT false,          
 *  FOREIGN KEY (assignmentId) REFERENCES Assignment(assignmentId) ON DELETE CASCADE,
 *  FOREIGN KEY (id_student) REFERENCES User(id_user) ON DELETE CASCADE
 * );
 * 
 * @author kathe
 */
public class Submission {
    private int submissionId;
    private Assignment assignmentId;
    private User id_student;
    private Timestamp submission_date;
    private String file_path;
    private BigDecimal grade; 
    private String comments;
    private boolean taskReviewed;
    private boolean taskSubmitted;

    // Constructor
    public Submission(int submissionId, Assignment assignmentId, User id_student, Timestamp submission_date,
                         String file_path, BigDecimal grade, String comments, boolean taskReviewed, boolean taskSubmitted) {
        this.submissionId = submissionId;
        this.assignmentId = assignmentId;
        this.id_student = id_student;
        this.submission_date = submission_date;
        this.file_path = file_path;
        this.grade = grade;
        this.comments = comments;
        this.taskReviewed = taskReviewed;
        this.taskSubmitted = taskSubmitted;
    }

    public int getSubmissionId() {
        return submissionId;
    }

    public void setSubmissionId(int submissionId) {
        this.submissionId = submissionId;
    }

    public Assignment getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(Assignment assignmentId) {
        this.assignmentId = assignmentId;
    }

    public User getIdStudent() {
        return id_student;
    }

    public void setIdStudent(User id_student) {
        this.id_student = id_student;
    }

    public Timestamp getSubmissionDate() {
        return submission_date;
    }

    public void setSubmissionDate(Timestamp submission_date) {
        this.submission_date = submission_date;
    }

    public String getFilePath() {
        return file_path;
    }

    public void setFilePath(String file_path) {
        this.file_path = file_path;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public boolean isTaskReviewed() {
        return taskReviewed;
    }

    public void setTaskReviewed(boolean taskReviewed) {
        this.taskReviewed = taskReviewed;
    }

    public boolean isTaskSubmitted() {
        return taskSubmitted;
    }

    public void setTaskSubmitted(boolean taskSubmitted) {
        this.taskSubmitted = taskSubmitted;
    }

    // Método toString para representar la información de la tarea
    @Override
    public String toString() {
        return "SubmissionAPI{" +
                "submissionId=" + submissionId +
                ", assignmentId=" + assignmentId +
                ", id_student=" + id_student +
                ", submission_date=" + submission_date +
                ", file_path='" + file_path + '\'' +
                ", grade=" + grade +
                ", comments='" + comments + '\'' +
                ", taskReviewed=" + taskReviewed +
                ", taskSubmitted=" + taskSubmitted +
                '}';
    }
}
