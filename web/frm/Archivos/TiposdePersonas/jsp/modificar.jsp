



<%@page import="controlador.TipospersonasControlador"%>
<%@page import="modelo.Tipospersonas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));
    String nombre_tipopersona = request.getParameter("nombre_tipopersona");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Tipospersonas tipopersona = new Tipospersonas();
    tipopersona.setId_tipopersona(id_tipopersona);
    tipopersona.setNombre_tipopersona(nombre_tipopersona);
    
    if (TipospersonasControlador.modificartipopersona(tipopersona)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>