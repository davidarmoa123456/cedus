



<%@page import="controlador.DiasControlador"%>
<%@page import="modelo.Dias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_dia = Integer.parseInt(request.getParameter("id_dia"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Dias dia=new Dias();
    dia.setId_dia(id_dia);
    dia=DiasControlador.buscarId(dia);
    if(dia.getId_dia()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_dia", dia.getId_dia());
    obj.put("nombre_dia", dia.getNombre_dia());
    
    out.print(obj);
    out.flush();
%>