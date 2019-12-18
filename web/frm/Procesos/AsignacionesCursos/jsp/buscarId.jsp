<%@page import="controlador.AsignacionesCursosDetControlador"%>
<%@page import="modelo.Asignaturas"%>
<%@page import="modelo.ConvocatoriasDetalles"%>
<%@page import="controlador.AsignacionesCursosControlador"%>
<%@page import="modelo.AsignacionesCursos"%>
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
    int id_asignacion_curso = Integer.parseInt(request.getParameter("id_asignacion_curso"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

  AsignacionesCursos asignacioncurso =AsignacionesCursosControlador.buscarId(id_asignacion_curso); 
    if (asignacioncurso != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        asignacioncurso = new  AsignacionesCursos();
        asignacioncurso.setId_asignacion_curso(id_asignacion_curso);
         
        
        Convocatorias convocatorias=new Convocatorias();
        

        Años año = new Años();
        convocatorias.setAño(año);
        
        Cursos curso= new Cursos();
        convocatorias.setCurso(curso);
        
        Turnos turno= new Turnos();
         convocatorias.setTurno(turno);
         
         Secciones seccion= new Secciones();
         convocatorias.setSeccion(seccion);
         
            ConvocatoriasDetalles convocatoriadetalle = new ConvocatoriasDetalles();
        
        Asignaturas asignatura= new Asignaturas();
        convocatoriadetalle.setAsignatura(asignatura);
         
         asignacioncurso.setConvocatoria(convocatorias);
         asignacioncurso.setConvocatoriadetalle(convocatoriadetalle);
        

         
  

        }
    
    String contenido_detalle =AsignacionesCursosDetControlador.buscarIdAsignaciones_Cursos(id_asignacion_curso);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_asignacion_curso",asignacioncurso.getId_asignacion_curso());
    obj.put("id_convocatoria",asignacioncurso.getConvocatoria().getId_convocatoria());
    obj.put("id_año", String.valueOf(asignacioncurso.getConvocatoria().getAño().getId_año()));
    obj.put("periodo_año", asignacioncurso.getConvocatoria().getAño().getPeriodo_año());
    obj.put("id_curso", String.valueOf(asignacioncurso.getConvocatoria().getCurso().getId_curso()));
    obj.put("nombre_curso", asignacioncurso.getConvocatoria().getCurso().getNombre_curso());
    obj.put("id_turno", String.valueOf(asignacioncurso.getConvocatoria().getTurno().getId_turno()));
    obj.put("nombre_turno", asignacioncurso.getConvocatoria().getTurno().getNombre_turno());
    obj.put("id_seccion", String.valueOf(asignacioncurso.getConvocatoria().getSeccion().getId_seccion()));
    obj.put("nombre_seccion", asignacioncurso.getConvocatoria().getSeccion().getNombre_seccion());
    obj.put("id_convocatoria_detalle", asignacioncurso.getConvocatoriadetalle().getId_convocatoria_detalle());
   //obj.put("id_convocatoria", asignacioncurso.getConvocatoriadetalle().getConvocatoria().getId_convocatoria());
    obj.put("nombre_asignatura", asignacioncurso.getConvocatoriadetalle().getAsignatura().getNombre_asignatura());
    obj.put("contenido_detalle", contenido_detalle);
    out.print(obj);
    out.flush();
%>