



<%@page import="controlador.AulasControlador"%>
<%@page import="modelo.Aulas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_aula = Integer.parseInt(request.getParameter("id_aula"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Aulas aula=new Aulas();
    aula.setId_aula(id_aula);
    aula=AulasControlador.buscarId(aula);
    if(aula.getId_aula()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_aula", aula.getId_aula());
    obj.put("nombre_aula", aula.getNombre_aula());
    
    out.print(obj);
    out.flush();
%>