/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author David
 */
public class Convocatorias {
private  int  id_convocatoria ;
private String estado_convocatoria;
private  Años año;
private Cursos curso;
private Turnos turno;
private Secciones seccion;
private int matricula;
private int costo_total;
private int cupo_convocatoria;
private Date fecha_convocatoria; 



    public Convocatorias() {
    }

    public Convocatorias(int id_convocatoria, String estado_convocatoria, Años año, Cursos curso, Turnos turno, Secciones seccion, int matricula, int costo_total, int cupo_convocatoria, Date fecha_convocatoria) {
        this.id_convocatoria = id_convocatoria;
        this.estado_convocatoria = estado_convocatoria;
        this.año = año;
        this.curso = curso;
        this.turno = turno;
        this.seccion = seccion;
        this.matricula = matricula;
        this.costo_total = costo_total;
        this.cupo_convocatoria = cupo_convocatoria;
        this.fecha_convocatoria = fecha_convocatoria;
    }

    public int getId_convocatoria() {
        return id_convocatoria;
    }

    public void setId_convocatoria(int id_convocatoria) {
        this.id_convocatoria = id_convocatoria;
    }

    public String getEstado_convocatoria() {
        return estado_convocatoria;
    }

    public void setEstado_convocatoria(String estado_convocatoria) {
        this.estado_convocatoria = estado_convocatoria;
    }

    public Años getAño() {
        return año;
    }

    public void setAño(Años año) {
        this.año = año;
    }

    public Cursos getCurso() {
        return curso;
    }

    public void setCurso(Cursos curso) {
        this.curso = curso;
    }

    public Turnos getTurno() {
        return turno;
    }

    public void setTurno(Turnos turno) {
        this.turno = turno;
    }

    public Secciones getSeccion() {
        return seccion;
    }

    public void setSeccion(Secciones seccion) {
        this.seccion = seccion;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public int getCosto_total() {
        return costo_total;
    }

    public void setCosto_total(int costo_total) {
        this.costo_total = costo_total;
    }

    public int getCupo_convocatoria() {
        return cupo_convocatoria;
    }

    public void setCupo_convocatoria(int cupo_convocatoria) {
        this.cupo_convocatoria = cupo_convocatoria;
    }

    public Date getFecha_convocatoria() {
        return fecha_convocatoria;
    }

    public void setFecha_convocatoria(Date fecha_convocatoria) {
        this.fecha_convocatoria = fecha_convocatoria;
    }

  

    

   


   
            
}
