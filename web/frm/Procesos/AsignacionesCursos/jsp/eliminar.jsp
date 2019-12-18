
<%@page import="modelo.AsignacionesCursos"%>
<%@page import="controlador.AsignacionesCursosControlador"%>
<%@page import="modelo.AsignacionesCursos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asignacion_curso = Integer.parseInt(request.getParameter("id_asignacion_curso"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

    AsignacionesCursos asignacion_curso = new AsignacionesCursos();
    asignacion_curso.setId_asignacion_curso(id_asignacion_curso);

    if (AsignacionesCursosControlador.eliminar(asignacion_curso)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>