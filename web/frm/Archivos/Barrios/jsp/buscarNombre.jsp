



<%@page import="controlador.BarriosControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_barrio = request.getParameter("bnombre_barrio");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "B�squeda exitosa.";
    String contenido = BarriosControlador.buscarNombre(nombre_barrio, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>