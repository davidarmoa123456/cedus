<%@page import="controlador.InscripcionesControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    String nombre_inscripcion = request.getParameter("bnombre_inscripcion");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));
    String mensaje = "Búsqueda exitosa.";
    String contenido = InscripcionesControlador.buscarNombre(nombre_inscripcion, pagina );
    JSONObject obj = new JSONObject();
    obj.put("mensaje",mensaje);
    obj.put("contenido",contenido);
    out.print(obj);
    out.flush();
%>