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
public class Personas {
    private int id_persona;
    private String nombre_persona;
    private String apellido_persona;
    private int ci_persona;
    private String telefono_persona;
    private String nacimiento_persona;
    private String direccion_persona;
    private String ocupacion_persona;
    Ciudades ciudad;
    Barrios barrio;
    Sexos sexo;
    Tipospersonas tipopersona;
    Estadosciviles estadocivil;
    Nacionalidades nacionalidad;

    public Personas() {
    }

    public Personas(int id_persona, String nombre_persona, String apellido_persona, int ci_persona, String telefono_persona, String nacimiento_persona, String direccion_persona, String ocupacion_persona, Ciudades ciudad, Barrios barrio, Sexos sexo, Tipospersonas tipopersona, Estadosciviles estadocivil, Nacionalidades nacionalidad) {
        this.id_persona = id_persona;
        this.nombre_persona = nombre_persona;
        this.apellido_persona = apellido_persona;
        this.ci_persona = ci_persona;
        this.telefono_persona = telefono_persona;
        this.nacimiento_persona = nacimiento_persona;
        this.direccion_persona = direccion_persona;
        this.ocupacion_persona = ocupacion_persona;
        this.ciudad = ciudad;
        this.barrio = barrio;
        this.sexo = sexo;
        this.tipopersona = tipopersona;
        this.estadocivil = estadocivil;
        this.nacionalidad = nacionalidad;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public void setNombre_persona(String nombre_persona) {
        this.nombre_persona = nombre_persona;
    }

    public String getApellido_persona() {
        return apellido_persona;
    }

    public void setApellido_persona(String apellido_persona) {
        this.apellido_persona = apellido_persona;
    }

    public int getCi_persona() {
        return ci_persona;
    }

    public void setCi_persona(int ci_persona) {
        this.ci_persona = ci_persona;
    }

    public String getTelefono_persona() {
        return telefono_persona;
    }

    public void setTelefono_persona(String telefono_persona) {
        this.telefono_persona = telefono_persona;
    }

    public String getNacimiento_persona() {
        return nacimiento_persona;
    }

    public void setNacimiento_persona(String nacimiento_persona) {
        this.nacimiento_persona = nacimiento_persona;
    }

    public String getDireccion_persona() {
        return direccion_persona;
    }

    public void setDireccion_persona(String direccion_persona) {
        this.direccion_persona = direccion_persona;
    }

    public String getOcupacion_persona() {
        return ocupacion_persona;
    }

    public void setOcupacion_persona(String ocupacion_persona) {
        this.ocupacion_persona = ocupacion_persona;
    }

    public Ciudades getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudades ciudad) {
        this.ciudad = ciudad;
    }

    public Barrios getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrios barrio) {
        this.barrio = barrio;
    }

    public Sexos getSexo() {
        return sexo;
    }

    public void setSexo(Sexos sexo) {
        this.sexo = sexo;
    }

    public Tipospersonas getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(Tipospersonas tipopersona) {
        this.tipopersona = tipopersona;
    }

    public Estadosciviles getEstadocivil() {
        return estadocivil;
    }

    public void setEstadocivil(Estadosciviles estadocivil) {
        this.estadocivil = estadocivil;
    }

    public Nacionalidades getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidades nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

   
   
}