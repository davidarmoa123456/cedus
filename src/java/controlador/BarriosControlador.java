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

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class BarriosControlador {
    public static boolean agregar (Barrios barrio){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into barrios(nombre_barrio)"
                + "values('" + barrio.getNombre_barrio() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Barrios buscarId(Barrios barrio){
        if (Conexion.conectar()){
            String sql = "select * from barrios where id_barrio='" + barrio.getId_barrio() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    barrio.setId_barrio(rs.getInt("id_barrio"));
                    barrio.setNombre_barrio(rs.getString("nombre_barrio"));
                }else{
                    barrio.setId_barrio(0);
                    barrio.setNombre_barrio("");
                    //return null;
                    //return barrio;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return barrio;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from barrios where upper(nombre_barrio) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_barrio offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_barrio") + "</td>"
                                + "<td>" + rs.getString("nombre_barrio") + "</td>"
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
     public static boolean modificarbarrio(Barrios barrio) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update barrios set nombre_barrio='" + barrio.getNombre_barrio() + "'"
                    + " where id_barrio=" + barrio.getId_barrio();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Barrios barrio){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from barrios where id_barrio=" + barrio.getId_barrio();
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

