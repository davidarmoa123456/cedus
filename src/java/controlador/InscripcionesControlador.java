
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import modelo.Años;
import modelo.Convocatorias;
import modelo.Cuentas_alumnos;
import modelo.Cursos;
import modelo.Inscripciones;
import modelo.Personas;
import modelo.Turnos;

import utiles.Conexion;
import utiles.utiles;

/**
 *
 * @author Administrator
 */
public class InscripcionesControlador {

    public static boolean agregar(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "select * from convocatorias "
                    + " where id_convocatoria='"
                    + inscripcion.getConvocatoria().getId_convocatoria() + "'"
                    + " and cupo_convocatoria>0";
            System.out.println(sql);
            
            

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                     String sql1 = "insert into inscripciones(fecha_inscripcion,id_persona,id_convocatoria,nro_cuotas)"
                        + "values('" + inscripcion.getFecha_inscripcion() + "','" + inscripcion.getPersona().getId_persona() + "','" + inscripcion.getConvocatoria().getId_convocatoria() + "','" + inscripcion.getNro_cuotas() + "')";

                 Conexion.getSt().executeUpdate(sql1, Statement.RETURN_GENERATED_KEYS);
                ResultSet keyset = Conexion.getSt().getGeneratedKeys();
                if (keyset.next()) {
                    int id_inscripcion = keyset.getInt(1);
                    inscripcion.setId_inscripcion(id_inscripcion);
                    Conexion.getConn().commit();
                }
                valor = true;
                }else{
                    valor =false;
                }
           
               
               
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }

        }
        return valor;

    }

    public static boolean agregarCuenta(Inscripciones inscripcion ) {
        boolean valor = false;
        int dia = 5;
        int mes = 2;
        String estado_cuenta = "PENDIENTE";
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int año = year;
        int ct = inscripcion.getConvocatoria().getCosto_total();
        int cu = inscripcion.getNro_cuotas();

        // int id=61;
        if (Conexion.conectar()) {

            // String dcupo="" ;
            try {

                System.out.println(inscripcion.getId_inscripcion());
                //
                for (int i = 1; i <= cu; i++) {
                    String fecha = año + "/" + (mes + i) + "/" + dia;

                    String sql = "insert into cuentas_alumnos "
                            + "(id_inscripcion,fecha_vencimiento,monto,estado_cuenta,numero_cuota,ci_persona) "
                            + "values ('" + inscripcion.getId_inscripcion() + "','" + fecha + "','" + (ct / cu) + "','" + estado_cuenta + "','" + i + "','" + inscripcion.getPersona().getCi_persona() + "')";
                  
              
            Conexion.getSt().executeUpdate(sql);
            valor = true;
                }

            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
//       public static boolean Cobrar(Inscripciones inscripcion) {
//        boolean valor = false;
//        if (Conexion.conectar()) {
//
//            String sql = "insert into cobros(id_inscripcion,fecha_cobro,monto_cobro,descripcion_cobro,estado_cobro)"
//                    + "values('" +inscripcion.getId_inscripcion()+ "','" +inscripcion.getFecha_inscripcion()+ "','" +inscripcion.getTotal_cobrar()+ "','MATRICULA+CUOTA','COBRADO')";
//           
//            try {
//                Conexion.getSt().executeUpdate(sql);
//                valor = true;
//            } catch (SQLException ex) {
//                System.err.println("Error:" + ex);
//            }
//            
//            
//        }
//        return valor;
//        
//
//    }

    public static Inscripciones GenerarCodigo(Inscripciones inscripcion) {
        if (Conexion.conectar()) {
            String sql = "select (max(id_inscripcion))+1  as cod from inscripciones";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    inscripcion.setId_inscripcion(rs.getInt("cod"));
                } 
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return inscripcion;
    }

    public static boolean descontarcupo(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "update convocatorias set cupo_convocatoria= cupo_convocatoria -1 "
                    + "where id_convocatoria= '" + inscripcion.getConvocatoria().getId_convocatoria() + "'";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }

        }
        return valor;

    }

    public static boolean Sumarcupo(Convocatorias convocatoria) {
        boolean valor = false;
        if (Conexion.conectar()) {

            String sql = "update convocatorias set cupo_convocatoria= cupo_convocatoria +1 "
                    + "where id_convocatoria= '" + convocatoria.getId_convocatoria() + "'";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error:" + ex);
            }

        }
        return valor;

    }

    public static Inscripciones buscarId(Inscripciones inscripcion) {
        if (Conexion.conectar()) {
            String sql = "select * from inscripciones i "
                    + "left join convocatorias ac on ac.id_convocatoria=i.id_convocatoria "
                    + "left join años a on a.id_año=ac.id_año"
                    + " left join cursos cu on cu.id_curso=ac.id_curso "
                    + "left join turnos tu on tu.id_turno=ac.id_turno "
                    + "left join personas p on p.id_persona=i.id_persona "
                    + "where i.id_inscripcion= " + inscripcion.getId_inscripcion() + "";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    inscripcion.setId_inscripcion(rs.getInt("id_inscripcion"));
                    inscripcion.setFecha_inscripcion(rs.getString("fecha_inscripcion"));
                    inscripcion.setNro_cuotas(rs.getInt("nro_cuotas"));

                    //  inscripcion.setTotal_cobrar(rs.getInt("total_cobrar"));
                    //inscripcion.setCuota_inscripcion(rs.getInt("cuota_inscripcion"));
                    Personas persona = new Personas();
                    persona.setId_persona(rs.getInt("id_persona"));
                    persona.setNombre_persona(rs.getString("nombre_persona"));
                    persona.setApellido_persona(rs.getString("apellido_persona"));
                    persona.setCi_persona(rs.getInt("ci_persona"));
                    inscripcion.setPersona(persona);

                    Años año = new Años();
                    año.setId_año(rs.getInt("id_año"));
                    año.setPeriodo_año(rs.getString("periodo_año"));

                    Cursos curso = new Cursos();
                    curso.setId_curso(rs.getInt("id_curso"));
                    curso.setNombre_curso(rs.getString("nombre_curso"));

                    Turnos turno = new Turnos();
                    turno.setId_turno(rs.getInt("id_turno"));
                    turno.setNombre_turno(rs.getString("nombre_turno"));

                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(rs.getInt("id_convocatoria"));
                    convocatoria.setEstado_convocatoria(rs.getString("estado_convocatoria"));
                    convocatoria.setMatricula(rs.getInt("matricula"));
                    convocatoria.setCosto_total(rs.getInt("costo_total"));
                    convocatoria.setAño(año);
                    convocatoria.setCurso(curso);
                    convocatoria.setTurno(turno);
                    inscripcion.setConvocatoria(convocatoria);

                } else {
                    //inscripcion.setId_inscripcion(0);
                    inscripcion.setFecha_inscripcion("");
                    inscripcion.setNro_cuotas(0);
                    Personas persona = new Personas();
                    persona.setId_persona(0);
                    persona.setNombre_persona("");
                    persona.setApellido_persona("");
                    persona.setCi_persona(0);
                    inscripcion.setPersona(persona);

                    Años año = new Años();
                    año.setId_año(0);

                    Cursos curso = new Cursos();
                    curso.setId_curso(0);

                    Turnos turno = new Turnos();
                    turno.setId_turno(0);

                    Convocatorias convocatoria = new Convocatorias();
                    convocatoria.setId_convocatoria(0);
                    convocatoria.setEstado_convocatoria("");
                    convocatoria.setMatricula(0);
                    convocatoria.setCosto_total(0);
                    convocatoria.setAño(año);
                    convocatoria.setCurso(curso);
                    convocatoria.setTurno(turno);
                    inscripcion.setConvocatoria(convocatoria);

                    //return null;
                    //return inscripcion;
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return inscripcion;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from inscripciones i "
                        + "left join convocatorias ac on ac.id_convocatoria=i.id_convocatoria "
                        + "left join personas p on p.id_persona=i.id_persona "
                        + "where cast (p.ci_persona as varchar(20)) like '%"
                        + nombre
                        + "%' "
                        + "order by  id_inscripcion "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("fecha_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                // + "<td>" + rs.getString("total_cobrar") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
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

    public static String buscarNombreInscripcion(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = " Select * from  inscripciones i "
                        + " left join personas p on"
                        + " p.id_persona=i.id_persona"
                        + " where not exists (select * from cobros_matriculas cm where i.id_inscripcion = cm.id_inscripcion )";
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_inscripcion") + "</td>"
                                + "<td>" + rs.getString("fecha_inscripcion") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("ci_persona") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
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

    public static boolean modificarinscripcion(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update inscripciones set fecha_inscripcion='" + inscripcion.getFecha_inscripcion() + "',id_persona='" + inscripcion.getPersona().getId_persona() + "',id_convocatoria='" + inscripcion.getConvocatoria().getId_convocatoria() + "',nro_cuotas='" + inscripcion.getNro_cuotas() + "'"
                    + " where id_inscripcion=" + inscripcion.getId_inscripcion();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminar(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from inscripciones where id_inscripcion=" + inscripcion.getId_inscripcion();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("ERROR:" + ex);
            }
        }
        return valor;
    }

    public static boolean eliminarCuenta(Inscripciones inscripcion) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from cuentas_alumnos where id_inscripcion='" + inscripcion.getId_inscripcion();
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
