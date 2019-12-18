



<%@page import="controlador.TipospersonasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_tipopersona = request.getParameter("bnombre_tipopersona");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "Búsqueda exitosa.";
    String contenido = TipospersonasControlador.buscarNombre(nombre_tipopersona, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>