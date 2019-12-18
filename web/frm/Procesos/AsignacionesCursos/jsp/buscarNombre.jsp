
<%@page import="controlador.AsignacionesCursosControlador"%>
<%@page import="controlador.ConvocatoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
     String nombre_asignacioncurso = request.getParameter("bnombre_asignacioncurso");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = AsignacionesCursosControlador.buscarNombre(nombre_asignacioncurso, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>