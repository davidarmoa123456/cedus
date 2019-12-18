package controlador;
import modelo.Roles;
import modelo.Usuarios;
import utiles.Conexion;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import utiles.utiles;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UsuariosControlador {

    public static boolean agregar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "insert into usuarios(nombre_usuario, login_usuario, password_usuario,id_rol)"
                    + "values ('" + usuario.getNombre_usuario()
                    + "','" + usuario.getLogin_usuario()
                    + "','" + utiles.md5(utiles.quitarGuiones(usuario.getPassword_usuario())) + "','" + usuario.getRol().getId_rol()
                    + "')";
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error Agregar:" + ex);
            }
        }
        return valor;
    }
    public static Usuarios buscarId(Usuarios usuario) {
        if (Conexion.conectar()) {
            String sql = "Select * from usuarios u inner join roles r on r.id_rol=u.id_rol  where id_usuario='" + usuario.getId_usuario() + "'";
            //System.out.println("sql =" + sql);
            try {
                ResultSet rs = Conexion.getSt().executeQuery(sql);
                if (rs.next()) {
                    usuario.setId_usuario(rs.getInt("id_usuario"));
                    usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                    usuario.setLogin_usuario(rs.getString("login_usuario"));
                    usuario.setPassword_usuario(rs.getString("password_usuario"));
                    Roles rol=new Roles();
                    rol.setId_rol(rs.getInt("id_rol"));
                    rol.setNombre_rol(rs.getString("nombre_rol"));
                    usuario.setRol(rol);
                } else {
                    usuario.setId_usuario(0);
                    usuario.setNombre_usuario("");
                    usuario.setLogin_usuario("");
                    usuario.setPassword_usuario("");
                    }
            } catch (SQLException ex) {
                System.out.println("Error BuscarID: " + ex);
            }
        }
        return usuario;
    }
    public static String buscarNombre(String nombre, int pagina) {
        int offset = (pagina - 1) * utiles.REGISTRO_PAGINA;
        String valor = "";
        if (Conexion.conectar()) {
            try {
                String sql = "Select * from usuarios where upper(nombre_usuario) like '%"
                        + nombre.toUpperCase() + "%'" + " order by id_usuario offset " + offset + " limit "
                        + utiles.REGISTRO_PAGINA;
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ResultSet rs = ps.executeQuery();
                    String tabla = "";
                    while (rs.next()) {
                        tabla += "<tr>"
                                + "<td>" + rs.getString("id_usuario") + "</td>"
                                + "<td>" + rs.getString("nombre_usuario") + "</td>"
                                + "<td>" + rs.getString("login_usuario") + "</td>"
                                + "<td>" + rs.getString("password_usuario") + "</td>"
                                + "</tr>";
                    }
                    if (tabla.equals("")) {
                        tabla = "<tr><td colspan=2>No existen registros...</td></tr>";
                    }
                    ps.close();
                    valor = tabla;
                } catch (SQLException ex) {
                    System.err.println("Error :" + ex);
                }
                Conexion.cerrar();
            } catch (Exception ex) {
                System.err.println("Error BuscarNombre:" + ex);
            }
        }
        Conexion.cerrar();
        return valor;
    }
    public static boolean modificar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "update usuarios set nombre_usuario= '" + usuario.getNombre_usuario()
                    + "', login_usuario ='" + usuario.getLogin_usuario()
                    +"', password_usuario ='" + utiles.md5(utiles.quitarGuiones(usuario.getPassword_usuario())) + "',id_rol ='" + usuario.getRol().getId_rol() +"'"
                    + "where id_usuario=" + usuario.getId_usuario();
            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.out.println("Error Modif:" + ex);
            }
        }
        return valor;
    }
    public static boolean eliminar(Usuarios usuario) {
        boolean valor = false;
        if (Conexion.conectar()) {
            String sql = "delete from usuarios where id_usuario=" + usuario.getId_usuario();

            try {
                Conexion.getSt().executeUpdate(sql);
                valor = true;
            } catch (SQLException ex) {
                System.err.println("Error BORRAR:" + ex);
            }
        }
        return valor;
    }
    public static Usuarios validarAcceso(Usuarios usuario, HttpServletRequest request) {
        if (Conexion.conectar()) {
            try {
                String sql = "select * from usuarios u "
                        + "left join roles r on u.id_rol=r.id_rol "
                        + "where u.login_usuario=? and u.password_usuario=?";
                try (PreparedStatement ps = Conexion.getConn().prepareStatement(sql)) {
                    ps.setString(1, utiles.quitarGuiones(usuario.getLogin_usuario()));
                    ps.setString(2, utiles.md5(utiles.quitarGuiones(usuario.getPassword_usuario())));
                    ResultSet rs = ps.executeQuery();
                    System.out.println("---> " + sql);
                    if (rs.next()) {
                        HttpSession sesion = request.getSession(true);
                        Roles rol = new Roles();
                        rol.setId_rol(rs.getInt("id_rol"));
                        rol.setNombre_rol(rs.getString("nombre_rol"));
                        usuario = new Usuarios();
                        usuario.setId_usuario(rs.getInt("id_usuario"));
                        usuario.setNombre_usuario(rs.getString("nombre_usuario"));
                        usuario.setLogin_usuario(rs.getString("login_usuario"));
                        usuario.setPassword_usuario(rs.getString("password_usuario"));
                        usuario.setRol(rol);

                        
                        sesion.setAttribute("usuarioLogueado", usuario);
                    } else {
                        usuario = null;
                    }
                    ps.close();
                }
            } catch (SQLException ex) {
                System.out.println("--> " + ex.getLocalizedMessage());
            }
        }
        Conexion.cerrar();
        return usuario;
    }
}
