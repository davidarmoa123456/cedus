



<%@page import="controlador.PersonasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_persona = request.getParameter("bnombre_persona");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    
    
    String mensaje = "B�squeda exitosa.";
    String contenido = PersonasControlador.buscarAlumno(nombre_persona, pagina );
    
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido", contenido);
    
    out.print(obj);
    out.flush();
%>