



<%@page import="controlador.HorariosControlador"%>
<%@page import="modelo.Horarios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_horario = Integer.parseInt(request.getParameter("id_horario"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Horarios hora=new Horarios();
    hora.setId_horario(id_horario);
    hora=HorariosControlador.buscarId(hora);
    if(hora.getId_horario()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_horario", hora.getId_horario());
    obj.put("inicio_hora", hora.getInicio_hora());
    obj.put("fin_hora", hora.getFin_hora());
    
    out.print(obj);
    out.flush();
%>