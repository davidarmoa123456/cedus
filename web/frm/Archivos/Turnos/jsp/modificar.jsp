



<%@page import="controlador.TurnosControlador"%>
<%@page import="modelo.Turnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_turno = Integer.parseInt(request.getParameter("id_turno"));
    String nombre_turno = request.getParameter("nombre_turno");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);
    turno.setNombre_turno(nombre_turno);
    
    if (TurnosControlador.modificarturno(turno)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>