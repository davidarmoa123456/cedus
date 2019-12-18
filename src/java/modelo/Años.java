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
public class Años {
    private int id_año;
    private String periodo_año;
    
 

    public Años() {
    }

    public Años(int id_año, String periodo_año) {
        this.id_año = id_año;
        this.periodo_año = periodo_año;
       
      
    }

    public int getId_año() {
        return id_año;
    }

    public void setId_año(int id_año) {
        this.id_año = id_año;
    }

    public String getPeriodo_año() {
        return periodo_año;
    }

    public void setPeriodo_año(String periodo_año) {
        this.periodo_año = periodo_año;
    }

   
   
    

   
}