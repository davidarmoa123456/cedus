



<%@page import="controlador.AñosControlador"%>
<%@page import="modelo.Años"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_año = Integer.parseInt(request.getParameter("id_año"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Años año=new Años();
    año.setId_año(id_año);
    año=AñosControlador.buscarId(año);
    if(año.getId_año()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_año", año.getId_año());
    obj.put("periodo_año", año.getPeriodo_año());
    
    out.print(obj);
    out.flush();
%>