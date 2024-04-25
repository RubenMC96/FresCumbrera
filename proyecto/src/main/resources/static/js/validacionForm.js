window.addEventListener('load', iniciar,true);


function iniciar(){
    document.getElementById("enviar").addEventListener("click", validar, true);
    


    function validarNombre(){
        let nombre = document.getElementById("nombre").value;
        if(!nombre.checkValidity()){
            if(nombre.validity.valueMissing){
                error(nombre, "El nombre no puede estár vacío");
            }
            if(nombre.validity.patternMismatch){
                error(nombre, "El nombre debe de contener solo letras y máximo 40 caracteres.")
            }
            return false;
        }
        return true;
    }
    
    function validarApellidos(){
        let apellidos = document.getElementById("apellidos");
    
        if(!apellidos.checkValidity()){
            if(apellidos.validity.valueMissing){
                error(apellidos, "Los apeliidos no pueden estár vacíos");
    
            }
            if(apellidos.validity.patternMismatch){
                error(apellidos, "Los apellidos deben de contener solo letras y máximo 40 caracteres.")
            }
            return false;
        }
        return true;
    }
    
    function validarEmail(){
        let email = document.getElementById("email");
    
        if(!email.checkValidity()){
            if(email.validity.valueMissing){
                error(email, "El email no puede estar vacío.");
            }
            if(email.validity.patternMismatch)
            error(email, "Los apellidos deben de contener solo letras y máximo 40 caracteres.");
            return false;
        }
        return true;
    }
    
    function validarTelefono(){
        let telefono = document.getElementById("telefono");
    
        if(!telefono.checkValidity()){
            if(telefono.validity.valueMissing){
                error(telefono, "El telefono no puede estar vacío.");
            }
            if(telefono.validity.patternMismatch){
                error(telefono,"El telefono debe de contener 9 dígitos");
            }
    
            return false;
        }
        return true;
    }
    
    function validarNombUsu(){
        let nombUsu = document.getElementById("nombreUsuario");
    
        if(!nombUsu.checkValidity()){
            if(nombUsu.validity.valueMissing){
                error(telefono, "El nombre de usuario no puede estar vacío.");
            }
            if(nombUsu.validity.patternMismatch){
                error(nombUsu, 'El nombre de usuario solo puede contener letras numeros y ".","_","-","*","/"');
            }
            return false;
        }
        return true;
    }
    
    function validarPassword(){
        let password = document.getElementById("password");
    
        if(!password.checkValidity()){
            if(password.validity.valueMissing){
                error(password, "La contraseña no puede estar vacía");
    
            }
        
            if(password.validity.patternMismatch){
                error(password,'La contraseña solo puede contener letras numeros y ".","_","-","*","/"');
            }
            return false;
        }
        return true;
    }
    
    function error(e, mensaje){
        document.getElementById("mensajeError").innerHTML = mensaje;
        e.className = "error";
        e.focus();
    }
    
    function borrarError(){
        let formulario = document.forms[0];
        for(let i = 0; i <= formulario.elements.length; i++){
            formulario.elements[i].className = '';
        }
    }
    
    function validar(e){
        borrarError();
        if(validarNombre() && validarApellidos() && validarEmail() && validarTelefono() && validarNombUsu() && validarPassword() && confirm("¿Estás seguro que deseas continuar?")){
            //Aqui va el POST con fetch o ajax
            return true;
        }
        else{
            e.preventDefault();
            return false;
        }
    }
}

