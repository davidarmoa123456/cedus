

<%@page import="controlador.SeccionesControlador"%>
<%@page import="modelo.Secciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Secciones seccion = new Secciones();
    seccion.setId_seccion(id_seccion);
    
    if (SeccionesControlador.eliminar(seccion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>