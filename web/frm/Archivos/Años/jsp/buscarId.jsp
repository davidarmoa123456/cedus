



<%@page import="controlador.A�osControlador"%>
<%@page import="modelo.A�os"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_a�o = Integer.parseInt(request.getParameter("id_a�o"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    A�os a�o=new A�os();
    a�o.setId_a�o(id_a�o);
    a�o=A�osControlador.buscarId(a�o);
    if(a�o.getId_a�o()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_a�o", a�o.getId_a�o());
    obj.put("periodo_a�o", a�o.getPeriodo_a�o());
    
    out.print(obj);
    out.flush();
%>