/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

/**
 *
 * @author chris
 */
public class UsuarioDTO {
     

    // Atributos privados
    private int id;
    
    private String password;
    private String nombre;
    private String email;
    private Roles rol;

   

    public int getId() {
        return id;
    }

  

    public String getPassword() {
        return password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public Roles getRol() {
        return rol;
    }


    public UsuarioDTO(int id, String password, String nombre, String email, Roles rol) {
        this.id = id;
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }
  

   

    
}
