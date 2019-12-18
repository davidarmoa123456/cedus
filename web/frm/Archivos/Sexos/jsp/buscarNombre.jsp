



<%@page import="controlador.SexosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_sexo = request.getParameter("bnombre_sexo");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = SexosControlador.buscarNombre(nombre_sexo, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>