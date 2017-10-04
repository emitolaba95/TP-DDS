//vuelve a la pantalla anterior
function goBack() {
    window.history.back();
}

function cambiarTexto(id,newText){
	document.getElementById(id).innerHTML = newText;
}

//esconde
function hide(id){
	document.getElementById(id).style.display = "none";
}

//muestra
function show(id){
	document.getElementById(id).style.display = "block";
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
    
    //si falta alguna de los dos, esto da false y no se redirige la pagina
    return (!falta_pass && !falta_user );
}

/* Toggle between adding and removing the "responsive" class to topnav when the user clicks on the icon */
function ajustar_navBar() {
    var x = document.getElementById("myTopnav");
    if (x.className === "topnav") {
        x.className += " responsive";
    } else {
        x.className = "topnav";
    }
}