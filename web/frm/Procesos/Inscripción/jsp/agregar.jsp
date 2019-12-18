
<%@page import="controlador.ConvocatoriasControlador"%>
<%@page import="modelo.Años"%>
<%@page import="modelo.Cuentas_alumnos"%>
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
      int ci_persona = Integer.parseInt(request.getParameter("ci_persona"));
      int nro_cuotas = Integer.parseInt(request.getParameter("nro_cuotas"));
      int costo_total = Integer.parseInt(request.getParameter("costo_total"));
      
    String tipo = "error";
    String mensaje = "Cupo Lleno.";
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    convocatoria.setCosto_total(costo_total);

     Personas persona = new Personas();
    persona.setId_persona(id_persona);
    persona.setCi_persona(ci_persona);
    
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    inscripcion.setFecha_inscripcion(fecha_inscripcion);
    inscripcion.setNro_cuotas(nro_cuotas);
    inscripcion.setPersona(persona);
    inscripcion.setConvocatoria(convocatoria);
    
    if (InscripcionesControlador.agregar(inscripcion)) {
        tipo = "success";
        mensaje = "Datos agregados.";
         InscripcionesControlador.descontarcupo(inscripcion);
    }
    //ConvocatoriasControlador.descontarcupo(convocatoria);
     if (InscripcionesControlador.agregarCuenta(inscripcion)) {
        tipo = "success";
        mensaje = "Cuenta con CI:"+persona.getCi_persona()+" Generada";
    }
     JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_inscripcion", String.valueOf(inscripcion.getId_inscripcion()));
    out.print(obj);
    out.flush();
%>