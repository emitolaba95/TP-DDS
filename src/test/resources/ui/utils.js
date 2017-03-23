//vuelve a la pantalla anterior
function goBack() {
    window.history.back();
}

//muestra una 'ventana emergente'
function alertar(texto){
    window.alert(texto);
}

function verificar_pass(){
    var user = document.forms["formulario"]["usuario"].value;  
    var pass = document.forms["formulario"]["password"].value;
    
    var falta_pass = (pass===""|| pass===null);
    var falta_user = (user==="" || user===null) ;
    
    if(falta_pass || falta_user ){
        alertar("Debe completar los datos!");
      
    }
    return (!falta_pass && !falta_user );
}