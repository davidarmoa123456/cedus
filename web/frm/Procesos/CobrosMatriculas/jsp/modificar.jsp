



<%@page import="controlador.AsignaturasControlador"%>
<%@page import="modelo.Asignaturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
    String nombre_asignatura = request.getParameter("nombre_asignatura");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    asignatura.setNombre_asignatura(nombre_asignatura);
    
    if (AsignaturasControlador.modificarasignatura(asignatura)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>