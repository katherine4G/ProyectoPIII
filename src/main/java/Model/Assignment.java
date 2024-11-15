package Model;

/**
 *CREATE TABLE Assignment (
  assignmentId INT AUTO_INCREMENT PRIMARY KEY,
  NRC VARCHAR(20),                             
  id_professor VARCHAR(50),                             
  title VARCHAR(255) NOT NULL,                 
  description TEXT,                            
  due_date DATE,                               
  file_path VARCHAR(255),                       -- Ruta del archivo de la asignación
  groupId INT,                                  -- Relación con el grupo
  FOREIGN KEY (id_professor) REFERENCES User(id_user) ON DELETE CASCADE,
  FOREIGN KEY (NRC) REFERENCES Course(NRC) ON DELETE CASCADE,
  FOREIGN KEY (groupId) REFERENCES StudentGroup(groupId) ON DELETE CASCADE
);
 * @author kathe
 */
public class Assignment {
   private int assignmentId;
    private Course NRC;
    private User id_professor;
    private String title;
    private String description;
    private String due_date;
    private String file_path;
    private StudentGroup groupId;

    public Assignment(int assignmentId, Course NRC, User id_professor, String title, String description, String due_date, String file_path, StudentGroup groupId) {
        this.assignmentId = assignmentId;
        this.NRC = NRC;
        this.id_professor = id_professor;
        this.title = title;
        this.description = description;
        this.due_date = due_date;
        this.file_path = file_path;
        this.groupId = groupId;
    }
     public Assignment(){}

    public int getAssignmentId() {
        return assignmentId;
    }

    public void setAssignmentId(int assignmentId) {
        this.assignmentId = assignmentId;
    }

    public Course getNRC() {
        return NRC;
    }

    public void setNRC(Course NRC) {
        this.NRC = NRC;
    }

    public User getId_professor() {
        return id_professor;
    }

    public void setId_professor(User id_professor) {
        this.id_professor = id_professor;
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

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public StudentGroup getGroupId() {
        return groupId;
    }

    public void setGroupId(StudentGroup groupId) {
        this.groupId = groupId;
    }
     
}
