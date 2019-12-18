/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Años;


import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class AñosControlador {
    public static boolean agregar (Años año){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into años(periodo_año)"
                + "values('" + año.getPeriodo_año() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Años buscarId(Años año){
        if (Conexion.conectar()){
            String sql = "select *from años  where id_año='" + año.getId_año()+ "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    año.setId_año(rs.getInt("id_año"));
                    año.setPeriodo_año(rs.getString("periodo_año"));
               
                    
                }else{
                     año.setId_año(0);
                    año.setPeriodo_año("");
                   
                    //return null;
                    //return año;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return año;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from años where upper(periodo_año) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_año offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_año") + "</td>"
                                + "<td>" + rs.getString("periodo_año") + "</td>"
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
     public static boolean modificaraño(Años año) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update años set periodo_año='" + año.getPeriodo_año() + "'"                 
                    + " where id_año=" + año.getId_año();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Años año){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from años where id_año=" + año.getId_año();
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


