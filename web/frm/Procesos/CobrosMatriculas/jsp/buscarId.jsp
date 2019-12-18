<%@page import="modelo.Usuarios"%>
<%@page import="controlador.CobrosMatriculaControlador"%>
<%@page import="modelo.CobrosMatricula"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_cobro_matricula = Integer.parseInt(request.getParameter("id_cobro_matricula"));
 
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    CobrosMatricula cobro_matricula=new CobrosMatricula();
    cobro_matricula.setId_cobro_matricula(id_cobro_matricula);
    cobro_matricula=CobrosMatriculaControlador.buscarId(cobro_matricula);
    if(cobro_matricula.getId_cobro_matricula()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_cobro_matricula", cobro_matricula.getId_cobro_matricula());
    obj.put("fecha_cobro", cobro_matricula.getFecha_cobro());
    obj.put("id_inscripcion", cobro_matricula.getInscripcion().getId_inscripcion());
    obj.put("matricula", cobro_matricula.getMonto_matricula());
    

    
    out.print(obj);
    out.flush();
%>