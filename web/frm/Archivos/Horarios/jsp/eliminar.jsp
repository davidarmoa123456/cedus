

<%@page import="controlador.BarriosControlador"%>
<%@page import="modelo.Barrios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_barrio = Integer.parseInt(request.getParameter("id_barrio"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Barrios barrio = new Barrios();
    barrio.setId_barrio(id_barrio);
    
    if (BarriosControlador.eliminar(barrio)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>