document.getElementById("enviar").addEventListener("click", validar, false);

const AVISONOMBRE = document.getElementById("avisoNombre");
const AVISOPRECIO = document.getElementById("avisoPrecio");
const AVISOSTOCK = document.getElementById("avisoStock");

function validarNombre(){
    let elemento = document.getElementById("nombre");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento, "<b>!</b> Introduzca tu nombre")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> El nombre debe tener entre 1 y 40 caracteres")
        }
        return false;
    }
    return true;
}
function validarPrecio(){
    let elemento = document.getElementById("precio");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento, "<b>!</b> Introduzca el precio")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> Introduzca caracteres numéricos con hasta dos decimales")
        }
        return false;
    }
    return true;
}
function validarStock(){

    let elemento = document.getElementById("stock");

    if(!elemento.checkValidity()){
        if(elemento.validity.valueMissing){
            error(elemento, "<b>!</b> Introduzca el stock")
        }
        if(elemento.validity.patternMismatch){
            error(elemento, "<b>!</b> Introduzca caracteres numéricos")
        }
        return false;
    }
    return true;
    
}

function validar(e){
    e.preventDefault();
    borrarError();
    

    let nombreValido = validarNombre();
    let precioValido = validarPrecio();
    let stockValido = validarStock();

    if(nombreValido && 
        precioValido && 
        stockValido){
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
        case "precio":
            AVISOPRECIO.innerHTML = mensaje;
            AVISOPRECIO.className = "mensajeError";
            break;
        case "stock":
            AVISOSTOCK.innerHTML = mensaje;
            AVISOSTOCK.className = "mensajeError";
            break;
    }
}

function borrarError(){
    let formulario = document.forms[0];
    let avisos = [AVISONOMBRE,
                AVISOPRECIO,
                AVISOSTOCK];
    
    for(let i = 0; i < formulario.length; i++){
        formulario.elements[i].className = "";
    }
    for(let i = 0; i < avisos.length; i++){
        avisos[i].className = "normal";
        avisos[i].innerHTML = "";
    }
}
