

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
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

  Convocatorias convocatorias =ConvocatoriasControlador.buscarId(id_convocatoria); 
    if (convocatorias != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        convocatorias = new Convocatorias();
        convocatorias.setId_convocatoria(id_convocatoria);
        Años año = new Años();
        convocatorias.setAño(año);
        
        Cursos curso= new Cursos();
        convocatorias.setCurso(curso);
        
        Turnos turno= new Turnos();
         convocatorias.setTurno(turno);
         
         Secciones seccion= new Secciones();
         convocatorias.setSeccion(seccion);
        }
    
    String contenido_detalle =ConvocatoriasDetControlador.buscarIdConvocatoria(id_convocatoria);
    
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_convocatoria",convocatorias.getId_convocatoria());
    obj.put("matricula", String.valueOf(convocatorias.getMatricula()));
    obj.put("costo_total", String.valueOf(convocatorias.getCosto_total()));
    obj.put("id_año", String.valueOf(convocatorias.getAño().getId_año()));
    obj.put("estado_convocatoria", convocatorias.getEstado_convocatoria());
    obj.put("periodo_año", convocatorias.getAño().getPeriodo_año());
    obj.put("id_curso", String.valueOf(convocatorias.getCurso().getId_curso()));
    obj.put("nombre_curso", convocatorias.getCurso().getNombre_curso());
    obj.put("id_turno", String.valueOf(convocatorias.getTurno().getId_turno()));
    obj.put("nombre_turno", convocatorias.getTurno().getNombre_turno());
    obj.put("id_seccion", String.valueOf(convocatorias.getSeccion().getId_seccion()));
    obj.put("nombre_seccion", convocatorias.getSeccion().getNombre_seccion());
   obj.put("contenido_detalle", contenido_detalle);
    
    out.print(obj);
    out.flush();
%>