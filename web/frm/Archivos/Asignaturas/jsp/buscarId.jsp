<%@page import="modelo.Usuarios"%>
<%@page import="controlador.AsignaturasControlador"%>
<%@page import="modelo.Asignaturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_asignatura = Integer.parseInt(request.getParameter("id_asignatura"));
 
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Asignaturas asignatura=new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    asignatura=AsignaturasControlador.buscarId(asignatura);
    if(asignatura.getId_asignatura()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_asignatura", asignatura.getId_asignatura());
    obj.put("nombre_asignatura", asignatura.getNombre_asignatura());

    
    out.print(obj);
    out.flush();
%>