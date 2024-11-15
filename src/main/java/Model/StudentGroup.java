package Model;

/**
 *
 * @author kathe
 */
public class StudentGroup {
    private int groupId;
    private Course NRC;  
    private String groupName;
    private User idProfessor; 

    public StudentGroup() {}

    public StudentGroup(int groupId, Course NRC, String groupName, User idProfessor) {
        this.groupId = groupId;
        this.NRC = NRC;
        this.groupName = groupName;
        this.idProfessor = idProfessor;
    }

    // Getters y Setters
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

    public User getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(User idProfessor) {
        this.idProfessor = idProfessor;
    }

    @Override
    public String toString() {
        return "StudentGroup{" +
                "groupId=" + groupId +
                ", NRC='" + NRC + '\'' +
                ", groupName='" + groupName + '\'' +
                ", idProfessor='" + idProfessor + '\'' +
                '}';
    }
}
