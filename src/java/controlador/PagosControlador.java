package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import modelo.Asignaturas;
import modelo.Cuentas_alumnos;
import modelo.Inscripciones;
import modelo.Personas;
import utiles.Conexion;
import utiles.utiles;

public class PagosControlador {

    public static Cuentas_alumnos buscarId(Cuentas_alumnos cuenta_alumno) {
        if (Conexion.conectar()) {
            String sql = "select * from cuentas_alumnos ca  "
                    + "left join inscripciones i on i.id_inscripcion=ca.id_inscripcion "
                    + "left join personas p on p.id_persona=i.id_persona "
                    + "where ca.id_cuentaalumno='" + cuenta_alumno.getId_cuentaalumno() + "'";
            System.out.println("sql " + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    cuenta_alumno.setPersona(rs.getString("nombre_persona"));
                    cuenta_alumno.setCi_persona(rs.getInt("ci_persona"));
                    cuenta_alumno.setId_cuentaalumno(rs.getInt("id_cuentaalumno"));
                    cuenta_alumno.setNumero_cuta(rs.getInt("numero_cuota"));
                    cuenta_alumno.setMontocuota(rs.getInt("monto"));
                }
            } catch (SQLException ex) {
                System.out.println("ERROR...." + ex);
            }
        }
        return cuenta_alumno;
    }

    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "select * from cuentas_alumnos ca  "
                        + "left join inscripciones i on i.id_inscripcion=ca.id_inscripcion "
                        + "left join personas p on p.id_persona=i.id_persona "
                        + "where ca.estado_cuenta='PENDIENTE' AND cast (ca.ci_persona as varchar(20)) like '%"
                        + nombre
                        + "%' "
                        + "order by  id_cuentaalumno "
                        + "offset " + offset + " limit " + utiles.REGISTRO_PAGINA;
                System.out.println("Llega");
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_cuentaalumno") + "</td>"
                                + "<td>" + rs.getString("nombre_persona") + "</td>"
                                + "<td>" + rs.getString("ci_persona") + "</td>"
                                + "<td>" + rs.getString("numero_cuota") + "</td>"
                                + "<td>" + rs.getString("fecha_vencimiento") + "</td>"
                                + "<td>" + rs.getString("monto") + "</td>"
                                + "<td>" + rs.getString("estado_cuenta") + "</td>"
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

    public static boolean Cobrar(Cuentas_alumnos cuentaalumno) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql1 = "select  min(numero_cuota) from cuentas_alumnos  where estado_cuenta ='PENDIENTE' and "
                    + " ci_persona='" + cuentaalumno.getInscripcion().getPersona().getCi_persona()+ "'";
            

            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql1);
                if (rs.next()) {
                    String sql = "update cuentas_alumnos set estado_cuenta='COBRADO'"
                            + " where id_cuentaalumno=" + cuentaalumno.getId_cuentaalumno();
                    Conexion.getSt().executeUpdate(sql);
                    valor=true;
                } else {
                    valor=false;
                }
               
            } catch (SQLException ex) {
                System.out.println("Error:" + ex);
            }
        }
        return valor;
    }
}
