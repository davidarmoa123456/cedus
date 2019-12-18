
<%@page import="controlador.AsignacionesCursosDetControlador"%>
<%@page import="modelo.AsignacionesCursosDet"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
  int id_asignacion_cursodet = Integer.parseInt(request.getParameter("id_asignacion_cursodet"));

    String tipo = "error";
    String mensaje = "Datos no eliminados.";

  AsignacionesCursosDet  asignacioncursodet = new AsignacionesCursosDet();
  asignacioncursodet.setId_asignacion_cursodet(id_asignacion_cursodet);
    if (AsignacionesCursosDetControlador.eliminar(asignacioncursodet)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>