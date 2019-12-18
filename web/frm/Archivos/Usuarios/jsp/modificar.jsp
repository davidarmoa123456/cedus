



<%@page import="controlador.UsuariosControlador"%>
<%@page import="modelo.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String login_usuario = request.getParameter("login_usuario");
    String password_usuario = request.getParameter("password_usuario");
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    usuario.setLogin_usuario(login_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setPassword_usuario(password_usuario);
    
    
    if (UsuariosControlador.modificarusuario(usuario)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>