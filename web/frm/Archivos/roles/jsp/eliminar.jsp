
<%@page import="controlador.RolesControlador"%>
<%@page import="modelo.Roles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_rol = Integer.parseInt(request.getParameter("id_rol"));

    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Roles rol = new Roles();
    rol.setId_rol(id_rol);
    
    if (RolesControlador.eliminar(rol)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>