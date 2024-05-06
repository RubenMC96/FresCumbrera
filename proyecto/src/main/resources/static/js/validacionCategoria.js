document.getElementById("enviar").addEventListener("click", validar, false);

const AVISONOMBRE = document.getElementById("avisoNombre");
const AVISODESCRIPCION = document.getElementById("avisoDescripcion");


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
function validarDescripcion(){
    let elemento = document.getElementById("descripcion");

    if(!elemento.checkValidity()){
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> La descripcion debe tener máximo 280 caracteres")
        }
        return false;
    }
    return true;
}

function validar(e){
    borrarError();
    e.preventDefault();

    let nombreValido = validarNombre();
    let descripcionValido = validarDescripcion();
    
    if(nombreValido && 
        descripcionValido){
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
        case "descripcion":
            AVISODESCRIPCION.innerHTML = mensaje;
            AVISODESCRIPCION.className = "mensajeError";
            break;
    }
}

function borrarError(){
    let formulario = document.forms[0];
    let avisos = [AVISONOMBRE,
        AVISODESCRIPCION];
    
    for(let i = 0; i < formulario.length; i++){
        formulario.elements[i].className = "";
    }
    for(let i = 0; i < avisos.length; i++){
        avisos[i].className = "normal";
        avisos[i].innerHTML = "";
    }
}
