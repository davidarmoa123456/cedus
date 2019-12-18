

<%@page import="controlador.DiasControlador"%>
<%@page import="modelo.Dias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_dia = Integer.parseInt(request.getParameter("id_dia"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Dias dia = new Dias();
    dia.setId_dia(id_dia);
    
    if (DiasControlador.eliminar(dia)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>