
<%@page import="controlador.FormulariosControlador"%>
<%@page import="modelo.Menus"%>
<%@page import="modelo.Formularios"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_formulario = Integer.parseInt(request.getParameter("id_formulario"));
    String nombre_formulario = request.getParameter("nombre_formulario");
    String codigo_formulario = request.getParameter("codigo_formulario");
    int id_menu = Integer.parseInt(request.getParameter("id_menu"));

   
    String tipo = "error";
    String mensaje = "Datos no modificados.";
    
    Formularios formulario = new Formularios();
    formulario.setId_formulario(id_formulario);
    formulario.setNombre_formulario(nombre_formulario);
    formulario.setCodigo_formulario(codigo_formulario);
    Menus menu = new Menus();
    menu.setId_menu(id_menu);
    formulario.setMenu(menu);
    
    if (FormulariosControlador.modificar(formulario)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>