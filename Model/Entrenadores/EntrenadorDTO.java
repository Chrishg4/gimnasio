/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entrenadores;

/**
 *
 * @author sofia
 */
public class EntrenadorDTO {

    private int id;
    private String nombre;
    private String contacto;
    private String especialidades;

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContacto() {
        return contacto;
    }

    public String getEspecialidades() {
        return especialidades;
    }

    public EntrenadorDTO(int id, String nombre, String contacto, String especialidades) {
        this.id = id;
        this.nombre = nombre;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }

    public EntrenadorDTO(String nombre, String contacto, String especialidades) {
        this.nombre = nombre;
        this.contacto = contacto;
        this.especialidades = especialidades;
    }
    
    

}
