

<%@page import="controlador.ConvocatoriasDetControlador"%>
<%@page import="modelo.Convocatorias"%>
<%@page import="modelo.ConvocatoriasDetalles"%>
<%@page import="modelo.Asignaturas"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    int id_convocatoria_detalle = Integer.parseInt(request.getParameter("id_convocatoria_detalle"));
    String estado_materia=request.getParameter("estado_materia");
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));  

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
   ConvocatoriasDetalles convocatoriadetalle = new ConvocatoriasDetalles();
    convocatoriadetalle.setId_convocatoria_detalle(id_convocatoria_detalle);
convocatoriadetalle.setEstado_materia(estado_materia);
    
    Convocatorias convocatoria = new Convocatorias();
    convocatoria.setId_convocatoria(id_convocatoria);
    
   Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    
convocatoriadetalle.setConvocatoria(convocatoria);
convocatoriadetalle.setAsignatura(asignatura);
      
    if (ConvocatoriasDetControlador.modificar(convocatoriadetalle)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>