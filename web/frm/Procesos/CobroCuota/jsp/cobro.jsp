
<%@page import="modelo.Personas"%>
<%@page import="modelo.Inscripciones"%>
<%@page import="modelo.Cuentas_alumnos"%>
<%@page import="controlador.PagosControlador"%>
<%@page import="modelo.Asignaturas"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_cuentaalumno = Integer.parseInt(request.getParameter("id_cuentaalumno"));
    int ci_persona = Integer.parseInt(request.getParameter("ci_persona"));
   
            System.out.println(ci_persona);

     
    String tipo = "error";
    String mensaje = "Datos no Cobrados.";
 

    
    Cuentas_alumnos cuentaalumno = new Cuentas_alumnos();
    cuentaalumno.setId_cuentaalumno(id_cuentaalumno);
    Inscripciones inscripcion=new Inscripciones();
    Personas persona=new Personas();
    persona.setCi_persona(ci_persona);
    inscripcion.setPersona(persona);
    cuentaalumno.setInscripcion(inscripcion);
   
    if (PagosControlador.Cobrar(cuentaalumno)) {
        tipo = "success";
        mensaje = "Cobrado";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
  
    
    out.print(obj);
    out.flush();
%>