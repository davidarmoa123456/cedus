



<%@page import="controlador.PersonasControlador"%>
<%@page import="modelo.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int ci_persona = Integer.parseInt(request.getParameter("ci_persona"));
    
   
    String tipo = "error";
    String mensaje = "Cedula ya existente.";
 
    Personas persona=new Personas();
    persona.setCi_persona(ci_persona);
    persona=PersonasControlador.noDuplicados(persona);
    if(persona.getCi_persona()!=0){
        tipo="success";
        mensaje="Cedula no existente";
        
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("ci_persona", persona.getCi_persona());
    out.print(obj);
    out.flush();
%>