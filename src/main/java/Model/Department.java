package Model;

/**
 *@ManyToOne(fetch = FetchType.LAZY)
 *@JoinColumn(name = "faculty_id")
 * @author kathe
 */

public class Department {
    private int idDepartment;
    private String nameDepartment;
    
    private Faculty faculty;

    public Department(int idDepartment, String nameDepartment, Faculty faculty) {
        this.idDepartment = idDepartment;
        this.nameDepartment = nameDepartment;
        this.faculty = faculty;
    }

    public Department() {}

    public int getIdDepartment() {
        return idDepartment;
    }

    public void setIdDepartment(int idDepartment) {
        this.idDepartment = idDepartment;
    }

    public String getNameDepartment() {
        return nameDepartment;
    }

    public void setNameDepartment(String nameDepartment) {
        this.nameDepartment = nameDepartment;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "Department [idDepartment=" + idDepartment + ", nameDepartment=" + nameDepartment + 
               ", faculty=" + (faculty != null ? faculty.toString() : "null") + "]";
    }
}
