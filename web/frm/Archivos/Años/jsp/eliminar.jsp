

<%@page import="controlador.A�osControlador"%>
<%@page import="modelo.A�os"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_a�o = Integer.parseInt(request.getParameter("id_a�o"));

     
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    A�os a�o = new A�os();
    a�o.setId_a�o(id_a�o);
    
    if (A�osControlador.eliminar(a�o)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>