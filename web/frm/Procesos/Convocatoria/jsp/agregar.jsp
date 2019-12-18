

<%@page import="utiles.utiles"%>
<%@page import="controlador.ConvocatoriasControlador"%>
<%@page import="modelo.Convocatorias"%>
<%@page import="modelo.Secciones"%>
<%@page import="modelo.Turnos"%>
<%@page import="modelo.Cursos"%>
<%@page import="modelo.Años"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_convocatoria = Integer.parseInt(request.getParameter("id_convocatoria"));
    int id_año = Integer.parseInt(request.getParameter("id_año"));
    int id_curso = Integer.parseInt(request.getParameter("id_curso"));
    int id_turno = Integer.parseInt(request.getParameter("id_turno"));
    int id_seccion = Integer.parseInt(request.getParameter("id_seccion"));
    String estado_convocatoria = request.getParameter("estado_convocatoria");
    int matricula =  Integer.parseInt(request.getParameter("matricula"));
    int costo_total = Integer.parseInt(request.getParameter("costo_total"));
    int cupo_convocatoria = Integer.parseInt(request.getParameter("cupo_convocatoria"));
   String sfecha_convocatoria = request.getParameter("fecha_convocatoria");    
    java.sql.Date fecha_convocatoria = utiles.stringToSqlDate(sfecha_convocatoria); 
System.out.print("agregar"+fecha_convocatoria); 
    String tipo = "error";
    String mensaje = "Datos no agregados.";

    Años año = new Años();
    año.setId_año(id_año);

    Cursos curso = new Cursos();
    curso.setId_curso(id_curso);

    Turnos turno = new Turnos();
    turno.setId_turno(id_turno);

    Secciones seccion = new Secciones();
    seccion.setId_seccion(id_seccion);

    Convocatorias convocatorias = new Convocatorias();
    convocatorias.setId_convocatoria(id_convocatoria);
    convocatorias.setEstado_convocatoria(estado_convocatoria);
    convocatorias.setMatricula(matricula);
    convocatorias.setCosto_total(costo_total);
    convocatorias.setCupo_convocatoria(cupo_convocatoria);
    convocatorias.setFecha_convocatoria(fecha_convocatoria);
    convocatorias.setAño(año);
    convocatorias.setCurso(curso);
    convocatorias.setTurno(turno);
    convocatorias.setSeccion(seccion);
    

    if (ConvocatoriasControlador.agregar(convocatorias)) {
        tipo = "success";
        mensaje = "Datos agregados.";

    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("id_convocatoria", String.valueOf(convocatorias.getId_convocatoria()));
    obj.put("estado_convocatoria", String.valueOf(convocatorias.getEstado_convocatoria()));
    obj.put("matricula", String.valueOf(convocatorias.getMatricula()));
    obj.put("costo_total", String.valueOf(convocatorias.getCosto_total()));
    obj.put("cupo_convocatoria", String.valueOf(convocatorias.getCupo_convocatoria()));
    obj.put("fecha_convocatoria", String.valueOf(convocatorias.getFecha_convocatoria()));
    out.print(obj);
    out.flush();

%>