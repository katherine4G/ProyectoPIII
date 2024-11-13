
package Model;

public class User {
    private String idUser; //cédula
    private String nameUser;
    private String lastName;
    private String email;
    private String password;
    private Role role; 

    public User(){}
    public User(String idUser, String nameUser, String lastName, String email, String password, Role role) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getIdUser() { return idUser; }
    public void setIdUser(String idUser) { this.idUser = idUser; }
    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }
    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }
    @Override
    public String toString() {
        return "\n" +
                "  Cédula: " + idUser + "\n\n" +
                "  Nombre: " + nameUser + "\n\n" +
                "  Apellidos: " + lastName + "\n\n" +
                "  Correo: " + email;
    }


}
