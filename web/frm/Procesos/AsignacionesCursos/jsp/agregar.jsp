

<%@page import="modelo.ConvocatoriasDetalles"%>
<%@page import="controlador.AsignacionesCursosControlador"%>
<%@page import="modelo.AsignacionesCursos"%>
<%@page import="modelo.Convocatorias"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
   int id_asignacion_curso = Integer.parseInt(request.getParameter("id_asignacion_curso"));
   int id_convocatoria =Integer.parseInt(request.getParameter("id_convocatoria")); 
   int id_convocatoria_detalle = Integer.parseInt(request.getParameter("id_convocatoria_detalle"));
     
  
    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    

 
 ConvocatoriasDetalles convocatoriadetalle = new ConvocatoriasDetalles();
 convocatoriadetalle.setId_convocatoria_detalle(id_convocatoria_detalle);
 Convocatorias convocatoria = new Convocatorias();
 convocatoria.setId_convocatoria(id_convocatoria);

AsignacionesCursos asignacioncurso= new AsignacionesCursos();
asignacioncurso.setId_asignacion_curso(id_asignacion_curso);

asignacioncurso.setConvocatoriadetalle(convocatoriadetalle);
asignacioncurso.setConvocatoria(convocatoria); 
    
    if (AsignacionesCursosControlador.agregar(asignacioncurso)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_asignacion_curso", String.valueOf(asignacioncurso.getId_asignacion_curso()));
    obj.put("id_convocatoria_detalle", String.valueOf(asignacioncurso.getConvocatoriadetalle().getId_convocatoria_detalle()));
    obj.put("id_convocatoria", String.valueOf(asignacioncurso.getConvocatoria().getId_convocatoria()));
    out.print(obj);
    out.flush();
    
%>