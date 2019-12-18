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
import modelo.Horarios;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class HorariosControlador {
    public static boolean agregar (Horarios hora){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into horarios(inicio_hora,fin_hora)"
                + "values('" + hora.getInicio_hora() + "','" + hora.getFin_hora() + "')";

        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
    public static Horarios buscarId(Horarios hora){
        if (Conexion.conectar()){
            String sql = "select * from horarios where id_horario='" + hora.getId_horario()+ "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    hora.setId_horario(rs.getInt("id_horario"));
                    hora.setInicio_hora(rs.getString("inicio_hora"));
                    hora.setFin_hora(rs.getString("fin_hora"));
                }else{
                    hora.setId_horario(0);
                    hora.setInicio_hora("");
                    hora.setFin_hora("");
                    //return null;
                    //return hora;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return hora;
    }
    public static String buscarInicio(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from horarios curret_time  " +
                "order by id_horario offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA; 
                System.out.println("Llega"+sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_horario") + "</td>"
                                + "<td>" + rs.getTime("inicio_hora") + "</td>"
                                + "<td>" + rs.getTime("fin_hora") + "</td>"
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
     public static boolean modificarhora(Horarios hora) {
         boolean valor= false;
        if (Conexion.conectar()){
            String sql = "update horarios set inicio_hora='" + hora.getInicio_hora() + "'"
                    + " where id_hora=" + hora.getId_horario();
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.out.println("Error:" + ex);
        }
     }
    return valor;
     }
     public static boolean eliminar(Horarios hora){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from horarios where id_hora=" + hora.getId_horario();
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


