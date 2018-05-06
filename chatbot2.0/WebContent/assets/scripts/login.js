
$("#entrar").on('click', function(){	
	var login = $("#login").val();
	var senha = $("#senha").val();
	$.get("controller.do", `command=login&login=${login}&senha=${senha}`)
	.done(function( data ) {
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		if(Data[0])
			window.location.href = "http://localhost:8080/chatbot2.0/funcionario.html";
		
	  });  
});
