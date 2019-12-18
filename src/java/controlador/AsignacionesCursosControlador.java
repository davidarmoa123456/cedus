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
import modelo.AsignacionesCursos;
import modelo.Asignaturas;
import modelo.Convocatorias;
import modelo.ConvocatoriasDetalles;
import modelo.Cursos;
import modelo.Secciones;
import modelo.Turnos;
import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author David
 */
public class AsignacionesCursosControlador {

    public static AsignacionesCursos buscarId(int id) {
        AsignacionesCursos asignaciones_cursos = null;
        System.out.println("hola id ");
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursos ac "
                        + " left join Convocatorias c on c.id_convocatoria=ac.id_convocatoria "
                        + " left join convocatorias_detalles cd on cd.id_convocatoria_detalle=ac.id_convocatoria_detalle"
                        + " left join Años a on c.id_año=a.id_año "
                        + " left join cursos cu on cu.id_curso=c.id_curso "
                        + " left join turnos tu on tu.id_turno=c.id_turno "
                        + " left join secciones se on c.id_seccion=se.id_seccion"
                        + " left join asignaturas asi on asi.id_asignatura=cd.id_asignatura"
                        + " where id_asignacion_curso= ?";
                System.out.println(sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    System.out.println(id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        asignaciones_cursos = new AsignacionesCursos();
                        asignaciones_cursos.setId_asignacion_curso(rs.getInt("id_asignacion_curso"));

                        Convocatorias convocatorias = new Convocatorias();
                        convocatorias.setId_convocatoria(rs.getInt("id_convocatoria"));
                        Años año = new Años();
                        año.setId_año(rs.getInt("id_año"));
                        año.setPeriodo_año(rs.getString("periodo_año"));
                        convocatorias.setAño(año);
                        ConvocatoriasDetalles convocatoriadetalle = new ConvocatoriasDetalles();
                        convocatoriadetalle.setId_convocatoria_detalle(rs.getInt("id_convocatoria_detalle"));
                    

                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        convocatoriadetalle.setAsignatura(asignatura);

                        Cursos curso = new Cursos();
                        curso.setId_curso(rs.getInt("id_curso"));
                        curso.setNombre_curso(rs.getString("nombre_curso"));
                        convocatorias.setCurso(curso);

                        Turnos turno = new Turnos();
                        turno.setId_turno(rs.getInt("id_turno"));
                        turno.setNombre_turno(rs.getString("nombre_turno"));
                        convocatorias.setTurno(turno);

                        Secciones seccion = new Secciones();
                        seccion.setId_seccion(rs.getInt("id_seccion"));
                        seccion.setNombre_seccion(rs.getString("nombre_seccion"));
                        convocatorias.setSeccion(seccion);
                        asignaciones_cursos.setConvocatoria(convocatorias);
                        asignaciones_cursos.setConvocatoriadetalle(convocatoriadetalle);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return asignaciones_cursos;
    }
 public static String buscarIdConvocatoria(int id) {
        String valor = "";
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
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursos ac "
                        + " left join Convocatorias c on c.id_convocatoria=ac.id_convocatoria"
                        + " left join Años a on c.id_año=a.id_año "
                        + "left join cursos cu on c.id_curso=cu.id_curso"
                        + " left join turnos tu on c.id_turno=tu.id_turno"
                        + " left join secciones se on c.id_seccion=se.id_seccion "
                        + " where upper(cu.nombre_curso) like '%"
                        + nombre.toUpperCase()
                        + "%'"
                        + "order by id_asignacion_curso "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asignacion_curso") + "</td>"
                                + "<td>" + rs.getString("id_convocatoria_detalle") + "</td>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"
                                + "<td>" + rs.getString("id_año") + "</td>"
                                + "<td>" + rs.getString("periodo_año") + "</td>"
                                + "<td>" + rs.getString("id_curso") + "</td>"
                                + "<td>" + rs.getString("nombre_curso") + "</td>"
                                + "<td>" + rs.getString("id_turno") + "</td>"
                                + "<td>" + rs.getString("nombre_turno") + "</td>"
                                + "<td>" + rs.getString("id_seccion") + "</td>"
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
                String sql = "select * from asignaciones_cursos ac"
                        + " left join Convocatorias c on c.id_convocatoria=ac.id_convocatoria"
                        + " left join Años a on c.id_año=a.id_año inner join cursos cu on c.id_curso=cu.id_curso inner join turnos tu"
                        + "on c.id_turno=tu.id_turno inner join secciones se on c.id_seccion=se.id_seccion"
                        + " where c.estado_convocatoria='HABILITADO' AND  a.periodo_año='" + año + "'"
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
                                + "<td>" + rs.getString("id_asignacion_curso") + "</td>"
                                + "<td>" + rs.getString("id_convocatoria") + "</td>"
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
///////////////////////////Descontar cupo///////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////
    public static boolean agregar(AsignacionesCursos asignacion_curso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            int Ac = asignacion_curso.getConvocatoria().getId_convocatoria();
            int Ad = asignacion_curso.getConvocatoriadetalle().getId_convocatoria_detalle();

            String sql = "insert into asignaciones_cursos (id_convocatoria,id_convocatoria_detalle) "
                    + "values('" + Ac + "','" + Ad + "')";
            System.out.println("--> " + sql + asignacion_curso.getId_asignacion_curso());
            try {
                Conexion.getSt().executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_asignacion_curso = keyset.getInt(1);
                    asignacion_curso.setId_asignacion_curso(id_asignacion_curso);
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

    public static boolean modificar(AsignacionesCursos asignacion_curso) {

        boolean valor = false;

        if (Conexion.conectar()) {
            String sql = "update asignaciones_cursos set id_convocatoria=?,id_convocatoria_detalle=?"
                    + " where id_asignacion_curso=?";
            System.out.println("sql:"+sql);
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {

                ps.setInt(1, asignacion_curso.getConvocatoria().getId_convocatoria());
                ps.setInt(2, asignacion_curso.getConvocatoriadetalle().getId_convocatoria_detalle());
                ps.setInt(3, asignacion_curso.getId_asignacion_curso());

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

    public static boolean eliminar(AsignacionesCursos asignacion_curso) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from asignaciones_cursos where  id_asignacion_curso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asignacion_curso.getId_asignacion_curso());
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
