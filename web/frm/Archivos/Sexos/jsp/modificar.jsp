



<%@page import="controlador.SexosControlador"%>
<%@page import="modelo.Sexos"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));
    String nombre_sexo = request.getParameter("nombre_sexo");

    
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Sexos sexo = new Sexos();
    sexo.setId_sexo(id_sexo);
    sexo.setNombre_sexo(nombre_sexo);
    
    if (SexosControlador.modificarsexo(sexo)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>