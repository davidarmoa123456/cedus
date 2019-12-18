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
public class ConvocatoriasDetalles {
  private int   id_convocatoria_detalle;
  private String estado_materia;
  private Convocatorias convocatoria;
  private Asignaturas asignatura;

    public ConvocatoriasDetalles() {
    }

    public ConvocatoriasDetalles(int id_convocatoria_detalle, String estado_materia, Convocatorias convocatoria, Asignaturas asignatura) {
        this.id_convocatoria_detalle = id_convocatoria_detalle;
        this.estado_materia = estado_materia;
        this.convocatoria = convocatoria;
        this.asignatura = asignatura;
    }

    public int getId_convocatoria_detalle() {
        return id_convocatoria_detalle;
    }

    public void setId_convocatoria_detalle(int id_convocatoria_detalle) {
        this.id_convocatoria_detalle = id_convocatoria_detalle;
    }

    public String getEstado_materia() {
        return estado_materia;
    }

    public void setEstado_materia(String estado_materia) {
        this.estado_materia = estado_materia;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Asignaturas getAsignatura() {
        return asignatura;
    }

    public void setAsignatura(Asignaturas asignatura) {
        this.asignatura = asignatura;
    }

   
  
}
