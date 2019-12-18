

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
    String mensaje = "Datos no modificados.";
    

 
 ConvocatoriasDetalles convocatoriadetalle = new ConvocatoriasDetalles();
 convocatoriadetalle.setId_convocatoria_detalle(id_convocatoria_detalle);
 Convocatorias convocatoria = new Convocatorias();
 convocatoria.setId_convocatoria(id_convocatoria);

AsignacionesCursos asignacioncurso= new AsignacionesCursos();
asignacioncurso.setId_asignacion_curso(id_asignacion_curso);

asignacioncurso.setConvocatoriadetalle(convocatoriadetalle);
asignacioncurso.setConvocatoria(convocatoria); 
    
    if (AsignacionesCursosControlador.modificar(asignacioncurso)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>