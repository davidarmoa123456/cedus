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
import modelo.Turnos;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class TurnosControlador {
    public static boolean agregar (Turnos turno){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into turnos(nombre_turno)"
                + "values('" + turno.getNombre_turno() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Turnos buscarId(Turnos turno){
        if (Conexion.conectar()){
            String sql = "select * from turnos where id_turno='" + turno.getId_turno() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    turno.setId_turno(rs.getInt("id_turno"));
                    turno.setNombre_turno(rs.getString("nombre_turno"));
                }else{
                    turno.setId_turno(0);
                    turno.setNombre_turno("");
                    //return null;
                    //return turno;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return turno;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from turnos where upper(nombre_turno) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_turno offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")){
                        tabla = "<tr><td> colspan=2>No exixten registros...</td></tr>";
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
     public static boolean modificarturno(Turnos turno) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update turnos set nombre_turno='" + turno.getNombre_turno() + "'"
                    + " where id_turno=" + turno.getId_turno();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Turnos turno){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from turnos where id_turno=" + turno.getId_turno();
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


