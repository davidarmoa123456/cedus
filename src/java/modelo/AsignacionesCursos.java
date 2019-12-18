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
public class AsignacionesCursos {
    private int id_asignacion_curso;
    private Convocatorias convocatoria;
    private ConvocatoriasDetalles convocatoriadetalle;

    public AsignacionesCursos() {
    }

    public AsignacionesCursos(int id_asignacion_curso, Convocatorias convocatoria, ConvocatoriasDetalles convocatoriadetalle) {
        this.id_asignacion_curso = id_asignacion_curso;
        this.convocatoria = convocatoria;
        this.convocatoriadetalle = convocatoriadetalle;
    }

    public int getId_asignacion_curso() {
        return id_asignacion_curso;
    }

    public void setId_asignacion_curso(int id_asignacion_curso) {
        this.id_asignacion_curso = id_asignacion_curso;
    }

    public Convocatorias getConvocatoria() {
        return convocatoria;
    }

    public void setConvocatoria(Convocatorias convocatoria) {
        this.convocatoria = convocatoria;
    }

    public ConvocatoriasDetalles getConvocatoriadetalle() {
        return convocatoriadetalle;
    }

    public void setConvocatoriadetalle(ConvocatoriasDetalles convocatoriadetalle) {
        this.convocatoriadetalle = convocatoriadetalle;
    }
    
}
   