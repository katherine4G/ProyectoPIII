
package Model;

public abstract class User {
    protected String id_user;
    protected String nameUser;
    protected String lastName;
    protected String email;
    protected String password;
    protected int rol;

    public User(String id_User, String name, String lastName, String email, String password, int rol) {
        this.id_user = id_User;
        this.nameUser = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public void login() {
        System.out.println(nameUser + " ha iniciado sesión.");
    }

    public void logout() {
        System.out.println(nameUser + " ha cerrado sesión.");
    }

    public String getId_User() {
        return id_user;
    }

    public void setId_User(String id_User) {
        this.id_user = id_User;
    }

    public String getName() {
        return nameUser;
    }

    public void setName(String name) {
        this.nameUser = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRol() {
        return rol;
    }

    public void setRol(int rol) {
        this.rol = rol;
    }
   


    public abstract void showInfo();
}
