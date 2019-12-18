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
import modelo.ConvocatoriasDetalles;
import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author David
 */
public class ConvocatoriasDetControlador {

    public static ConvocatoriasDetalles buscarId(int id) {
        ConvocatoriasDetalles convocatoriadetalle = null;
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where ad.id_convocatoria_detalle= ?";
                System.out.println("slq");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatoriadetalle = new ConvocatoriasDetalles();
                        convocatoriadetalle.setId_convocatoria_detalle(rs.getInt("id_convocatoria_detalle"));
                        convocatoriadetalle.setEstado_materia(rs.getString("estado_materia"));

                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        convocatoriadetalle.setAsignatura(asignatura);

                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                        convocatoria.setEstado_convocatoria(rs.getString("estado_convocatoria"));
                        convocatoriadetalle.setConvocatoria(convocatoria);

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatoriadetalle;
    }
    public static ConvocatoriasDetalles buscarIdAsignatura(int id) {
        System.out.println("Hola Asignacion de clases");
        ConvocatoriasDetalles convocatoriadetalle = null;
        
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where ad.id_convocatoria_detalle= ?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setInt(1, id);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        convocatoriadetalle = new ConvocatoriasDetalles();
                        convocatoriadetalle.setId_convocatoria_detalle(rs.getInt("id_convocatoria_detalle"));
                        convocatoriadetalle.setEstado_materia(rs.getString("estado_materia"));

                        Asignaturas asignatura = new Asignaturas();
                        asignatura.setId_asignatura(rs.getInt("id_asignatura"));
                        asignatura.setNombre_asignatura(rs.getString("nombre_asignatura"));
                        convocatoriadetalle.setAsignatura(asignatura);

                        Convocatorias convocatoria = new Convocatorias();
                        convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                        convocatoria.setEstado_convocatoria(rs.getString("estado_convocatoria"));
                        convocatoriadetalle.setConvocatoria(convocatoria);
                     

                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return convocatoriadetalle;
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
    public static String buscarAsignatura(int id) {
       
          System.out.println("Asignacion "+id);
          
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias_detalles ad "
                        + " left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + " left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + " where ad.id_convocatoria=" + id + " " 
                        + " order by id_convocatoria_detalle";
                        
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
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where ad.id_convocatoria=" +id+ "";
                        
                       
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                               + "<td>" + rs.getInt("id_convocatoria_detalle") + "</td>"
                                + "<td>" + rs.getInt("id_convocatoria") + "</td>"
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
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where upper(a.nombre_asignatura) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by  id_convocatoria_detalle "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getInt("id_convocatoria_detalle") + "</td>"
                                + "<td>" + rs.getInt("id_convocatoria") + "</td>"
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
    public static String buscarNombreAsignatura(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from convocatorias_detalles ad "
                        + "left join convocatorias ac on ac.id_convocatoria=ad.id_convocatoria "
                        + "left join asignaturas a on a.id_asignatura=ad.id_asignatura "
                        + "where upper(a.nombre_asignatura) like '%"
                        + nombre.toUpperCase()
                        + "%' "
                        + "order by  id_convocatoria_detalle "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("--> " + sql);
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                //+ "<td>" + rs.getInt("id_convocatoria_detalle") + "</td>"
                                + "<td>" + rs.getInt("id_convocatoria") + "</td>"
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

    public static boolean agregar(ConvocatoriasDetalles convocatoriadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into convocatorias_detalles "
                    + "(id_convocatoria,id_asignatura,estado_materia) "
                    + "values (?,?,?)";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoriadetalle.getConvocatoria().getId_convocatoria());
                ps.setInt(2, convocatoriadetalle.getAsignatura().getId_asignatura());
                ps.setString(3, convocatoriadetalle.getEstado_materia());
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

    public static boolean modificar(ConvocatoriasDetalles convocatoriadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update convocatorias_detalles set "
                    + "id_convocatoria=?,"
                    + "id_asignatura=?,"
                    + "estado_materia=?"
                    + "where  id_convocatoria_detalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoriadetalle.getConvocatoria().getId_convocatoria());
                ps.setInt(2, convocatoriadetalle.getAsignatura().getId_asignatura());
                ps.setString(3, convocatoriadetalle.getEstado_materia());
                ps.setInt(4, convocatoriadetalle.getId_convocatoria_detalle());
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

    public static boolean eliminar(ConvocatoriasDetalles convocatoriadetalle) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from convocatorias_detalles where  id_convocatoria_detalle=?";
            try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                ps.setInt(1, convocatoriadetalle.getId_convocatoria_detalle());
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
            String sql = "delete from convocatorias_detalles where id_convocatoria=?";
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
