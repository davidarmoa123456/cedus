



<%@page import="controlador.AulasControlador"%>
<%@page import="modelo.Aulas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_aula = Integer.parseInt(request.getParameter("id_aula"));
    String nombre_aula = request.getParameter("nombre_aula");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Aulas aula = new Aulas();
    aula.setId_aula(id_aula);
    aula.setNombre_aula(nombre_aula);
    
    if (AulasControlador.modificaraula(aula)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>