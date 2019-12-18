



<%@page import="controlador.EstadoscivilesControlador"%>
<%@page import="modelo.Estadosciviles"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Estadosciviles estadocivil = new Estadosciviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);
    
    if (EstadoscivilesControlador.modificarestadocivil(estadocivil)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>