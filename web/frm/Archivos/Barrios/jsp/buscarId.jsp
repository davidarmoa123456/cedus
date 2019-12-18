



<%@page import="controlador.BarriosControlador"%>
<%@page import="modelo.Barrios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_barrio = Integer.parseInt(request.getParameter("id_barrio"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Barrios barrio=new Barrios();
    barrio.setId_barrio(id_barrio);
    barrio=BarriosControlador.buscarId(barrio);
    if(barrio.getId_barrio()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_barrio", barrio.getId_barrio());
    obj.put("nombre_barrio", barrio.getNombre_barrio());
    
    out.print(obj);
    out.flush();
%>