

<%@page import="controlador.EstadoscivilesControlador"%>
<%@page import="modelo.Estadosciviles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Estadosciviles estadocivil = new Estadosciviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    
    if (EstadoscivilesControlador.eliminar(estadocivil)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>