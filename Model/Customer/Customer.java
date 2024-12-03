/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Customer;

import java.time.LocalDate;
import java.time.Period;

/**
 *
 * @author sofia
 */
public class Customer {

    private String cedula;
    private String Nombre;
    private LocalDate fechaNacimiento;
    private String Contacto;
    private String tipoMembresia;

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return Nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getContacto() {
        return Contacto;
    }

    public String getTipoMembresia() {
        return tipoMembresia;
    }

    public void setContacto(String Contacto) {
        this.Contacto = Contacto;
    }

    public void setTipoMembresia(String tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
    

    public Customer(String cedula, String Nombre, LocalDate fechaNacimiento, String Contacto, String tipoMembresia) {
        this.cedula = cedula;
        this.Nombre = Nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.Contacto = Contacto;
        this.tipoMembresia = tipoMembresia;
    }

      public static int calulateAge(LocalDate fechaNacimiento) {
        if (fechaNacimiento == null) {
            throw new IllegalArgumentException("La fecha de nacimiento no puede ser nula");
        }
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }
    
}
