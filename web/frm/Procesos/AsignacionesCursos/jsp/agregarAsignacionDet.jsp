

<%@page import="controlador.AsignacionesCursosDetControlador"%>
<%@page import="modelo.Aulas"%>
<%@page import="modelo.Dias"%>
<%@page import="modelo.Horarios"%>
<%@page import="modelo.Personas"%>
<%@page import="modelo.AsignacionesCursos"%>
<%@page import="modelo.AsignacionesCursosDet"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    
    //int id_detallepedido = Integer.parseInt(request.getParameter("id_detallepedido"));
    int id_asignacion_curso = Integer.parseInt(request.getParameter("id_asignacion_curso"));
    int id_persona = Integer.parseInt(request.getParameter("id_persona")); 
    int id_horario = Integer.parseInt(request.getParameter("id_horario")); 
    int id_dia = Integer.parseInt(request.getParameter("id_dia")); 
    int id_aula = Integer.parseInt(request.getParameter("id_aula")); 

    
    String tipo = "error";
    String mensaje = "Datos no agregados.";
    
   AsignacionesCursosDet  asignacioncursodet = new AsignacionesCursosDet();
   

        AsignacionesCursos asignacioncurso = new AsignacionesCursos();
        asignacioncurso.setId_asignacion_curso(id_asignacion_curso);
        asignacioncursodet.setAsignacioncurso(asignacioncurso);

        Personas persona = new Personas();
        persona.setId_persona(id_persona);
        asignacioncursodet.setPersona(persona);
        
        Horarios horario = new Horarios();
        horario.setId_horario(id_horario);
        asignacioncursodet.setHorario(horario);

        Dias dia = new Dias();
        dia.setId_dia(id_dia);
        asignacioncursodet.setDia(dia);

        Aulas aula = new Aulas();
        aula.setId_aula(id_aula);
        asignacioncursodet.setAula(aula);
    
      
    if (AsignacionesCursosDetControlador.agregar(asignacioncursodet)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
    
%>