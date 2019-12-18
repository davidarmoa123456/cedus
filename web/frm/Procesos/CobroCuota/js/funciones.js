function buscarIdCobro() {
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
            $("#id_cuentaalumno").val(json.id_cuentaalumno);
            $("#id_inscripcion").val(json.id_inscripcion);
            // $("fecha_vencimiento").val(json.fecha_vencimiento);
            $("#montocuota").val(json.monto);
            $("#nombre_persona").val(json.nombre_persona);
            $("#ci_persona").val(json.ci_persona);

            $("#numero_cuta").val(json.numero_cuta);
            //alert(json.numero_cuta);
            //$("estado").val(json.estado);
            if (json.nuevo === "true") {
                $("#botonCobrarCuota").prop('disabled', true);
            } else {
                $("#botonCobrarCuota").prop('disabled', false);
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
function buscarNombreCobro() {
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
                $("#id_cuentaalumno").val(id);
                $("#montocuota").focus();
                buscarIdCobro();
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
function agregarCobro() {
    var datosFormulario = $("#formPrograma").serialize();
    $.ajax({
        type: 'POST',
        url: 'jsp/cobro.jsp',
        data: datosFormulario,
        dataType: 'json',
        beforeSend: function (objeto) {
            $("#mensajes").html("Enviando datos al Servidor ...");
        },
        success: function (json) {
            $("#mensajes").html(json.mensaje);
            limpiarFormulario();
        },
        error: function (e) {
            $("#mensajes").html("No se pudo modificar los datos.");
        },
        complete: function (objeto, exito, error) {
            $("#id_asignatura").focus();
        }
    });
}

function validarFormulario() {
    var valor = true;
    if ($("#montocuota").val().trim() === "") {
        valor = false;
        $("#mensajes").html("Ningun campo puede estar vacio puede estar vacio.");
        $("#nombre_asignatura").focus();
    }
    return valor;
}
function limpiarFormulario() {
    $("#id_cuentaalumno").val("0");
    $("#id_inscripcion").val("0");
    $("#montocuota").val("0");
    $("#nombre_persona").val("");
    $("#ci_persona").val("");
    $("#ci_persona").val("0");
    $("#numero_cuta").val("0");
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



