/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Usuarios;

/**
 *
 * @author chris
 */
public class Usuario {
      

    
    private int id;
   // private String username;
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

    public void setId(int id) {
        this.id = id;
    }

   

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRol(Roles rol) {
        this.rol = rol;
    }

    public Usuario( String password, String nombre, String email, Roles rol) {
        
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    public Usuario(int id,  String password, String nombre, String email, Roles rol) {
        this.id = id;
       
        this.password = password;
        this.nombre = nombre;
        this.email = email;
        this.rol = rol;
    }

    
    /**
     *
     */
    public Usuario(){
        this(0,"","","",Roles.ADMINISTRADOR);
        
    }

    
}
//    
//     // Método para verificar si es administrador
//    public boolean esAdministrador() {
//        return ROL_ADMINISTRADOR.equals(this.rol);
//    }
//
//    // Método para verificar si es entrenador
//    public boolean esEntrenador() {
//        return ROL_ENTRENADOR.equals(this.rol);
//    }