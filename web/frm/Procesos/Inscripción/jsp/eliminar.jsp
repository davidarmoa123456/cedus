

<%@page import="modelo.Convocatorias"%>
<%@page import="controlador.InscripcionesControlador"%>
<%@page import="modelo.Inscripciones"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
     Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    Inscripciones inscripcion = new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);
    
    if (InscripcionesControlador.eliminar(inscripcion)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }
    if (InscripcionesControlador.Sumarcupo(convocatoria)) {
        tipo = "success";
        
    }
   /* if (InscripcionesControlador.eliminarCuenta(inscripcion)) {
        tipo = "success";
        mensaje = "Cuenta Eliminada.";
    }*/

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>