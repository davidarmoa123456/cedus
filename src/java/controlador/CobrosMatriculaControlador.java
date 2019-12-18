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
import modelo.CobrosMatricula;
import modelo.Convocatorias;
import modelo.Inscripciones;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class CobrosMatriculaControlador {
    public static boolean agregar (CobrosMatricula cobro_matricula){
    boolean valor= false;
    if(Conexion.conectar ()){
        
        String sql = "insert into cobros_matriculas(fecha_cobro,id_inscripcion,monto_matricula)"
                + "values('" + cobro_matricula.getFecha_cobro()+  "','"+cobro_matricula.getInscripcion().getId_inscripcion()+"','"+cobro_matricula.getMonto_matricula()+"')";
                System.out.println(sql);
        try {
            Conexion.getSt().executeUpdate(sql);
            valor = true;
        } catch (SQLException ex) {
            System.err.println("Error:" + ex);
        }
    }
    return valor;
    
    
}
     public static CobrosMatricula GenerarCodigoCo(CobrosMatricula cobro_matricula) {
        if (Conexion.conectar()) {
             String sql="select (max(id_cobro_matricula))+1 as cod from cobros_matriculas";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cobro_matricula.setId_cobro_matricula(rs.getInt("cod"));
                } 
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return cobro_matricula;
    }
    public static CobrosMatricula buscarId(CobrosMatricula cobro_matricula){
        if (Conexion.conectar()){
            String sql = "select * from cobros_matriculas where id_cobro_matricula='" + cobro_matricula.getId_cobro_matricula() + "'";
            System.out.println("sql "+sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()){
                    cobro_matricula.setId_cobro_matricula(rs.getInt("id_cobro_matricula"));
                    cobro_matricula.setFecha_cobro(rs.getString("fecha_cobro"));
                    cobro_matricula.setMonto_matricula(rs.getInt("monto_matricula"));
                    
                   
                    
                    Inscripciones inscripcion=new Inscripciones();
                    inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                 
                    
                    cobro_matricula.setInscripcion(inscripcion);
                }else{
                    cobro_matricula.setId_cobro_matricula(0);
                    cobro_matricula.setFecha_cobro("");
                    Convocatorias convocatoria=new Convocatorias();
                    convocatoria.setMatricula(0);
                    
                    Inscripciones inscripcion=new Inscripciones();
                    inscripcion.setId_inscripcion(0);
                    
                    cobro_matricula.setInscripcion(inscripcion);
                    //return null;
                    //return cobro_matricula;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return cobro_matricula;
    }
    public static String buscarNombre(String nombre, int pagina){
        int offset = (pagina - 1)*utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()){
            try {
                String sql = "select * from cobros_matriculas order by id_cobro_matricula offset " + offset + " limit " +
                        utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)){
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cobro_matricula") + "</td>"
                                + "<td>" + rs.getString("fecha_cobro") + "</td>"
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
     
     public static boolean eliminar(CobrosMatricula cobro_matricula){
         boolean valor = false;
         if (Conexion.conectar()){
             String sql = "delete from cobros_matriculas where id_cobro_matricula=" + cobro_matricula.getId_cobro_matricula();
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


