function buscarIdPersonas() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarId.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#ci_persona").val(json.ci_persona);
            $("#telefono_persona").val(json.telefono_persona);
            $("#nacimiento_persona").val(json.nacimiento_persona);
            $("#direccion_persona").val(json.direccion_persona);
            $("#ocupacion_persona").val(json.ocupacion_persona);

            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);

            $("#id_barrio").val(json.id_barrio);
            $("#nombre_barrio").val(json.nombre_barrio);

            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);

            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);

            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);

            $("#id_tipopersona").val(json.id_tipopersona);
            $("#nombre_tipopersona").val(json.nombre_tipopersona);

            if (json.nuevo === "true") {
                $("#botonAgregar").prop('disabled', false);
                $("#botonModificar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', true);
                // siguienteCampo("#nombre_persona", "#botonAgregar", true);
            } else {
                $("#botonAgregar").prop('disabled', true);
                $("#botonModificar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', false);
                //siguienteCampo("#nombre_persona", "#botonModificar", true);
            }
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombrePersonas() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_persona").val(id);
                $("#nombre_persona").focus();
                buscarIdPersonas();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function agregarPersonas() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/agregar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_persona").focus();
        }
    });
}
function modificarPersonas() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/modificar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {

        }
    });
}
function eliminarPersonas() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/eliminar.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
            $("#id_persona").focus();
            $("#id_persona").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function validarFormulario() {
    var valor = true;
    if ($("#nombre_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio.");
        $("#nombre_persona").focus();
    }
    if ($("#apellido_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Apellido no puede estar vacio.");
        $("#apellido_persona").focus();
    }
    if ($("#ci_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Cedula no puede estar vacio.");
        $("#ci_persona").focus();
    }
    if ($("#telefono_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Telefono no puede estar vacio.");
        $("#telefono_persona").focus();
    }
    if ($("#nacimiento_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Fecha de nacimiento no puede estar vacio.");
        $("#nacimiento_persona").focus();
    }
    return valor;
    if ($("#direccion_persona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("direccion no puede estar vacio.");
        $("#direccion_persona").focus();
    }
    return valor;
    if ($("#id_tipopersona").val().trim() === "") {
        valor = false;
        $("#mensajes").html("tipo de persona no puede estar vacio.");
        $("#id_tipopersona").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_persona").val("0");
    $("#nombre_persona").val("");
    $("#apellido_persona").val("");
    $("#ci_persona").val("");
    $("#telefono_persona").val("");
    $("#nacimiento_persona").val("");
    $("#direccion_persona").val("");
    $("#ocupacion_persona").val("");
    $("#id_ciudad").val("0");
    $("#nombre_ciudad").val("");

    $("#id_barrio").val("0");
    $("#nombre_barrio").val("");

    $("#id_estadocivil").val("0");
    $("#nombre_estadocivil").val("");

    $("#id_nacionalidad").val("0");
    $("#nombre_nacionalidad").val("");

    $("#id_sexo").val("0");
    $("#nombre_sexo").val("");

    $("#id_tipopersona").val("0");
    $("#nombre_tipopersona").val("");
}

function Datosnoduplicados() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/noduplicado.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            if (json.ci_persona === 0) {
                $("#ci_persona").val("");
                 $("#mensajes").html(json.mensaje);
                $("#ci_persona").focus();

            } else {
                $("#ci_persona").val(json.ci_persona);

            }


        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdCiudades() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdciudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_ciudad").val(json.id_ciudad);
            $("#nombre_ciudad").val(json.nombre_ciudad);
        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreCiudades() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreciudad.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_ciudad").val(id);
                $("#nombre_ciudad").focus();
                buscarIdCiudades();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdBarrios() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdbarrios.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_barrio").val(json.id_barrio);
            $("#nombre_barrio").val(json.nombre_barrio);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreBarrios() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrebarrios.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_barrio").val(id);
                $("#nombre_barrio").focus();
                buscarIdBarrios();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdEstadosciviles() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdestadosciviles.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_estadocivil").val(json.id_estadocivil);
            $("#nombre_estadocivil").val(json.nombre_estadocivil);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreEstadosciviles() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreestadosciviles.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_estadocivil").val(id);
                $("#nombre_estadocivil").focus();
                buscarIdEstadosciviles();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdNacionalidad() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdnacionalidades.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_nacionalidad").val(json.id_nacionalidad);
            $("#nombre_nacionalidad").val(json.nombre_nacionalidad);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreNacionalidad() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombrenacionalidades.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");

        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_nacionalidad").val(id);
                $("#nombre_nacionalidad").focus();
                buscarIdNacionalidad();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdSexos() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdsexos.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_sexo").val(json.id_sexo);
            $("#nombre_sexo").val(json.nombre_sexo);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreSexos() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombresexos.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_sexo").val(id);
                $("#nombre_sexo").focus();
                buscarIdSexos();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarIdTipospersonas() {
    var datosFormulario = $("#formPrograma").serialize();
    //alert(datosFormulario);
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdtipopersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_tipopersona").val(json.id_tipopersona);
            $("#nombre_tipopersona").val(json.nombre_tipopersona);

        },
        error: function (e) {
            $("#mensajes").html("No se pudo recuperar los datos.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function buscarNombreTipospersonas() {
    var datosFormulario = $("#formBuscar").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombretipopersona.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
            $("#contenidoBusqueda").css("display", "none");
            ;
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);

            $("#contenidoBusqueda").html(json.contenido);
            $("#contenidoBusqueda").fadeIn("slow");
            $("tbody tr").on("click", function () {
                var id = $(this).find("td:first").html();
                $("#panelBuscar").html("");
                $("#id_tipopersona").val(id);
                $("#nombre_tipopersona").focus();
                buscarIdTipospersonas();
                $("#buscar").fadeOut("slow");
                $("#panelPrograma").fadeIn("slow");
            });
        },
        error: function (e) {
            $("#mensajes").html("No se pudo buscar registros.");
        },
        complete: function (objeto, exito, error) {
            if (exito === "success") {
            }
        }
    });
}
function abrir(url){
    open(url,"Personas","width=1300,height=400,top=100,left=25");
}
function tecla(event) {

    num = event.keyCode;

    if (num == 13) {
        $("#buscar").load("buscar.html");
        $("#buscar").fadeIn("slow");
        $("#panelPrograma").fadeOut("slow");
    }


}
function salir(event) {

    num = event.keyCode;

    if (num == 27) {
        $("#buscar").fadeOut("slow");
        $("#panelPrograma").fadeIn("slow");
        
    }


}

