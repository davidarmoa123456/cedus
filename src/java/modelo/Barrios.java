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
public class Barrios {
    private int id_barrio;
    private String nombre_barrio;

    public Barrios() {
    }

    public Barrios(int id_barrio, String nombre_barrio) {
        this.id_barrio = id_barrio;
        this.nombre_barrio = nombre_barrio;
    }
    

    public int getId_barrio() {
        return id_barrio;
    }

    public void setId_barrio(int id_barrio) {
        this.id_barrio = id_barrio;
    }

    public String getNombre_barrio() {
        return nombre_barrio;
    }

    public void setNombre_barrio(String nombre_barrio) {
        this.nombre_barrio = nombre_barrio;
    }
    

}