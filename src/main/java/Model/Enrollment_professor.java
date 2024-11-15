package Model;

/**
 *     tabla mySql
 *  CREATE TABLE Enrollment_professor (
    enroll_profeId INT AUTO_INCREMENT PRIMARY KEY,
    id_professor VARCHAR(50),  -- como cédula
    NRC VARCHAR(20),           -- NRC del curso
    FOREIGN KEY (id_professor) REFERENCES User(id_user) ON DELETE CASCADE,
    FOREIGN KEY (NRC) REFERENCES Course(NRC) ON DELETE CASCADE);

 * @author kathe
 */
public class Enrollment_professor {
 private int enroll_profeId;
    private User id_professor;
    private Course NRC;

    public Enrollment_professor(int enroll_profeId, User id_professor, Course NRC) {
        this.enroll_profeId = enroll_profeId;
        this.id_professor = id_professor;
        this.NRC = NRC;
    }
    public Enrollment_professor(){}
    
    public int getEnroll_profeId() {
        return enroll_profeId;
    }

    public void setEnroll_profeId(int enroll_profeId) {
        this.enroll_profeId = enroll_profeId;
    }

    public User getId_professor() {
        return id_professor;
    }

    public void setId_professor(User id_professor) {
        this.id_professor = id_professor;
    }

    public Course getNRC() {
        return NRC;
    }

    public void setNRC(Course NRC) {
        this.NRC = NRC;
    }

    @Override
    public String toString() {
        return "Enrollment_professorAPI{" + "enroll_profeId=" + enroll_profeId + ", id_professor=" + id_professor + ", NRC=" + NRC + '}';
    }
    
}
