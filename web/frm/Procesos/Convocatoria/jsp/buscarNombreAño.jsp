



<%@page import="controlador.A�osControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_a�o = request.getParameter("bnombre_a�o");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "B�squeda exitosa.";
    String contenido = A�osControlador.buscarNombre(nombre_a�o, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>