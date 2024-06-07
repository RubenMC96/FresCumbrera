document.getElementById("enviar").addEventListener("click", validar, false);

const AVISONOMBRE = document.getElementById("avisoNombre");
const AVISOEMAIL = document.getElementById("avisoEmail");
const AVISOMENSAJE = document.getElementById("avisoMensaje");


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
function validarMensaje(){
    let regexMensaje = /^[\p{L}\p{M}\p{N}\p{Zs}.-]+$/u;

    let elemento = document.getElementById("mensaje");

    if(regexMensaje.test(elemento.value)){
        return true;
    }
    else{
        error(elemento, "<b>!</b> Falta el comentario o es inválida. Inténtelo de nuevo.")
        return false;
    }
    
}

function validar(e){
    borrarError();
    e.preventDefault();

    let nombreValido = validarNombre();
    let emailValido = validarEmail();
    let mensajeValido = validarMensaje();

    if(nombreValido && 
        emailValido &&
        mensajeValido 
        ){
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
        case "email":
            AVISOEMAIL.innerHTML = mensaje;
            AVISOEMAIL.className = "mensajeError";
            break;
        case "mensaje":
            AVISOMENSAJE.innerHTML = mensaje;
            AVISOMENSAJE.className = "mensajeError";
            break;
        
    }
}

function borrarError(){
    let formulario = document.forms[0];
    let avisos = [AVISONOMBRE,
                AVISOEMAIL,
                AVISOMENSAJE ];
    
    for(let i = 0; i < formulario.length; i++){
        formulario.elements[i].className = "";
    }
    for(let i = 0; i < avisos.length; i++){
        avisos[i].className = "";
        avisos[i].innerHTML = "";
    }
}
