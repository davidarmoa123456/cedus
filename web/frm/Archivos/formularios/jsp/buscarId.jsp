
<%@page import="modelo.Menus"%>
<%@page import="controlador.FormulariosControlador"%>
<%@page import="modelo.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    
    Formularios formulario = FormulariosControlador.buscarId(id_formulario);
    if(formulario!=null){
        tipo = "success";
        mensaje = "Datos encontrados.";
        nuevo = "false";
    }else{
        formulario = new Formularios();
        Menus menu = new Menus();
        formulario.setMenu(menu);
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    
    obj.put("nombre_formulario", formulario.getNombre_formulario());
    obj.put("codigo_formulario", formulario.getCodigo_formulario());
    obj.put("id_menu", formulario.getMenu().getId_menu());
    obj.put("nombre_menu", formulario.getMenu().getNombre_menu());
    
    out.print(obj);
    out.flush();
%>