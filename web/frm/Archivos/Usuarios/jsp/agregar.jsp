
<%@page import="modelo.Roles"%>
<%@page import="controlador.UsuariosControlador"%>

<%@page import="modelo.Usuarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_usuario = Integer.parseInt(request.getParameter("id_usuario"));
    String nombre_usuario = request.getParameter("nombre_usuario");
    String login_usuario = request.getParameter("login_usuario");
    String password_usuario = request.getParameter("password_usuario");
      int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    String nombre_rol = request.getParameter("nombre_rol");

     
    String tipo = "error";
    String mensaje = " Usuario ya existente.";
    
    Usuarios usuario = new Usuarios();
    usuario.setId_usuario(id_usuario);
    usuario.setLogin_usuario(login_usuario);
    usuario.setNombre_usuario(nombre_usuario);
    usuario.setPassword_usuario(password_usuario);
    
    Roles rol= new Roles();
    rol.setId_rol(id_rol);
    rol.setNombre_rol(nombre_rol);
    
    usuario.setRol(rol);
    
    if (UsuariosControlador.agregar(usuario)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>