

<%@page import="controlador.AñosControlador"%>
<%@page import="modelo.Años"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_año = Integer.parseInt(request.getParameter("id_año"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Años año = new Años();
    año.setId_año(id_año);
    
    if (AñosControlador.eliminar(año)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>