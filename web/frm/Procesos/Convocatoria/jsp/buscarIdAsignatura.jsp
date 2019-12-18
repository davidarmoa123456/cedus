<%@page import="controlador.AsignaturasControlador"%>
<%@page import="modelo.Asignaturas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet" %>
<%
    int id_asignatura =Integer.parseInt(request.getParameter("id_asignatura"));
    String nombre_asignatura =request.getParameter("nombre_asignatura");
     
    Asignaturas asignatura = new Asignaturas();
    asignatura.setId_asignatura(id_asignatura);
    asignatura.setNombre_asignatura(nombre_asignatura);
    
    
    String mensaje= "datos no encontrados";
    asignatura= AsignaturasControlador.buscarId(asignatura);
    System.out.println("llega");
    if (asignatura.getId_asignatura()!=0){
        mensaje ="datos encontrados";
    }
    String tipo="error";
    String nuevo="true";
    if (asignatura!=null){
        tipo= "success";
        mensaje= "datos encontrados.";
        nuevo= "false";
    }
    JSONObject obj=new JSONObject ();
    obj.put("tipo",tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("id_asignatura",asignatura.getId_asignatura());
    obj.put("nombre_asignatura",asignatura.getNombre_asignatura());
    //obj.put("id_asignatura",asignatura.getArticulo(),getId_asignatura());
    //obj.put("nombre_asignatura",asignatura.getArticulo().getNombre_asignatura());
    out.print(obj);
    out.flush();
    %>