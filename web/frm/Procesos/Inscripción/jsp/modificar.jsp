



<%@page import="modelo.Personas"%>
<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.InscripcionesControlador"%>
<%@page import="modelo.Inscripciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String fecha_inscripcion = request.getParameter("fecha_inscripcion");
    int total_cobrar =Integer.parseInt(request.getParameter("total"));
    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);

    Personas persona = new Personas();
    persona.setId_persona(id_persona);

    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion.setTotal_cobrar(total_cobrar);
    inscripcion.setFecha_inscripcion(fecha_inscripcion);
    inscripcion.setPersona(persona);
    inscripcion.setConvocatoria(convocatoria);

    
    if (InscripcionesControlador.modificarinscripcion(inscripcion)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>