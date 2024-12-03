/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Pago;

import java.util.Date;

/**
 *
 * @author pamel
 */
public class PagoDTO {
    private int idpago;
    private String customer;
    private Date fecha;
    private double subtotal;
    private double impuesto;
    private double total;

    public int getIdPago() {
        return idpago;
    }

    public String getCustomer() {
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

    public PagoDTO(int idpago, String customer, Date fecha, double subtotal, double impuesto, double total) {
        this.idpago = idpago;
        this.customer = customer;
        this.fecha = fecha;
        this.subtotal = subtotal;
        this.impuesto = impuesto;
        this.total = total;
    }

  
}
