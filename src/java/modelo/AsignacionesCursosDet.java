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
public class AsignacionesCursosDet {
   private int id_asignacion_cursodet; 
  private Personas persona;
  private Horarios horario; 
  private Dias dia;
  private Aulas aula;
  private AsignacionesCursos asignacioncurso;

    public AsignacionesCursosDet() {
    }

    public AsignacionesCursosDet(int id_asignacion_cursodet, Personas persona, Horarios horario, Dias dia, Aulas aula, AsignacionesCursos asignacioncurso) {
        this.id_asignacion_cursodet = id_asignacion_cursodet;
        this.persona = persona;
        this.horario = horario;
        this.dia = dia;
        this.aula = aula;
        this.asignacioncurso = asignacioncurso;
    }

    public int getId_asignacion_cursodet() {
        return id_asignacion_cursodet;
    }

    public void setId_asignacion_cursodet(int id_asignacion_cursodet) {
        this.id_asignacion_cursodet = id_asignacion_cursodet;
    }

    public Personas getPersona() {
        return persona;
    }

    public void setPersona(Personas persona) {
        this.persona = persona;
    }

    public Horarios getHorario() {
        return horario;
    }

    public void setHorario(Horarios horario) {
        this.horario = horario;
    }

    public Dias getDia() {
        return dia;
    }

    public void setDia(Dias dia) {
        this.dia = dia;
    }

    public Aulas getAula() {
        return aula;
    }

    public void setAula(Aulas aula) {
        this.aula = aula;
    }

    public AsignacionesCursos getAsignacioncurso() {
        return asignacioncurso;
    }

    public void setAsignacioncurso(AsignacionesCursos asignacioncurso) {
        this.asignacioncurso = asignacioncurso;
    }
  
    
}
