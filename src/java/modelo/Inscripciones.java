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
public class Inscripciones {

    private int id_inscripcion;
    private String fecha_inscripcion;
    private Convocatorias convocatoria;
    private Personas persona;
    private int nro_cuotas;

    public Inscripciones() {
    }

    public Inscripciones(int id_inscripcion, String fecha_inscripcion, Convocatorias convocatoria, Personas persona, int nro_cuotas) {
        this.id_inscripcion = id_inscripcion;
        this.fecha_inscripcion = fecha_inscripcion;
        this.convocatoria = convocatoria;
        this.persona = persona;
        this.nro_cuotas = nro_cuotas;
    }

    public int getId_inscripcion() {
        return id_inscripcion;
    }

    public void setId_inscripcion(int id_inscripcion) {
        this.id_inscripcion = id_inscripcion;
    }

    public String getFecha_inscripcion() {
        return fecha_inscripcion;
    }

    public void setFecha_inscripcion(String fecha_inscripcion) {
        this.fecha_inscripcion = fecha_inscripcion;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public int getNro_cuotas() {
        return nro_cuotas;
    }

    public void setNro_cuotas(int nro_cuotas) {
        this.nro_cuotas = nro_cuotas;
    }

    

}
