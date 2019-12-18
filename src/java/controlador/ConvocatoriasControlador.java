/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import modelo.Años;
import modelo.Convocatorias;
import modelo.Cursos;
import modelo.Secciones;
import modelo.Turnos;
import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author David
 */
public class ConvocatoriasControlador {
 
    public static Convocatorias buscarId(int id) {
        Convocatorias convocatorias= null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias ac"
                        + " left join Años a on ac.id_año=a.id_año inner join cursos cu on ac.id_curso=cu.id_curso inner join turnos tu"
                        + " on ac.id_turno=tu.id_turno inner join secciones se on ac.id_seccion=se.id_seccion "
                        + " where id_convocatoria = ?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatorias= new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));
                        convocatorias.setEstado_convocatoria(rs.getString("estado_convocatoria"));
                        convocatorias.setMatricula(rs.getInt("matricula"));
                        convocatorias.setCosto_total(rs.getInt("costo_total"));
                        convocatorias.setCupo_convocatoria(rs.getInt("cupo_convocatoria"));
                        convocatorias.setFecha_convocatoria(rs.getDate("fecha_convocatoria"));
                       
                        
                        
                       Años año= new Años();
                       año.setId_año(rs.getInt("id_año"));
                       año.setPeriodo_año(rs.getString("periodo_año"));
                       convocatorias.setAño(año);
                       
                       Cursos curso= new Cursos();
                       curso.setId_curso(rs.getInt("id_curso"));
                       curso.setNombre_curso(rs.getString("nombre_curso"));
                       convocatorias.setCurso(curso);
                       
                       Turnos turno= new Turnos();
                       turno.setId_turno(rs.getInt("id_turno"));
                       turno.setNombre_turno(rs.getString("nombre_turno"));
                       convocatorias.setTurno(turno);
                       
                       Secciones seccion= new Secciones();
                       seccion.setId_seccion(rs.getInt("id_seccion"));
                       seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                       convocatorias.setSeccion(seccion);
                       
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatorias;
    }
    /* $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").val(json.id_convocatoria);
            $("#estado_convocatoria").val(json.estado_convocatoria);
            $("#matricula").val(json.matricula);
            $("#costo_total").val(json.costo_total);
            $("#cupo_convocatoria").val(json.cupo_convocatoria);
            $("#fecha_convocatoria").val(json.fecha_convocatoria);
            $("#id_año").val(json.id_año);
            $("#periodo_año").val(json.periodo_año);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#id_seccion").val(json.id_seccion);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#contenidoDetalle").html(json.contenido_detalle);*/

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int año = year;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias ac"
                        + " left join Años a on ac.id_año=a.id_año inner join cursos cu on ac.id_curso=cu.id_curso inner join turnos tu"
                        + " on ac.id_turno=tu.id_turno inner join secciones se on ac.id_seccion=se.id_seccion"
                        + " where ac.estado_convocatoria='HABILITADO' AND  a.periodo_año='"+año+"'"
                        + " And  upper(cu.nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%'"
                        + "order by id_convocatoria "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"
                                + "<td>" + rs.getString("estado_convocatoria") + "</td>"
                                + "<td>" + rs.getString("cupo_convocatoria") + "</td>"
                                + "<td>" + rs.getString("periodo_año") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_seccion") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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
    public static String buscarConvocatoria(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
          Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int año = year;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias ac"
                        + " left join Años a on ac.id_año=a.id_año inner join cursos cu on ac.id_curso=cu.id_curso inner join turnos tu"
                        + " on ac.id_turno=tu.id_turno inner join secciones se on ac.id_seccion=se.id_seccion"
                        + " where ac.estado_convocatoria='HABILITADO' AND  a.periodo_año='"+año+"'"
                        + " and  upper(cu.nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%'"
                        + "order by id_convocatoria ASC "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"
                                + "<td>" + rs.getString("periodo_año") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td  colspan=5>No existen registros ...</td></tr>";
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
///////////////////////////Descontar cupo///////////////////////////////////////////////////


    
///////////////////////////////////////////////////////////////////////////////////////////

    public static boolean agregar(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int Ac = convocatoria.getAño().getId_año();
            int Cu = convocatoria.getCurso().getId_curso();
            int Tu = convocatoria.getTurno().getId_turno();
            int Se = convocatoria.getSeccion().getId_seccion();
            String Et =convocatoria.getEstado_convocatoria();
             
            int ma = convocatoria.getMatricula();
            int cos =convocatoria.getCosto_total();
            int cup=  convocatoria.getCupo_convocatoria();
            Date fc= convocatoria.getFecha_convocatoria();
            
            System.out.println("FECHA:"+fc);
            String sql = "insert into convocatorias (id_año,id_curso,id_turno,id_seccion,estado_convocatoria,matricula,costo_total,cupo_convocatoria,fecha_convocatoria) "
                    + "values('" + Ac + "','" + Cu + "','" + Tu + "','" + Se + "','" + Et + "','" + ma + "','" + cos + "','" + cup + "','" + fc + "')";
            System.out.println("--> " + sql+convocatoria.getId_convocatoria());
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_convocatoria = keyset.getInt(1);
                    convocatoria.setId_convocatoria(id_convocatoria);
                    Conexion.getConn().commit();
                }
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
            Conexion.cerrar();
        }

        return valor;
    }

    public static boolean modificar(Convocatorias convocatoria) {
         
        boolean valor = false;

        if (Conexion.conectar()) {
            String sql = "update convocatorias set id_año=?,id_curso=?,id_turno=?,id_seccion=?,estado_convocatoria=?,matricula=?,costo_total=?,cupo_convocatoria=?,fecha_convocatoria=?"
                    + " where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, convocatoria.getAño().getId_año());
                ps.setInt(2, convocatoria.getCurso().getId_curso());
                ps.setInt(3, convocatoria.getTurno().getId_turno());
                ps.setInt(4, convocatoria.getSeccion().getId_seccion());
                ps.setString(5,convocatoria.getEstado_convocatoria());
                ps.setInt(6,convocatoria.getMatricula());
                ps.setInt(7,convocatoria.getCosto_total());
                ps.setInt(8,convocatoria.getCupo_convocatoria());
                ps.setDate(9, (java.sql.Date)convocatoria.getFecha_convocatoria());
                System.out.println(convocatoria.getFecha_convocatoria());
                ps.setInt(10,convocatoria.getId_convocatoria());
                
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> Grabado");
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }

    public static boolean eliminar(Convocatorias convocatoria  ) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from convocatorias where id_convocatoria=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                valor = true;
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
                try {
                    Conexion.getConn().rollback();
                } catch (SQLException ex1) {
                    System.out.println("--> " + ex1.getLocalizedMessage());
                }
            }
        }
        Conexion.cerrar();
        return valor;
    }
}   

