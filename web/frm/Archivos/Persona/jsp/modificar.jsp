



<%@page import="modelo.Tipospersonas"%>
<%@page import="modelo.Sexos"%>
<%@page import="modelo.Nacionalidades"%>
<%@page import="modelo.Barrios"%>
<%@page import="modelo.Estadosciviles"%>
<%@page import="modelo.Ciudades"%>
<%@page import="controlador.PersonasControlador"%>
<%@page import="modelo.Personas"%>
<%@page import="org.json.simple.JSONObject"%>
<%@page import="java.sql.ResultSet"%>
<%
    int id_persona = Integer.parseInt(request.getParameter("id_persona"));
    String nombre_persona = request.getParameter("nombre_persona");
    String apellido_persona = request.getParameter("apellido_persona");
    int ci_persona = Integer.parseInt(request.getParameter("ci_persona"));
    String telefono_persona = request.getParameter("telefono_persona");
    String nacimiento_persona = request.getParameter("nacimiento_persona");
    String direccion_persona = request.getParameter("direccion_persona");
    String ocupacion_persona = request.getParameter("ocupacion_persona");
    int id_ciudad = Integer.parseInt(request.getParameter("id_ciudad"));
    String nombre_ciudad = request.getParameter("nombre_ciudad");
    int id_barrio = Integer.parseInt(request.getParameter("id_barrio"));
    String nombre_barrio = request.getParameter("nombre_barrio");
    int id_estadocivil = Integer.parseInt(request.getParameter("id_estadocivil"));
    String nombre_estadocivil = request.getParameter("nombre_estadocivil");
    int id_nacionalidad = Integer.parseInt(request.getParameter("id_nacionalidad"));
    String nombre_nacionalidad = request.getParameter("nombre_nacionalidad");
    int id_sexo = Integer.parseInt(request.getParameter("id_sexo"));
    String nombre_sexo = request.getParameter("nombre_sexo");
    int id_tipopersona = Integer.parseInt(request.getParameter("id_tipopersona"));
    String nombre_tipopersona = request.getParameter("nombre_tipopersona");

    String tipo = "error";
    String mensaje = "Datos no modificados.";

    Personas persona = new Personas();
    persona.setId_persona(id_persona);
    persona.setNombre_persona(nombre_persona);
    persona.setApellido_persona(apellido_persona);
    persona.setCi_persona(ci_persona);
    persona.setTelefono_persona(telefono_persona);
    persona.setNacimiento_persona(nacimiento_persona);
    persona.setDireccion_persona(direccion_persona);
    persona.setOcupacion_persona(ocupacion_persona);
    Ciudades ciudad = new Ciudades();
    ciudad.setId_ciudad(id_ciudad);
    ciudad.setNombre_ciudad(nombre_ciudad);
    persona.setCiudad(ciudad);
     Barrios barrio = new Barrios();
    barrio.setId_barrio(id_barrio);
    barrio.setNombre_barrio(nombre_barrio);
    persona.setBarrio(barrio);

    Estadosciviles estadocivil = new Estadosciviles();
    estadocivil.setId_estadocivil(id_estadocivil);
    estadocivil.setNombre_estadocivil(nombre_estadocivil);
    persona.setEstadocivil(estadocivil);

    Nacionalidades nacionalidad = new Nacionalidades();
    nacionalidad.setId_nacionalidad(id_nacionalidad);
    nacionalidad.setNombre_nacionalidad(nombre_nacionalidad);
    persona.setNacionalidad(nacionalidad);

    Sexos sexo = new Sexos();
    sexo.setId_sexo(id_sexo);
    sexo.setNombre_sexo(nombre_sexo);
    persona.setSexo(sexo);

    Tipospersonas tipopersona = new Tipospersonas();
    tipopersona.setId_tipopersona(id_tipopersona);
    tipopersona.setNombre_tipopersona(nombre_tipopersona);
    persona.setTipopersona(tipopersona);
    if (PersonasControlador.modificarpersona(persona)) {
        tipo = "success";
        mensaje = "Datos modificados.";
    }

    JSONObject obj = new JSONObject();
    obj.put("tipo", tipo);
    obj.put("mensaje", mensaje);
    out.print(obj);
    out.flush();
%>