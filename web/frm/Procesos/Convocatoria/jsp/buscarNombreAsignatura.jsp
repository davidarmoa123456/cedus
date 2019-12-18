<%@page import="controlador.AsignaturasControlador"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import= "java.sql.ResultSet"%>
<%
    String nombre_asignatura = request.getParameter("bnombre_asignatura");
    int pagina = Integer.parseInt(request.getParameter("bpagina"));

    String mensaje = "busqueda exitosa.";
    String contenido = AsignaturasControlador.buscarNombre(nombre_asignatura, pagina);

    JSONObject obj = new JSONObject();
    obj.put("mensaje", mensaje);
    obj.put("contenido", contenido);

    out.print(obj);
    out.flush();
%>