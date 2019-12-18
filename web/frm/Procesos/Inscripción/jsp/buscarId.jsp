<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.InscripcionesControlador"%>
<%@page import="modelo.Inscripciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    String eliminar ="false";
    Inscripciones inscripcion=new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion=InscripcionesControlador.buscarId(inscripcion);
    if(inscripcion.getId_inscripcion()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("eliminar", eliminar);
    obj.put("id_inscripcion", inscripcion.getId_inscripcion());
    obj.put("fecha_inscripcion", inscripcion.getFecha_inscripcion());
    obj.put("id_convocatoria", String.valueOf(inscripcion.getConvocatoria().getId_convocatoria()));
    obj.put("matricula", inscripcion.getConvocatoria().getMatricula());
    obj.put("costo_total", inscripcion.getConvocatoria().getCosto_total());
    obj.put("id_año", inscripcion.getConvocatoria().getAño().getId_año());
    obj.put("periodo_año", inscripcion.getConvocatoria().getAño().getPeriodo_año());
    obj.put("id_curso", inscripcion.getConvocatoria().getCurso().getId_curso());
    obj.put("nombre_curso", inscripcion.getConvocatoria().getCurso().getNombre_curso());
    obj.put("id_turno", inscripcion.getConvocatoria().getTurno().getId_turno());
    obj.put("id_turno", inscripcion.getConvocatoria().getTurno().getId_turno());
    obj.put("nombre_turno", inscripcion.getConvocatoria().getTurno().getNombre_turno());
    obj.put("id_persona", inscripcion.getPersona().getId_persona());
    obj.put("nombre_persona", inscripcion.getPersona().getNombre_persona());
    obj.put("apellido_persona", inscripcion.getPersona().getApellido_persona());
    obj.put("ci_persona", inscripcion.getPersona().getCi_persona());
    obj.put("nro_cuotas", inscripcion.getNro_cuotas());
     out.print(obj);
    out.flush();
%>