

<%@page import="controlador.NacionalidadesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_nacionalidad = request.getParameter("bnombre_nacionalidad");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "B�squeda exitosa.";
    String contenido =NacionalidadesControlador.buscarNombre(nombre_nacionalidad, pagina);
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>