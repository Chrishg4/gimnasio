/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Clases;

import Model.Entrenadores.Entrenador;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author sofia
 */
public class Clase {

    private int id;
    private String tipoClase;
    private LocalDateTime horario;
    private int entrenadorId;
    private int capacidadMaxima;

    public int getId() {
        return id;
    }

    public String getTipoClase() {
        return tipoClase;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setEntrenadorId(int entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public void setTipoClase(String tipoClase) {
        this.tipoClase = tipoClase;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    

    public void setCapacidadMaxima(int capacidadMaxima) {
        this.capacidadMaxima = capacidadMaxima;
    }

    
    public Clase(int id, String tipoClase, LocalDateTime horario, int entrenadorId, int capacidadMaxima) {
        this.id = id;
        this.tipoClase = tipoClase;
        this.horario = horario;
        this.entrenadorId = entrenadorId;
        this.capacidadMaxima = capacidadMaxima;
    }

    public int getEntrenadorId() {
        return entrenadorId;
    }

   
}
