



<%@page import="controlador.PersonasControlador"%>
<%@page import="modelo.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    
   
    String tipo = "error";
    String mensaje = "Datos no encontrados.";
    String nuevo = "true";
    Personas persona=new Personas();
    persona.setId_persona(id_persona);
    persona=PersonasControlador.buscarId(persona);
    if(persona.getId_persona()!=0){
        tipo="success";
        mensaje="Datos encontrados";
        nuevo="false";
    }
    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    obj.put("nuevo", nuevo);
    obj.put("id_persona", persona.getId_persona());
    obj.put("nombre_persona", persona.getNombre_persona());
    obj.put("apellido_persona", persona.getApellido_persona());
    obj.put("ci_persona", persona.getCi_persona());
    obj.put("telefono_persona", persona.getTelefono_persona());
    obj.put("nacimiento_persona", persona.getNacimiento_persona());
    obj.put("direccion_persona", persona.getDireccion_persona());
    obj.put("ocupacion_persona", persona.getOcupacion_persona());
    obj.put("id_ciudad", persona.getCiudad().getId_ciudad());
    obj.put("nombre_ciudad", persona.getCiudad().getNombre_ciudad());
    obj.put("id_barrio", persona.getBarrio().getId_barrio());
    obj.put("nombre_barrio", persona.getBarrio().getNombre_barrio());
    obj.put("id_estadocivil", persona.getEstadocivil().getId_estadocivil());
    obj.put("nombre_estadocivil", persona.getEstadocivil().getNombre_estadocivil());
    obj.put("id_nacionalidad", persona.getNacionalidad().getId_nacionalidad());
    obj.put("nombre_nacionalidad", persona.getNacionalidad().getNombre_nacionalidad());
    obj.put("id_sexo", persona.getSexo().getId_sexo());
    obj.put("nombre_sexo", persona.getSexo().getNombre_sexo());
    obj.put("id_tipopersona", persona.getTipopersona().getId_tipopersona());
    obj.put("nombre_tipopersona", persona.getTipopersona().getNombre_tipopersona());
    out.print(obj);
    out.flush();
%>