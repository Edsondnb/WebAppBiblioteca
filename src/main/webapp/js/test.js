/**
 * 
 */

$(document).ready(function() {
	
	comunicacion().then(function(){	
		console.log("la aplicacion sigue su curso");
	})
	
});

/*
async function comunicacion(){	
	await $.ajax({
		type: "GET",
		datatype: "html",
		url: "./ServletTest",
		data: $.param({
			usuario: "miguel",
			tecnologia: "java"
		}),
		success: function(data){
			let parsedData = JSON.parse(data);
			console.log(parsedData[1]["autor"]);
		}
	})
}*/