document.getElementById("enviar").addEventListener("click", validar, false);

const AVISOCOMENTARIO = document.getElementById("avisoComentario");
const AVISOPUNTUACION = document.getElementById("avisoPuntuacion");


function validarComentario(){
    let elemento = document.getElementById("comentario");
    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
        error(elemento, "<b>!</b> Introduce tu comentario")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> El comentario debe tener entre 1 y 280 caracteres")
        }
        return false;
    }
    return true;
}
function validarPuntuacion(){
    let elemento = document.getElementById("puntuacion");
    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
        error(elemento, "<b>!</b> Introduce tu puntuacion")
        }
        return false;
    }
    return true;
}


function validar(e){
    borrarError();
    e.preventDefault();

    let comentarioValido = validarComentario();
    let puntuacionValida = validarPuntuacion();

    if(comentarioValido && puntuacionValida){
        alert("Formulario enviado");
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
        case "comentario":
            AVISOCOMENTARIO.innerHTML = mensaje;
            AVISOCOMENTARIO.className = "mensajeError";
            break;
        case "puntuacion":
            AVISOPUNTUACION.innerHTML = mensaje;
            AVISOPUNTUACION.className = "mensajeError";
            break;
    }
}

function borrarError(){
    let formulario = document.forms[0];
    let avisos = [AVISOCOMENTARIO,
                AVISOPUNTUACION];
    
    for(let i = 0; i < formulario.length; i++){
        formulario.elements[i].className = "";
    }
    for(let i = 0; i < avisos.length; i++){
        avisos[i].className = "normal";
        avisos[i].innerHTML = "";
    }
}
