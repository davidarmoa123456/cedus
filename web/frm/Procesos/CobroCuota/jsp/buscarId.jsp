 
<%@page import="modelo.Inscripciones"%>
<%@page import="controlador.PagosControlador"%>
<%@page import="modelo.Cuentas_alumnos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cuentaalumno= Integer.parseInt(request.getParameter("id_cuentaalumno"));

   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
   Cuentas_alumnos cuentaalumno =new Cuentas_alumnos();
   cuentaalumno.setId_cuentaalumno(id_cuentaalumno);
    cuentaalumno=PagosControlador.buscarId(cuentaalumno);
    if(cuentaalumno.getId_cuentaalumno()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cuentaalumno", cuentaalumno.getId_cuentaalumno());
    //obj.put("id_inscripcion", cuentaalumno.getInscripcion().getId_inscripcion());
    obj.put("monto", cuentaalumno.getMontocuota());
    obj.put("numero_cuta", cuentaalumno.getNumero_cuta());
    obj.put("nombre_persona", cuentaalumno.getPersona());
    obj.put("ci_persona", cuentaalumno.getCi_persona());
 
    out.print(obj);
    out.flush();
%>