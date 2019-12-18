/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author David
 */
public class CobrosMatricula {
 private int id_cobro_matricula;
  private Inscripciones inscripcion;
  private String fecha_cobro;
  private int monto_matricula;

    public CobrosMatricula() {
    }
  
    public CobrosMatricula(int id_cobro_matricula, Inscripciones inscripcion, String fecha_cobro, int monto_matricula) {
        this.id_cobro_matricula = id_cobro_matricula;
        this.inscripcion = inscripcion;
        this.fecha_cobro = fecha_cobro;
        this.monto_matricula = monto_matricula;
  
     }

    public int getId_cobro_matricula() {
        return id_cobro_matricula;
    }

    public void setId_cobro_matricula(int id_cobro_matricula) {
        this.id_cobro_matricula = id_cobro_matricula;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public String getFecha_cobro() {
        return fecha_cobro;
    }

    public void setFecha_cobro(String fecha_cobro) {
        this.fecha_cobro = fecha_cobro;
    }

    public int getMonto_matricula() {
        return monto_matricula;
    }

    public void setMonto_matricula(int monto_matricula) {
        this.monto_matricula = monto_matricula;
    }
    
     
    
}

