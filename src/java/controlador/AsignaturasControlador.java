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
import modelo.Asignaturas;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class AsignaturasControlador {
    public static boolean agregar (Asignaturas asignatura){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into asignaturas(nombre_asignatura)"
                + "values('" + asignatura.getNombre_asignatura() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Asignaturas buscarId(Asignaturas asignatura){
        if (Conexion.conectar()){
            String sql = "select * from asignaturas where id_asignatura='" + asignatura.getId_asignatura() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                    asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                }else{
                    asignatura.setId_asignatura(0);
                    asignatura.setNombre_asignatura("");
                    //return null;
                    //return asignatura;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return asignatura;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from asignaturas where upper(nombre_asignatura) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_asignatura offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td> <colspan=2>No exixten registros...</td></tr>";
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
     public static String buscarAsignaturaAsignacion(int id) {
        String valor = "";
         System.out.println(id);
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where ad.id_convocatoria=" + id + " "
                        + "order by id_convocatoria_detalle";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria_detalle") + "</td>"
                                + "<td>" + rs.getString("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("estado_materia") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getInt("id_convocatoria_detalle") + ")'"
                                + " type='button' class='btn btn-primary btn-sm'><span class='glyphicon glyphicon-pencil'>"
                                + "</span></button></td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=6>No existen registros ...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static String buscarNombreAsignatura(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from asignaturas where upper(nombre_asignatura) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_asignatura offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td> <colspan=2>No exixten registros...</td></tr>";
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
     public static boolean modificarasignatura(Asignaturas asignatura) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update asignaturas set nombre_asignatura='" + asignatura.getNombre_asignatura() + "'"
                    + " where id_asignatura=" + asignatura.getId_asignatura();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Asignaturas asignatura){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from asignaturas where id_asignatura=" + asignatura.getId_asignatura();
             try {
                 Conexion.getSt().executeUpdate(sql);
                 valor = true;
             } catch (SQLException ex) {
                 System.err.println("ERROR:" + ex);
             }
         }
         return valor;
     }
  


}


