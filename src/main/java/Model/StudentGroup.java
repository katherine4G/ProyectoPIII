/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author kathe
 */
public class StudentGroup {
    private int groupId;
    private String NRC;  // Relacionado con el curso
    private String groupName;  // Nombre del grupo
    private String idProfessor;  // Relacionado con el profesor

    public StudentGroup() {}

    public StudentGroup(int groupId, String NRC, String groupName, String idProfessor) {
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

    public String getNRC() {
        return NRC;
    }

    public void setNRC(String NRC) {
        this.NRC = NRC;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(String idProfessor) {
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
