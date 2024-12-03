/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Customer;

import java.sql.Date;

/**
 *
 * @author sofia
 */
public class CustomerDTO {

    private String cedula;
    private String Nombre;
    private Date fechaNacimiento;
    private String Contacto;
    private String tipoMembresia;

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getContacto() {
        return Contacto;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public CustomerDTO(String cedula, String Nombre, Date fechaNacimiento, String Contacto, String tipoMembresia) {
        this.cedula = cedula;
        this.Nombre = Nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.Contacto = Contacto;
        this.tipoMembresia = tipoMembresia;
    }
    
    
    
}
