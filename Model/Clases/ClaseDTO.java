/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Clases;

import java.time.LocalDateTime;

/**
 *
 * @author sofia
 */
public class ClaseDTO {
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

    public int getEntrenadorId() {
        return entrenadorId;
    }

    public int getCapacidadMaxima() {
        return capacidadMaxima;
    }

    public ClaseDTO(int id, String tipoClase, LocalDateTime horario, int entrenadorId, int capacidadMaxima) {
        this.id = id;
        this.tipoClase = tipoClase;
        this.horario = horario;
        this.entrenadorId = entrenadorId;
        this.capacidadMaxima = capacidadMaxima;
    }
    
    
    
}
