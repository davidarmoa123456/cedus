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
public class Dias {
    private int id_dia;
    private String nombre_dia;

    public Dias() {
    }

    public Dias(int id_dia, String nombre_dia) {
        this.id_dia = id_dia;
        this.nombre_dia = nombre_dia;
    }
    

    public int getId_dia() {
        return id_dia;
    }

    public void setId_dia(int id_dia) {
        this.id_dia = id_dia;
    }

    public String getNombre_dia() {
        return nombre_dia;
    }

    public void setNombre_dia(String nombre_dia) {
        this.nombre_dia = nombre_dia;
    }
    

}