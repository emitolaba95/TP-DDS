//vuelve a la pantalla anterior
function goBack() {
    window.history.back();
}

//muestra una 'ventana emergente'
function alertar(texto){
    window.alert(texto);
}

function dar_bienvenida(){
    alertar("Bienvenido!");
}

function verificar_pass(password){
    pass = document.getElementById(password).innerHTML;
    
    if(pass.equals("")){
        alertar("Debe ingresar una contraseña!");
    }
        
}