package Model;

/**
 * 
    CREATE TABLE StudentGroup (
      groupId INT AUTO_INCREMENT PRIMARY KEY,
      NRC VARCHAR(20),                             
      groupName VARCHAR(255),                       
      id_professor  VARCHAR(50),                             
      FOREIGN KEY (id_professor) REFERENCES User(id_user) ON DELETE CASCADE,
      FOREIGN KEY (NRC) REFERENCES Course(NRC) ON DELETE CASCADE
    );
 * @author kathe
 */
public class Enrollment_student {
    private int groupId;
    private Course NRC;
    private String groupName;
    private User id_professor;

    public Enrollment_student(int groupId, Course NRC, String groupName, User id_professor) {
        this.groupId = groupId;
        this.NRC = NRC;
        this.groupName = groupName;
        this.id_professor = id_professor;
    }
     public Enrollment_student(){}

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public Course getNRC() {
        return NRC;
    }

    public void setNRC(Course NRC) {
        this.NRC = NRC;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public User getId_professor() {
        return id_professor;
    }

    public void setId_professor(User id_professor) {
        this.id_professor = id_professor;
    }

  
}

