
<%@page import="controlador.HorariosControlador"%>
<%@page import="modelo.Horarios"%>
<%@page import="org.json.simple.JSONObject"%>

<%@page import="java.sql.ResultSet"%>
<%
    int id_hora = Integer.parseInt(request.getParameter("id_horario"));
    String inicio_hora = request.getParameter("inicio_hora");
    String fin_hora = request.getParameter("fin_hora");

     
    String tipo = "error";
    String mensaje = "Barrio ya existente.";
    
    Horarios hora = new Horarios();
    hora.setId_horario(id_hora);
    hora.setInicio_hora(inicio_hora);
    hora.setFin_hora(fin_hora);
    
    if (HorariosControlador.agregar(hora)) {
        tipo = "success";
        mensaje = "Barrio agregado.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>