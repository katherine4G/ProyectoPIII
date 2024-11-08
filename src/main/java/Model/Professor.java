/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

public class Professor extends User {

    public Professor(String id_User, String name, String lastName, String email, String password, int rol) {
        super(id_User, name, lastName, email, password, rol);
    }

    @Override
    public void showInfo() {
    }
    
    
}
