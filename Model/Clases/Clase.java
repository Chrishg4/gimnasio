/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Clases;

import Model.Entrenadores.Entrenador;
import java.time.LocalDateTime;

/**
 *
 * @author sofia
 */
public class Clase {

    private int id;
    private String tipoClase;
    private LocalDateTime horario;
    private Entrenador entrenadorId;
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

    public Entrenador getEntrenadorId() {
        return entrenadorId;
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

    public void setEntrenadorId(Entrenador entrenadorId) {
        this.entrenadorId = entrenadorId;
    }

    public Clase(int id, String tipoClase, LocalDateTime horario, Entrenador entrenadorId, int capacidadMaxima) {
        this.id = id;
        this.tipoClase = tipoClase;
        this.horario = horario;
        this.entrenadorId = entrenadorId;
        this.capacidadMaxima = capacidadMaxima;
    }

}
