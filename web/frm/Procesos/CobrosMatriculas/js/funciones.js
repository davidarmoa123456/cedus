function buscarIdCoMatriculas() {
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
$("#id_cobro_matricula").val(json.id_cobro_matricula);
$("#fecha_cobro").val(json.fecha_cobro);
$("#id_inscripcion").val(json.id_inscripcion);
$("#matricula").val(json.matricula);
$("#fecha_cobro").val(FechaActual());
if (json.nuevo === "true") {
$("#botonAgregar").prop('disabled', false);
$("#botonModificar").prop('disabled', true);
$("#botonEliminar").prop('disabled', true);
} else {
$("#botonAgregar").prop('disabled', true);
$("#botonModificar").prop('disabled', false);
$("#botonEliminar").prop('disabled', false);
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
function buscarNombreCoMatriculas() {
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
$("#id_cobro_matricula").val(id);
$("#fecha_cobro").focus();
buscarIdCoMatriculas();
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
function buscarIdInscripciones() {
var datosFormulario = $("#formPrograma").serialize();
//alert(datosFormulario);
$.ajax({
type: 'POST',
url: 'jsp/buscarIdInscripcion.jsp',
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
url: 'jsp/buscarNombreInscripcion.jsp',
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
function agregarCoMatriculas() {
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
$("#id_cobro_matricula").focus();
$("#id_cobro_matricula").select();
},
error: function (e) {
$("#mensajes").html("No se pudo modificar los datos.");
},
complete: function (objeto, exito, error) {
$("#id_cobro_matricula").focus();
}
});
}
function modificarCoMatriculas() {
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
$("#id_cobro_matricula").focus();
$("#id_cobro_matricula").select();
},
error: function (e) {
$("#mensajes").html("No se pudo modificar los datos.");
},
complete: function (objeto, exito, error) {
}
});
}
function eliminarCoMatriculas() {
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
$("#id_cobro_matricula").focus();
$("#id_cobro_matricula").select();
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
if ($("#fecha_cobro").val().trim() === "") {
valor = false;
$("#mensajes").html("Nombre no puede estar vacio.");
$("#fecha_cobro").focus();
}
return valor;
}
function limpiarFormulario() {
$("#id_cobro_matricula").val("0");
$("#fecha_cobro").val("");
$("#id_inscripcion").val("0");
$("#matricula").val("0");

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
//Funcion para ingresar la fecha actual
function FechaActual() {
    var d = new Date();

    var month = d.getMonth() + 1;
    var day = d.getDate();

    var output = d.getFullYear() + '-' +
            (('' + month).length < 2 ? '0' : '') + month + '-' +
            (('' + day).length < 2 ? '0' : '') + day;

    return output;

}


