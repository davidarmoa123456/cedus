
<%@page import="controlador.FormulariosControlador"%>
<%@page import="modelo.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));

    
    String tipo = "error";
    String mensaje = "Datos no eliminados.";
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
    
    if (FormulariosControlador.eliminar(formulario)) {
        tipo = "success";
        mensaje = "Datos eliminados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();

%>