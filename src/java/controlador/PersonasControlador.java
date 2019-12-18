/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Barrios;
import modelo.Ciudades;
import modelo.Estadosciviles;
import modelo.Nacionalidades;
import modelo.Personas;
import modelo.Sexos;
import modelo.Tipospersonas;
import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class PersonasControlador {
    public static boolean agregar (Personas persona){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into personas(nombre_persona,apellido_persona,ci_persona,telefono_persona,nacimiento_persona,direccion_persona,ocupacion_persona,id_ciudad,id_barrio,id_estadocivil,id_nacionalidad,id_sexo,id_tipopersona)"
                + "values('" + persona.getNombre_persona() + "','" + persona.getApellido_persona() + "','" + persona.getCi_persona() + "','" + persona.getTelefono_persona() + "','" + persona.getNacimiento_persona()+ "','" + persona.getDireccion_persona()+ "','" + persona.getOcupacion_persona()+ "','" + persona.getCiudad().getId_ciudad()+ "','" + persona.getBarrio().getId_barrio()+ "','" + persona.getEstadocivil().getId_estadocivil()+ "','" + persona.getNacionalidad().getId_nacionalidad()+ "','" + persona.getSexo().getId_sexo()+ "','" + persona.getTipopersona().getId_tipopersona()+ "')";
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
            
        }
    }
    return valor;
    
    
}
    public static Personas buscarId(Personas persona){
        if (Conexion.conectar()){
            String sql = "select * from personas p inner join ciudades c on p.id_ciudad=c.id_ciudad inner join barrios ba on p.id_barrio=ba.id_barrio inner join estadosciviles es on p.id_estadocivil=es.id_estadocivil inner join nacionalidades na on p.id_nacionalidad=na.id_nacionalidad inner join sexos s on p.id_sexo=s.id_sexo inner join tipos_personas tp on p.id_tipopersona=tp.id_tipopersona where p.id_persona='" + persona.getId_persona() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setApellido_persona(rs.getString("apellido_persona"));
                    persona.setCi_persona(rs.getInt("ci_persona"));
                    persona.setTelefono_persona(rs.getString("telefono_persona"));
                    persona.setNacimiento_persona(rs.getString("nacimiento_persona"));
                    persona.setDireccion_persona(rs.getString("direccion_persona"));
                    persona.setOcupacion_persona(rs.getString("ocupacion_persona"));
                    
                    Ciudades ciudad= new Ciudades();
                    ciudad.setId_ciudad(rs.getInt("id_ciudad"));
                    ciudad.setNombre_ciudad(rs.getString("nombre_ciudad"));
                    persona.setCiudad(ciudad);
                    
                    Barrios barrio=new Barrios();
                    barrio.setId_barrio(rs.getInt("id_barrio"));
                    barrio.setNombre_barrio(rs.getString("nombre_barrio"));
                    persona.setBarrio(barrio);
                    
                    Estadosciviles estadocivil=new Estadosciviles();
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                     persona.setEstadocivil(estadocivil);
                     
                    Nacionalidades nacionalidad=new Nacionalidades();
                    nacionalidad.setId_nacionalidad(rs.getInt("id_nacionalidad"));
                    nacionalidad.setNombre_nacionalidad(rs.getString("nombre_nacionalidad"));
                    persona.setNacionalidad(nacionalidad);
                   
                    Sexos sexo=new Sexos();
                    sexo.setId_sexo(rs.getInt("id_sexo"));
                    sexo.setNombre_sexo(rs.getString("nombre_sexo"));
                    persona.setSexo(sexo);
                    
                    Tipospersonas tipopersona=new Tipospersonas();
                    tipopersona.setId_tipopersona(rs.getInt("id_tipopersona"));
                    tipopersona.setNombre_tipopersona(rs.getString("nombre_tipopersona"));
                    persona.setTipopersona(tipopersona);
                    
                }else{
                    persona.setId_persona(0);
                    persona.setNombre_persona("");
                    persona.setApellido_persona("");
                    persona.setCi_persona(0);
                    persona.setTelefono_persona("");
                    persona.setNacimiento_persona("");
                    persona.setDireccion_persona("");
                    persona.setOcupacion_persona("");
                    Ciudades ciudad= new Ciudades();
                    
                    ciudad.setId_ciudad(0);
                    ciudad.setNombre_ciudad("");
                    persona.setCiudad(ciudad);
                    
                     Barrios barrio=new Barrios();
                    barrio.setId_barrio(0);
                    barrio.setNombre_barrio("");
                    persona.setBarrio(barrio);
                    
                    Estadosciviles estadocivil=new Estadosciviles();
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");
                     persona.setEstadocivil(estadocivil);
                     
                    Nacionalidades nacionalidad=new Nacionalidades();
                    nacionalidad.setId_nacionalidad(0);
                    nacionalidad.setNombre_nacionalidad("");
                    persona.setNacionalidad(nacionalidad);
                   
                    Sexos sexo=new Sexos();
                    sexo.setId_sexo(0);
                    sexo.setNombre_sexo("");
                    persona.setSexo(sexo);
                    
                    Tipospersonas tipopersona=new Tipospersonas();
                    tipopersona.setId_tipopersona(0);
                    tipopersona.setNombre_tipopersona("");
                    persona.setTipopersona(tipopersona);
                   
                    //return null;
                    //return persona;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return persona;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        
        if (Conexion.conectar()){
            try {
                String sql = "select * from personas p where cast (p.ci_persona as varchar(20)) like '%" +
                nombre + "%'" + "order by id_persona offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("ERROR: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("ERROR: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static String buscarAlumno(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        
        if (Conexion.conectar()){
            try {
                String sql = "select * from personas p inner join tipos_personas tp on p.id_tipopersona=tp.id_tipopersona where nombre_tipopersona='ALUMNO'"
                        + "AND  not exists (select * from inscripciones i where p.id_persona = i.id_persona ) AND "
                        + "Cast (p.ci_persona as varchar(20)) like '%" +
                nombre + "%'" + "order by id_persona offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                      
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=2>No exixten registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("ERROR: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("ERROR: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static String buscarProfesor(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        
        if (Conexion.conectar()){
            try {
                String sql = "select * from personas p inner join tipos_personas tp on p.id_tipopersona=tp.id_tipopersona "
                        + "where nombre_tipopersona='PROFESOR' And "
                        + "Cast (p.ci_persona as varchar(20)) like '%" +
                nombre + "%'" + "order by id_persona offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                      
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td colspan=2>No exixten registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("ERROR: " + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("ERROR: " + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
     public static boolean modificarpersona(Personas persona) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update personas set nombre_persona='" + persona.getNombre_persona() + "',apellido_persona='" + persona.getApellido_persona() + "',ci_persona='" + persona.getCi_persona() + "',telefono_persona='" + persona.getTelefono_persona() + "',nacimiento_persona='" + persona.getNacimiento_persona() + "',direccion_persona='" + persona.getDireccion_persona() + "',ocupacion_persona='" + persona.getOcupacion_persona() + "',id_ciudad='" + persona.getCiudad().getId_ciudad() + "',id_barrio='" + persona.getBarrio().getId_barrio() + "',id_estadocivil='" + persona.getEstadocivil().getId_estadocivil() + "',id_nacionalidad='" + persona.getNacionalidad().getId_nacionalidad() + "',id_sexo='" + persona.getSexo().getId_sexo()+ "',id_tipopersona='" + persona.getTipopersona().getId_tipopersona() + "'"
                    + " where id_persona=" + persona.getId_persona();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Personas persona){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from personas where id_persona=" + persona.getId_persona();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("ERROR:" + ex);
             }
         }
         return valor;
     }
      public static Personas noDuplicados(Personas persona){
        if (Conexion.conectar()){
            String sql = "select * from personas where ci_persona='" + persona.getCi_persona() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                persona.setCi_persona(0); 
                
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return persona;
    }
  


}

