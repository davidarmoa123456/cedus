
<%@page import="controlador.PermisosControlador"%>
<%@page import="modelo.Permisos"%>
<%@page import="modelo.Formularios"%>
<%@page import="modelo.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_permiso = Integer.parseInt(request.getParameter("id_permiso"));
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));

   
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
   
    
    Permisos permiso = new Permisos();
    permiso.setId_permiso(id_permiso);
    permiso.setRol(rol);
    permiso.setFormulario(formulario);
    
    if (PermisosControlador.modificar(permiso)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>