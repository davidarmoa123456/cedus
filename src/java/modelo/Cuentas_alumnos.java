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
public class Cuentas_alumnos {
    private int id_cuentaalumno;
    private Inscripciones inscripcion;
    private int montocuota;
    private String fecha_vencimiento;
    private String estado;
    private int numero_cuta;
    private String persona;
    private int ci_persona;
    

    public Cuentas_alumnos() {
    }

    public Cuentas_alumnos(int id_cuentaalumno, Inscripciones inscripcion, int montocuota, String fecha_vencimiento, String estado, int numero_cuta, String persona, int ci_persona) {
        this.id_cuentaalumno = id_cuentaalumno;
        this.inscripcion = inscripcion;
        this.montocuota = montocuota;
        this.fecha_vencimiento = fecha_vencimiento;
        this.estado = estado;
        this.numero_cuta = numero_cuta;
        this.persona = persona;
        this.ci_persona = ci_persona;
    }

    public int getId_cuentaalumno() {
        return id_cuentaalumno;
    }

    public void setId_cuentaalumno(int id_cuentaalumno) {
        this.id_cuentaalumno = id_cuentaalumno;
    }

    public Inscripciones getInscripcion() {
        return inscripcion;
    }

    public void setInscripcion(Inscripciones inscripcion) {
        this.inscripcion = inscripcion;
    }

    public int getMontocuota() {
        return montocuota;
    }

    public void setMontocuota(int montocuota) {
        this.montocuota = montocuota;
    }

    public String getFecha_vencimiento() {
        return fecha_vencimiento;
    }

    public void setFecha_vencimiento(String fecha_vencimiento) {
        this.fecha_vencimiento = fecha_vencimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getNumero_cuta() {
        return numero_cuta;
    }

    public void setNumero_cuta(int numero_cuta) {
        this.numero_cuta = numero_cuta;
    }

    public String getPersona() {
        return persona;
    }

    public void setPersona(String persona) {
        this.persona = persona;
    }

    public int getCi_persona() {
        return ci_persona;
    }

    public void setCi_persona(int ci_persona) {
        this.ci_persona = ci_persona;
    }
    

    
   
}