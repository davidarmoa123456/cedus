
<%@page import="modelo.Aulas"%>
<%@page import="modelo.Dias"%>
<%@page import="modelo.Horarios"%>
<%@page import="modelo.Personas"%>
<%@page import="modelo.AsignacionesCursos"%>
<%@page import="controlador.AsignacionesCursosDetControlador"%>
<%@page import="modelo.AsignacionesCursosDet"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asignacion_cursodet = Integer.parseInt(request.getParameter("id_asignacion_cursodet"));

    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";

    AsignacionesCursosDet asignacioncursodet = AsignacionesCursosDetControlador.buscarId(id_asignacion_cursodet);
    if (asignacioncursodet != null) {
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    } else {
        asignacioncursodet = new AsignacionesCursosDet();
       

        AsignacionesCursos asignacioncurso = new AsignacionesCursos();
        asignacioncurso.setId_asignacion_curso(0);
        asignacioncursodet.setAsignacioncurso(asignacioncurso);

        Personas persona = new Personas();
        persona.setId_persona(0);
        persona.setNombre_persona("");
        asignacioncursodet.setPersona(persona);
        
        Horarios horario = new Horarios();
        horario.setId_horario(0);
        horario.setInicio_hora("");
        horario.setFin_hora("");
        asignacioncursodet.setHorario(horario);

        Dias dia = new Dias();
        dia.setId_dia(0);
        dia.setNombre_dia("");
        asignacioncursodet.setDia(dia);

        Aulas aula = new Aulas();
        aula.setId_aula(0);
        aula.setNombre_aula("");
        asignacioncursodet.setAula(aula);
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_asignacion_cursodet", String.valueOf(asignacioncursodet.getId_asignacion_cursodet()));
  //  obj.put("id_asignacion_curso", String.valueOf(asignacioncursodet.getAsignacioncurso().getId_asignacion_curso()));
    obj.put("id_persona", String.valueOf(asignacioncursodet.getPersona().getId_persona()));
    obj.put("nombre_persona", asignacioncursodet.getPersona().getNombre_persona());
    obj.put("id_horario", String.valueOf(asignacioncursodet.getHorario().getId_horario()));
    obj.put("inicio_hora", asignacioncursodet.getHorario().getInicio_hora());
    obj.put("fin_hora", asignacioncursodet.getHorario().getFin_hora());
    obj.put("id_dia", String.valueOf(asignacioncursodet.getDia().getId_dia()));
    obj.put("nombre_dia", asignacioncursodet.getDia().getNombre_dia());
    obj.put("id_aula", String.valueOf(asignacioncursodet.getAula().getId_aula()));
    obj.put("nombre_aula", asignacioncursodet.getAula().getNombre_aula());


    out.print(obj);
    out.flush();
%>