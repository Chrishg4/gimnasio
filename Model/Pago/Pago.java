/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pago;

import Model.Customer.Customer;
import java.util.Date;

/**
 *
 * @author pamel
 */
public class Pago {
    private int idpago;
    private Customer customer;
    private Date fecha;
    private double subtotal;
    private double impuesto;
    private double total;

    public int getIdpago() {
        return idpago;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public Pago(int idpago, Customer customer, Date fecha, double subtotal, double impuesto, double total) {
        this.idpago = idpago;
        this.customer = customer;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pago{" + "idpago=" + idpago + ", customer=" + customer + ", fecha=" + fecha + ", subtotal=" + subtotal + ", impuesto=" + impuesto + ", total=" + total + '}';
    }

   
}
