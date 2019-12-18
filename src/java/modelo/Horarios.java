/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Administrator
 */
public class Horarios {
    private int id_horario;
    private String inicio_hora;
    private String fin_hora;

    public Horarios() {
    }

    public Horarios(int id_horario, String inicio_hora, String fin__hora) {
        this.id_horario = id_horario;
        this.inicio_hora = inicio_hora;
        this.fin_hora = fin__hora;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getInicio_hora() {
        return inicio_hora;
    }

    public void setInicio_hora(String inicio_hora) {
        this.inicio_hora = inicio_hora;
    }

    public String getFin_hora() {
        return fin_hora;
    }

    public void setFin_hora(String fin_hora) {
        this.fin_hora = fin_hora;
    }

    
}