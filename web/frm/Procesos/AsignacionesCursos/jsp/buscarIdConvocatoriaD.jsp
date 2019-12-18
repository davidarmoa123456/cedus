

<%@page import="modelo.ConvocatoriasDetalles"%>
<%@page import="utiles.utiles"%>
<%@page import="controlador.ConvocatoriasDetControlador"%>
<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.ConvocatoriasControlador"%>
<%@page import="modelo.Secciones"%>
<%@page import="modelo.Turnos"%>
<%@page import="modelo.Cursos"%>
<%@page import="modelo.Años"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria_detalle = Integer.parseInt(request.getParameter("id_convocatoria_detalle"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    ConvocatoriasDetalles convocatoriasd = ConvocatoriasDetControlador.buscarId(id_convocatoria_detalle);
    if (convocatoriasd != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        convocatoriasd = new ConvocatoriasDetalles();
        convocatoriasd.setId_convocatoria_detalle(id_convocatoria_detalle);

    }

    //String contenido_detalle =ConvocatoriasDetControlador.buscarIdConvocatoria(id_convocatoria);
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_convocatoria_detalle", convocatoriasd.getId_convocatoria_detalle());
    obj.put("id_convocatoria", convocatoriasd.getConvocatoria().getId_convocatoria());
    obj.put("nombre_asignatura", convocatoriasd.getAsignatura().getNombre_asignatura());

    obj.put("nuevo", nuevo);
    out.print(obj);
    out.flush();
%>