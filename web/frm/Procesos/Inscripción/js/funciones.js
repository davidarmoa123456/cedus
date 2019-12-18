function buscarIdInscripciones() {
    var datosFormulario = $("#formPrograma").serialize();

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
            $("#id_inscripcion").val(json.id_inscripcion);
            $("#fecha_inscripcion").val(json.fecha_inscripcion);
            $("#id_convocatoria").val(json.id_convocatoria);
            $("#matricula").val(json.matricula);
            $("#costo_total").val(json.costo_total);
            $("#nro_cuotas").val(json.nro_cuotas);
            $("#id_año").val(json.id_año);
            $("#periodo_año").val(json.periodo_año);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#id_persona").val(json.id_persona);
            $("#nombre_persona").val(json.nombre_persona);
            $("#apellido_persona").val(json.apellido_persona);
            $("#ci_persona").val(json.ci_persona);

            if (json.nuevo === "true") {
                $("#botonGuardar").prop('disabled', false);
                $("#botonEliminar").prop('disabled', true);
                $("#botonImprimir").prop('disabled', false);
                $("#fecha_inscripcion").val(FechaActual());
                $('#plus').click(function () {
                    if ($('#nro_cuotas').val() === 0 || $('#nro_cuotas').val() <= 10) {
                        $('#nro_cuotas').val(parseInt($('#nro_cuotas').val()) + 1);
                       
                    }
                    // $('#plus').attr("disabled", false);
                });
                $('#minus').click(function () {
                    if ($('#nro_cuotas').val()>0) {
                        $('#nro_cuotas').val(parseInt($('#nro_cuotas').val()) - 1);
                       
                    }
                    // $('#plus').attr("disabled", false);
                });

            } else {
                $("#botonGuardar").prop('disabled', true);
                $("#botonEliminar").prop('disabled', false);
                $("#botonImprimir").prop('disabled', false);
                 $('#plus').attr("disabled", true);
                 $('#minus').attr("disabled", true);
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
function buscarNombreInscripciones() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombre.jsp',
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
                $("#id_inscripcion").val(id);
                $("#fecha_inscripcion").focus();

                buscarIdInscripciones();

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
function buscarIdPersonas() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdPersona.jsp',
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
        url: 'jsp/buscarNombrePersona.jsp',
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
function buscarIdAños() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdAño.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_año").val(json.id_año);
            $("#periodo_año").val(json.periodo_año);
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
function buscarNombreAños() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreAño.jsp',
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
                $("#id_año").val(id);
                $("#periodo_año").focus();

                buscarIdAños();

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
function buscarIdCursos() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdCurso.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
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
function buscarNombreCursos() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreCurso.jsp',
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
                $("#id_curso").val(id);
                $("#nombre_curso").focus();

                buscarIdCursos();

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
function buscarIdTurnos() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdTurno.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
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
function buscarNombreTurnos() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreTurno.jsp',
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
                $("#id_turno").val(id);
                $("#nombre_turno").focus();

                buscarIdTurnos();

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
function buscarIdConvocatoria() {
    var datosFormulario = $("#formPrograma").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarIdConvocatoria.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            $("#id_convocatoria").val(json.id_convocatoria);
            $("#estado_convocatoria").val(json.estado_convocatoria);
            $("#matricula").val(json.matricula);
            $("#costo_total").val(json.costo_total);
            $("#id_año").val(json.id_año);
            $("#periodo_año").val(json.periodo_año);
            $("#id_curso").val(json.id_curso);
            $("#nombre_curso").val(json.nombre_curso);
            $("#id_turno").val(json.id_turno);
            $("#nombre_turno").val(json.nombre_turno);
            $("#id_seccion").val(json.id_seccion);
            $("#nombre_seccion").val(json.nombre_seccion);
            $("#contenidoDetalle").html(json.contenido_detalle);
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
function buscarNombreConvocatoria() {
    var datosFormulario = $("#formBuscar").serialize();

    $.ajax({
        type: 'POST',
        url: 'jsp/buscarNombreConvocatoria.jsp',
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
                $("#id_convocatoria").val(id);
                $("#periodo_año").focus();

                buscarIdConvocatoria();

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
function agregarInscripciones() {
    var datosFormulario = $("#formPrograma").serialize();

    var id_inscripcion = $("#id_inscripcion").val();

    datosFormulario += "&id_inscripcion=" + id_inscripcion;
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
            $("#id_inscripcion").val(json.id_inscripcion);
            limpiarFormulario();

            $("#id_inscripcion").focus();

            $("#id_inscripcion").select();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_inscripcion").focus();
        }
    });
}
function eliminarInscripciones() {
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

            $("#id_inscripcion").focus();

            $("#id_inscripcion").select();
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
    if ($("#fecha_inscripcion").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Nombre no puede estar vacio.");
        $("#fecha_inscripcion").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_inscripcion").val("0");
    $("#fecha_inscripcion").val("");
    $("#id_persona").val("0");
    $("#nombre_persona").val("");
    $("#apellido_persona").val("");
    $("#ci_persona").val("0");
    $("#id_Convocatoria").val("0");
    $("#matricula").val("0");
    $("#cuota_inscripcion").val("0");
    $("#periodo_año").val("");
    $("#nombre_curso").val("");
    $("#nombre_turno").val("");
}
function abrir(url) {
    open(url, "Personas", "width=1300,height=400,top=100,left=25");
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
function FechaActual() {
    var d = new Date();

    var month = d.getMonth() + 1;
    var day = d.getDate();

    var output = d.getFullYear() + '-' +
            (('' + month).length < 2 ? '0' : '') + month + '-' +
            (('' + day).length < 2 ? '0' : '') + day;

    return output;

}
function abrir(url) {
    open(url, "Cobros", "width=1300,height=400,top=100,left=25");
}