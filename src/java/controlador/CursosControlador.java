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
import modelo.Cursos;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class CursosControlador {
    public static Cursos buscarId(Cursos curso){
        if (Conexion.conectar()){
            String sql = "select * from cursos where id_curso='" + curso.getId_curso() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    curso.setId_curso(rs.getInt("id_curso"));
                    curso.setNombre_curso(rs.getString("nombre_curso"));
                }else{
                    curso.setId_curso(0);
                    curso.setNombre_curso("");
                    //return null;
                    //return curso;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return curso;
    }

    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from cursos where upper(nombre_curso) like '%" +
                nombre.toUpperCase() + "%'" + "order by id_curso offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
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
    
}

