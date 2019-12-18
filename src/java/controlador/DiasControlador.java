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
import modelo.Dias;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class DiasControlador {
    public static boolean agregar (Dias dia){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into dias(nombre_dia)"
                + "values('" + dia.getNombre_dia() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Dias buscarId(Dias dia){
        if (Conexion.conectar()){
            String sql = "select * from dias where id_dia='" + dia.getId_dia() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    dia.setId_dia(rs.getInt("id_dia"));
                    dia.setNombre_dia(rs.getString("nombre_dia"));
                }else{
                    dia.setId_dia(0);
                    dia.setNombre_dia("");
                    //return null;
                    //return dia;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return dia;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from dias where upper(nombre_dia) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_dia offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_dia") + "</td>"
                                + "<td>" + rs.getString("nombre_dia") + "</td>"
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
     public static boolean modificardia(Dias dia) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update dias set nombre_dia='" + dia.getNombre_dia() + "'"
                    + " where id_dia=" + dia.getId_dia();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Dias dia){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from dias where id_dia=" + dia.getId_dia();
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

