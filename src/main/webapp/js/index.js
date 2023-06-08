
$(document).ready(function(){
	
	$("#form-login").submit(function(event){
		event.preventDefault();
		auteticarUsuario();
	});
	
	//codigo de pagina register.html
	$("#form-register").submit(function(event){
		event.preventDefault();
		registrarUsuario();
	});
	
	
})

function auteticarUsuario(){
	let username = $("#usuario").val();
	let contrasena = $("#contrasena").val();
	
	$.ajax({
		type: "GET",
		datatype: "html",
		url: "./ServletUsuarioLogin",
		data: $.param({
			username: username,
			contrasena: contrasena
		}),
		success: function(result){
			let parsedResult = JSON.parse(result);
			
			if(parsedResult != false){
				$("#login-error").addClass("d-none");		
				let username = parsedResult['username']; 
				document.location.href = "home.html?username=" + username;

			}else{
				$("#login-error").removeClass("d-none");
				
				setTimeout(function() {
				    $("#login-error").addClass("d-none");
				}, 3500);
			};
		}
	})
}



function registrarUsuario(){
	let username = $("#input-username").val();
	let contrasena = $("#input-contrasena").val();
	let repetirContrasena = $("#input-contrasena-repeat").val();
	let nombre =  $("#input-nombre").val();
	let apellidos =  $("#input-apellidos").val();
	let email =  $("#input-email").val();
	let saldo =  $("#input-saldo").val();
	let premium =  $("#input-premium").prop("checked");
	
	if(contrasena == repetirContrasena){
		//console.log("registrar usuario");
		
		$.ajax({
		type: "GET",
		datatype: "html",
		url: "./ServletUsuarioRegister",
		data: $.param({
				username: username,
				contrasena: contrasena,
				nombre: nombre,
				apellidos: apellidos,
				email: email,
				saldo: saldo,
				premium: premium
		 }),
		 success: function(result){
			let parseResult = JSON.parse(result);
			if(parseResult != false){
				$("#register-error").addClass("d-none");
				document.location.href = "home.html?username=" + username;
			}else{
				$("#register-error").removeClass("d-none");
				$("#register-error").html("Error en el registro del usuario");
				
				setTimeout(function() {
					$("#register-error").addClass("d-none");
				}, 3500);
			}	
		 }
		});
			
	}else{
		$("#register-error").removeClass("d-none");
		$("#register-error").html("Las contrase&ntilde;as no coinciden");	
			
		setTimeout(function() {
			$("#register-error").addClass("d-none");
		}, 3500);
	}
}




