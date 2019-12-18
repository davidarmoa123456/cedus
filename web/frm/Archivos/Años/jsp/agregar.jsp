
<%@page import="controlador.AñosControlador"%>
<%@page import="modelo.Años"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_año = Integer.parseInt(request.getParameter("id_año"));
    String periodo_año = request.getParameter("periodo_año");

     
    String tipo = "error";
    String mensaje = "Barrio ya existente.";
    
    Años año = new Años();
    año.setId_año(id_año);
    año.setPeriodo_año(periodo_año);
    
    if (AñosControlador.agregar(año)) {
        tipo = "success";
        mensaje = "Barrio agregado.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>