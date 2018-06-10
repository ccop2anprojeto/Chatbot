
$("#entrar").on('click', function(){	
	var login = $("#login").val();
	var senha = $("#senha").val();
	$.get("controller.do", `command=login&login=${login}&senha=${senha}`)
	.done(function( data ) {
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		if(Data[0]){
			if(Data[1].cargo == "atendente"){
				$.get("controller.do", `command=insertRowAtendente&id=${Data[1].id}`)
				.done(function(data){
					console.log(data);
					if(data[0]){
						
					}
				});
			}
			window.location.href = "http://localhost:8080/chatbot2.0/funcionario.html";
			sessionStorage.setItem('user', JSON.stringify(Data[1]));
		}else
			alert("login invalido");
			
	  });  
});
