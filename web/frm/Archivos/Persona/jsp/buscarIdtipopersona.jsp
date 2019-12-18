



<%@page import="controlador.TipospersonasControlador"%>
<%@page import="modelo.Tipospersonas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Tipospersonas tipopersona=new Tipospersonas();
    tipopersona.setId_tipopersona(id_tipopersona);
    tipopersona=TipospersonasControlador.buscarId(tipopersona);
    if(tipopersona.getId_tipopersona()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_tipopersona", tipopersona.getId_tipopersona());
    obj.put("nombre_tipopersona", tipopersona.getNombre_tipopersona());
    
    out.print(obj);
    out.flush();
%>