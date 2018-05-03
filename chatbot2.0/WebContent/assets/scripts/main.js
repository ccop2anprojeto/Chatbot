var talk = [], interaction = {};
console.log($("#sendMsg"));
localStorage.removeItem("talk");


$("#sendMsg").on('click', function(){
	var pergunta = $("#perg").val();
	console.log(pergunta);
	if( pergunta != ""){		
		console.log("pergunta diferente de null");
		$.get("controller.do", `command=newChatBot&perg=${pergunta}`)
		.done(function( data ) {
			console.log(data);
			var Data = JSON.parse(data);
			console.log(Data);
			interaction["pergunta"] = pergunta;
			interaction["resposta"] = Data[0].resp;
		    talk.push(interaction);
		    localStorage.setItem("talk", JSON.stringify(talk));
		    
		    var teste = JSON.parse(localStorage.getItem("talk"));
			console.log(teste);
			console.log(teste[0].pergunta);
			
			appendIteraction();

		  });
	}
	$("#perg").val("");
});

var appendIteraction = () => {
	var templateReceived, templateSend;
	
	
	templateReceived = `<div class="message received"><span>${interaction.pergunta}</span></div>`;
	
	templateSend = `<div class="message sent"><span>${interaction.resposta}</span></div> `;
	
	$(".content_messages").append(templateReceived);
	$(".content_messages").append(templateSend);
	
};