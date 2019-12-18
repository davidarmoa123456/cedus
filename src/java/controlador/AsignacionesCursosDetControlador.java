/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.AsignacionesCursos;
import modelo.Asignaturas;
import modelo.Convocatorias;
import modelo.AsignacionesCursosDet;
import modelo.Aulas;
import modelo.Dias;
import modelo.Horarios;
import modelo.Personas;
import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author David
 */
public class AsignacionesCursosDetControlador {

    public static AsignacionesCursosDet buscarId(int id) {
        AsignacionesCursosDet asignacioncursodet = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursosDet ad "
                        + "left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                        + "left join personas p on p.id_persona=ad.id_persona"
                        + "  left join dias d on d.id_dia=ad.id_dia"
                        + " left join horarios h on h.id_horario=ad.id_horario"
                        + " left join aulas au on au.id_aula=ad.id_aula "
                        + "where ad.id_asignacion_cursodet= ?";
                System.out.println("slq");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        asignacioncursodet = new AsignacionesCursosDet();
                        asignacioncursodet.setId_asignacion_cursodet(rs.getInt("id_asignacion_cursodet"));
                      
                        AsignacionesCursos asignacioncurso=new  AsignacionesCursos();
                        asignacioncurso.setId_asignacion_curso(rs.getInt("id_asignacion_curso"));
                        
                        Personas persona=new Personas();
                        persona.setId_persona(rs.getInt("id_persona"));
                        persona.setNombre_persona(rs.getString("nombre_persona"));
                        asignacioncursodet.setPersona(persona);
                        
                        Horarios horario= new Horarios();
                        horario.setId_horario(rs.getInt("id_horario"));
                        horario.setInicio_hora(rs.getString("inicio_hora"));
                        horario.setFin_hora(rs.getString("fin_hora"));
                        asignacioncursodet.setHorario(horario);
                        
                        Dias dia=new Dias();
                        dia.setId_dia(rs.getInt("id_dia"));
                        dia.setNombre_dia(rs.getString("nombre_dia"));
                        asignacioncursodet.setDia(dia);
                        
                        Aulas aula= new Aulas();
                        aula.setId_aula(rs.getInt("id_aula"));
                        aula.setNombre_aula(rs.getString("nombre_aula"));
                        asignacioncursodet.setAula(aula);
                        
                       

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return asignacioncursodet;
    }
    

    public static String buscarIdAsignaciones_Cursos(int id) {
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursosDet ad "
                        + "left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                        + "left join personas p on p.id_persona=ad.id_persona"
                        + "  left join dias d on d.id_dia=ad.id_dia"
                        + " left join horarios h on h.id_horario=ad.id_horario"
                        + " left join aulas au on au.id_aula=ad.id_aula "
                        + "where ad.id_asignacion_curso=" + id + " "
                        + "order by id_asignacion_cursodet";
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asignacion_cursodet") + "</td>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("id_horario") + "</td>"
                                + "<td>" + rs.getString("inicio_hora") + "</td>"
                                + "<td>" + rs.getString("fin_hora") + "</td>"
                                + "<td>" + rs.getString("id_dia") + "</td>"
                                + "<td>" + rs.getString("nombre_dia") + "</td>"
                                + "<td>" + rs.getString("id_aula") + "</td>"
                                + "<td>" + rs.getString("nombre_aula") + "</td>"
                                + "<td class='centrado'>"
                                + "<button onclick='editarLinea(" + rs.getInt("id_asignacion_cursodet") + ")'"
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
    public static String buscarAsignatura(int id) {
       
          System.out.println("Asignacion "+id);
          
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursosDet ad "
                        + " left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                        + " left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + " where ad.id_asignacion_curso=" + id + " " 
                        + " order by id_asignacion_cursodet";
                        
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        // System.out.println("total"+total);
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_asignacion_cursodet") + "</td>"
                                + "<td>" + rs.getString("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("estado_materia") + "</td>"
                                + "</tr>";
                        System.out.println(tabla);
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

    public static String buscarNombreAsignatura(int id) {
        //int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursosDet ad "
                        + "left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where ad.id_asignacion_curso=" +id+ "";
                        
                       
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getInt("id_asignacion_cursodet") + "</td>"
                                + "<td>" + rs.getInt("id_asignacion_curso") + "</td>"
                                + "<td>" + rs.getInt("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("estado_materia") + "</td>"
                                + "</tr>";
                        System.out.println(tabla);
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
                String sql = "select * from asignaciones_cursosDet ad "
                        + "left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                       + "left join personas p on p.id_persona=ad.id_persona"
                        + "  left join dias d on d.id_dia=ad.id_dia"
                        + " left join horarios h on h.id_horario=ad.id_horario"
                        + " left join aulas au on au.id_aula=ad.id_aula "
                        + "where upper(p.nombre_persona) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by  id_asignacion_cursodet "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_asignacion_cursodet") + "</td>"
                                + "<td>" + rs.getInt("id_asignacion_curso") + "</td>"
                                + "<td>" + rs.getString("id_persona") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("id_horario") + "</td>"
                                + "<td>" + rs.getString("inicio_hora") + "</td>"
                                + "<td>" + rs.getString("fin_hora") + "</td>"
                                + "<td>" + rs.getString("id_dia") + "</td>"
                                + "<td>" + rs.getString("nombre_dia") + "</td>"
                                + "<td>" + rs.getString("id_aula") + "</td>"
                                + "<td>" + rs.getString("nombre_aula") + "</td>"
                                + "</tr>";
                        System.out.println(tabla);
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
    public static String buscarNombreAsignatura(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from asignaciones_cursosDet ad "
                        + "left join asignaciones_cursos ac on ac.id_asignacion_curso=ad.id_asignacion_curso "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where upper(a.nombre_asignatura) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by  id_asignacion_cursodet "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                //+ "<td>" + rs.getInt("id_asignacion_cursodet") + "</td>"
                                + "<td>" + rs.getInt("id_asignacion_curso") + "</td>"
                                + "<td>" + rs.getInt("id_asignatura") + "</td>"
                                + "<td>" + rs.getString("nombre_asignatura") + "</td>"
                                + "<td>" + rs.getString("estado_materia") + "</td>"
                                + "</tr>";
                        System.out.println(tabla);
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

    public static boolean agregar(AsignacionesCursosDet asignacioncursodet) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into asignaciones_cursosDet "
                    + "(id_asignacion_curso,id_persona,id_horario,id_dia,id_aula) "
                    + "values (?,?,?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asignacioncursodet.getAsignacioncurso().getId_asignacion_curso());
                ps.setInt(2, asignacioncursodet.getPersona().getId_persona());
                ps.setInt(3, asignacioncursodet.getHorario().getId_horario());
                ps.setInt(4, asignacioncursodet.getDia().getId_dia());
                ps.setInt(5, asignacioncursodet.getAula().getId_aula());
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

    public static boolean modificar(AsignacionesCursosDet asignacioncursodet) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update asignaciones_cursosDet set "
                    + "id_asignacion_curso=?,"
                    + "id_persona=?,"
                    + "id_horario=?,"
                    + "id_dia=?,"
                    + "id_aula=?,"
                    + "where  id_asignacion_cursodet=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asignacioncursodet.getAsignacioncurso().getId_asignacion_curso());
                ps.setInt(2, asignacioncursodet.getPersona().getId_persona());
                ps.setInt(3, asignacioncursodet.getHorario().getId_horario());
                ps.setInt(4, asignacioncursodet.getDia().getId_dia());
                ps.setInt(5, asignacioncursodet.getAula().getId_aula());
                ps.setInt(6, asignacioncursodet.getId_asignacion_cursodet());
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

    public static boolean eliminar(AsignacionesCursosDet asignacioncursodet) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from asignaciones_cursosDet where  id_asignacion_cursodet=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, asignacioncursodet.getId_asignacion_cursodet());
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

    public static boolean eliminarAsignaturaConvocatoria(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from asignaciones_cursosDet where id_asignacion_curso=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoria.getId_convocatoria());
                ps.executeUpdate();
                ps.close();
                Conexion.getConn().commit();
                System.out.println("--> " + convocatoria.getId_convocatoria());
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
