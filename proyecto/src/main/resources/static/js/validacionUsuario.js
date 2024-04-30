document.getElementById("enviar").addEventListener("click", validar, false);

const AVISONOMBRE = document.getElementById("avisoNombre");
const AVISOAPELLIDOS = document.getElementById("avisoApellidos");
const AVISOEMAIL = document.getElementById("avisoEmail");
const AVISOTELEFONO = document.getElementById("avisoTelefono");
const AVISONOMBUSUARIO = document.getElementById("avisoNombUsuario");
const AVISOCONTRASENA = document.getElementById("avisoContrasena");
const AVISOCONTRASENA2 = document.getElementById("avisoContrasena2");


function validarNombre(){
    let elemento = document.getElementById("nombre");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento, "<b>!</b> Introduce tu nombre")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> El nombre debe tener entre 1 y 40 caracteres")
        }
        return false;
    }
    return true;
}
function validarApellidos(){
    let elemento = document.getElementById("apellidos");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento, "<b>!</b> Introduce tu apellido")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> El nombre debe tener entre 1 y 80 caracteres")
        }
        return false;
    }
    return true;
}
function validarEmail(){
    let regexEmail = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;

    let elemento = document.getElementById("email");

    if(regexEmail.test(elemento.value)){
        return true;
    }
    else{
        error(elemento, "<b>!</b> La dirección de correo electrónico falta o es inválida. Inténtelo de nuevo.")
        return false;
    }
    
}
function validarTelefono(){
    let regerxTelefono = /^\d{9}$/;
    let elemento = document.getElementById("telefono");

    if(regerxTelefono.test(elemento.value)){
        return true;
    }
    else{
        error(elemento, "El telefono falta o es inválido. Inténtelo de nuevo.")
        return false;
    }
}

function validarNombUsu(){
    let regexNombreUsuario = /^[A-Za-z][A-Za-z0-9_]{3,}$/;
    let elemento = document.getElementById("nombreUsuario");

    if(regexNombreUsuario.test(elemento.value)){
        return true;
    }
    else{
        error(elemento, "Nombre de usuario vacío no válido, debe de comenzar por una letra y un tamaño mínimo de 3 caracteres");
        return false;
    }

}

function validarContrasena(){

    let elemento = document.getElementById("contrasena");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento,"La contraseña no puede estar vacía");
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "La contraseña debe de contener al menos 6 caracteres");
        }
        return false;
    }
    return true;

}
function validarContrasenas(){

    let contrasena1 = document.getElementById("contrasena");
    let contrasena2 = document.getElementById("contrasena2");

    if(contrasena2.lenght == 0){
        error(contrasena2, "Confirme la contraseña");
        return false;
    }
    if(contrasena1.value === contrasena2.value){
        return true;
    }
    else{
        error(contrasena2, "Las contraseñas con coinciden");
        return false;
    }

}

function validar(e){
    borrarError();
    e.preventDefault();

    let nombreValido = validarNombre();
    let apellidosValido = validarApellidos();
    let emailValido = validarEmail();
    let telefonoValido = validarTelefono();
    let nombreUsuarioValido = validarNombUsu();
    let contrasenaValida = validarContrasena();
    let contrasenasValidas = validarContrasenas();

    if(nombreValido && 
        apellidosValido && 
        emailValido && 
        telefonoValido &&
        nombreUsuarioValido &&
        contrasenaValida &&
        contrasenasValidas){
            alert('formulario enviado');
            document.getElementById("myForm").submit();
            return true;
        }
        else{
            return false;
        }
}

function error(elemento, mensaje){
    let idElemento = elemento.id;

    elemento.className = "error";
    elemento.focus();

    switch(idElemento){
        case "nombre":
            AVISONOMBRE.innerHTML = mensaje;
            AVISONOMBRE.className = "mensajeError";
            break;
        case "apellidos":
            AVISOAPELLIDOS.innerHTML = mensaje;
            AVISOAPELLIDOS.className = "mensajeError";
            break;
        case "email":
            AVISOEMAIL.innerHTML = mensaje;
            AVISOEMAIL.className = "mensajeError";
            break;
        case "telefono":
            AVISOTELEFONO.innerHTML = mensaje;
            AVISOTELEFONO.className = "mensajeError";
            break;
        case "nombreUsuario":
            AVISONOMBUSUARIO.innerHTML = mensaje;
            AVISONOMBUSUARIO.className = "mensajeError";
            break;
        case "contrasena":
            AVISOCONTRASENA.innerHTML = mensaje;
            AVISOCONTRASENA.className = "mensajeError";
            break;
        case "contrasena2":
            AVISOCONTRASENA2.innerHTML = mensaje;
            AVISOCONTRASENA2.className = "mensajeError";
            break;
    }
}

function borrarError(){
    let formulario = document.forms[0];
    let avisos = [AVISONOMBRE,
                AVISOAPELLIDOS,
                AVISOEMAIL,
                AVISOTELEFONO, 
                AVISONOMBUSUARIO, 
                AVISOCONTRASENA,
                AVISOCONTRASENA2];
    
    for(let i = 0; i < formulario.length; i++){
        formulario.elements[i].className = "";
    }
    for(let i = 0; i < avisos.length; i++){
        avisos[i].className = "normal";
        avisos[i].innerHTML = "";
    }
}
