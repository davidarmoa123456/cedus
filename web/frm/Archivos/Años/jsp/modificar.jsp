



<%@page import="controlador.A�osControlador"%>
<%@page import="modelo.A�os"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_a�o = Integer.parseInt(request.getParameter("id_a�o"));
    String periodo_a�o = request.getParameter("periodo_a�o");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    A�os a�o = new A�os();
    a�o.setId_a�o(id_a�o);
    a�o.setPeriodo_a�o(periodo_a�o);
    
    if (A�osControlador.modificara�o(a�o)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>