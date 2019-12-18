



<%@page import="controlador.PagosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_cuentaalumno = request.getParameter("bnombre_cuentaalumno");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = PagosControlador.buscarNombre(nombre_cuentaalumno, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>