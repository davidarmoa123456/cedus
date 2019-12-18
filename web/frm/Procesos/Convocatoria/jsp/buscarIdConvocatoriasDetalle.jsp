
<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.ConvocatoriasDetControlador"%>
<%@page import="modelo.ConvocatoriasDetalles"%>
<%@page import="modelo.Asignaturas"%>


<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria_detalle = Integer.parseInt(request.getParameter("id_convocatoria_detalle"));
    String estado_materia=request.getParameter("estado_materia");
    
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

   ConvocatoriasDetalles convocatoriadetalle =ConvocatoriasDetControlador.buscarIdAsignatura(id_convocatoria_detalle);
    if (convocatoriadetalle != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        convocatoriadetalle = new ConvocatoriasDetalles();
        convocatoriadetalle.setId_convocatoria_detalle(0);
        convocatoriadetalle.setEstado_materia(estado_materia);
        
        
       Convocatorias convocatoria = new Convocatorias();
        convocatoria.setId_convocatoria(0);
        convocatoriadetalle.setConvocatoria(convocatoria);
        
        Asignaturas asignatura = new Asignaturas();
        asignatura.setId_asignatura(0);
        asignatura.setNombre_asignatura("");
        convocatoriadetalle.setAsignatura(asignatura);
    }
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);

    obj.put("id_convocatoria_detalle", String.valueOf(convocatoriadetalle.getId_convocatoria_detalle()));
    obj.put("id_convocatoria", String.valueOf(convocatoriadetalle.getConvocatoria().getId_convocatoria()));
    obj.put("id_asignatura", String.valueOf(convocatoriadetalle.getAsignatura().getId_asignatura()));
    obj.put("nombre_asignatura", convocatoriadetalle.getAsignatura().getNombre_asignatura());
    obj.put("estado_materia", String.valueOf(convocatoriadetalle.getEstado_materia()));
    
    out.print(obj);
    out.flush();
%>