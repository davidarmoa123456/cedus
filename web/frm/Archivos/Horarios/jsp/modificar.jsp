



<%@page import="controlador.BarriosControlador"%>
<%@page import="modelo.Barrios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_barrio = Integer.parseInt(request.getParameter("id_barrio"));
    String nombre_barrio = request.getParameter("nombre_barrio");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Barrios barrio = new Barrios();
    barrio.setId_barrio(id_barrio);
    barrio.setNombre_barrio(nombre_barrio);
    
    if (BarriosControlador.modificarbarrio(barrio)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>