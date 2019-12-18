



<%@page import="controlador.CobrosMatriculaControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_comatricula = request.getParameter("bnombre_comatricula");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = CobrosMatriculaControlador.buscarNombre(nombre_comatricula, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>