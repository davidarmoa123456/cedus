

<%@page import="controlador.PersonasControlador"%>
<%@page import="modelo.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    
    if (PersonasControlador.eliminar(persona)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>