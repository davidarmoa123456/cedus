
<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.ConvocatoriasDetControlador"%>
<%@page import="controlador.AsignacionesCursosControlador"%>
<%@page import="controlador.ConvocatoriasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
     String nombre_asignatura = request.getParameter("bnombre_asignatura");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    System.out.print(pagina);
    
 
   
    String mensaje = "Búsqueda exitosa.";
    String contenido = ConvocatoriasDetControlador.buscarNombre(nombre_asignatura,pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>