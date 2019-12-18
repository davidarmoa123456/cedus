
<%@page import="controlador.DiasControlador"%>
<%@page import="modelo.Dias"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_dia = Integer.parseInt(request.getParameter("id_dia"));
    String nombre_dia = request.getParameter("nombre_dia");

     
    String tipo = "error";
    String mensaje = "Día ya existente";
    
    Dias dia = new Dias();
    dia.setId_dia(id_dia);
    dia.setNombre_dia(nombre_dia);
    
    if (DiasControlador.agregar(dia)) {
        tipo = "success";
        mensaje = "Datos agregado.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>