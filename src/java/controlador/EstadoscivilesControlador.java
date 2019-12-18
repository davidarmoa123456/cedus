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
import modelo.Estadosciviles;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class EstadoscivilesControlador {
    public static boolean agregar (Estadosciviles estadocivil){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into estadosciviles(nombre_estadocivil)"
                + "values('" + estadocivil.getNombre_estadocivil() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Estadosciviles buscarId(Estadosciviles estadocivil){
        if (Conexion.conectar()){
            String sql = "select * from estadosciviles where id_estadocivil='" + estadocivil.getId_estadocivil() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    estadocivil.setId_estadocivil(rs.getInt("id_estadocivil"));
                    estadocivil.setNombre_estadocivil(rs.getString("nombre_estadocivil"));
                }else{
                    estadocivil.setId_estadocivil(0);
                    estadocivil.setNombre_estadocivil("");
                    //return null;
                    //return estadocivil;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return estadocivil;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from estadosciviles where upper(nombre_estadocivil) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_estadocivil offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_estadocivil") + "</td>"
                                + "<td>" + rs.getString("nombre_estadocivil") + "</td>"
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
     public static boolean modificarestadocivil(Estadosciviles estadocivil) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update estadosciviles set nombre_estadocivil='" + estadocivil.getNombre_estadocivil() + "'"
                    + " where id_estadocivil=" + estadocivil.getId_estadocivil();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Estadosciviles estadocivil){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from estadosciviles where id_estadocivil=" + estadocivil.getId_estadocivil();
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

