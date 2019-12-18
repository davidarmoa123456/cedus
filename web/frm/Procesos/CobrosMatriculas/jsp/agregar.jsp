
<%@page import="modelo.Convocatorias"%>
<%@page import="modelo.Inscripciones"%>
<%@page import="controlador.CobrosMatriculaControlador"%>
<%@page import="modelo.CobrosMatricula"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_cobro_matricula = Integer.parseInt(request.getParameter("id_cobro_matricula"));
    String fecha_cobro = request.getParameter("fecha_cobro");
    int id_inscripcion = Integer.parseInt(request.getParameter("id_inscripcion"));
    int matricula = Integer.parseInt(request.getParameter("matricula"));
    System.out.print(id_inscripcion);
    

     
    String tipo = "error";
    String mensaje = "MATRICULA YA COBRADA.";
    
    
 
    
    Inscripciones inscripcion=new Inscripciones();
    inscripcion.setId_inscripcion(id_inscripcion);

    

    
    CobrosMatricula cobro_matricula = new CobrosMatricula();
    cobro_matricula.setId_cobro_matricula(id_cobro_matricula);
    cobro_matricula.setFecha_cobro(fecha_cobro);
    cobro_matricula.setMonto_matricula(matricula);
    cobro_matricula.setInscripcion(inscripcion);
 
    
    if (CobrosMatriculaControlador.agregar(cobro_matricula)) {
        tipo = "success";
        mensaje = "Datos agregados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>