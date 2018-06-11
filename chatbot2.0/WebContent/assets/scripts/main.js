var talk = [], interaction = {};
var notIdentified, controll = 0, cont = 1, convCompleted = false, convAttendent, user, atend;
sessionStorage.clear();

console.log($("#sendMsg"));
localStorage.removeItem("talk");
localStorage.removeItem("user");
localStorage.removeItem("data");

var appendPerg = (perg) => {
	var templateReceived = `<div class="message received"><span>${perg}</span></div>`;
	$(".content_messages").append(templateReceived);

}
var appendResp = (resp) => {		
	var templateSend = `<div class="message sent"><span>${resp}</span></div> `;
	$(".content_messages").append(templateSend);
};

$(".btn-comecar").on('click', function(){
	$(".wisper").removeClass("active");  
  $(".container_chat").addClass("active");
  $(".content_messages").addClass("expand");
  $(".content_area").addClass("expand");
  $(".container_chat").addClass("expand");
  $(".icon_chat").removeClass("active");
  var init = `começar`;
  appendPerg(init);
  startTalk(init);
});

var startTalk = (init) => {
	$.get("controller.do", `command=historyIdentify&init=${init}`)
	.done(function( data ) {
		console.log(data[0]);
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		
		appendResp(Data[0].resp);
	  });  
}
var historyIdentify = (cpf) => {
  	
  $.get("controller.do", `command=historyIdentify&init=${cpf}`)
	.done(function( data ) {
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		notIdentified = Data[1];
		if(!notIdentified){
			//iniciando o atendimento
		    localStorage.setItem("data", JSON.stringify(Data));
		    localStorage.setItem("user", JSON.stringify(Data[2]));
		}
		appendResp(Data[0].resp);				
		
	  });	
}

var historyQuestions = (pergunta) => {
	cont = 1;
	$.get("controller.do", `command=newChatBot&perg=${pergunta}`)
	.done(function( data ) {
		console.log(data);
		var Data = JSON.parse(data);
		console.log(Data);
		interaction["pergunta"] = pergunta;
		interaction["resposta"] = Data[0].resp;
	    talk.push(interaction);
	    localStorage.setItem("talk", JSON.stringify(talk));
	    localStorage.setItem("respostas", JSON.stringify(Data));
	    var teste = JSON.parse(localStorage.getItem("talk"));
		console.log(teste);
		
		appendResp(interaction.resposta);
		feedBackResp();
	});
	
}

var feedBackResp = () => {
	var templateSend = `<div class="message sent">Satisfeito(a) com a resposta?<span></span><div class="content_buttons"><button type="button" id="sim" class="btn btn-success sim">Sim</button><button type="button" id="nao" class="btn btn-warning nao">Não</button></div></div> `;
	
	$(".content_messages").append(templateSend);
	
	$(".sim").on('click', function(){
		templateSend = `<div class="message sent"><span>Fico feliz por ter tirado sua dúvida :)</span></div> <div class="message sent"><span>Posso te ajudar em mais alguma coisa?</span></div>`;
		$(".content_messages").append(templateSend);
		convCompleted = true;
		
	});
	
	$(".nao").on('click', function(){			
		console.log("clicou");
		let data = JSON.parse(localStorage.getItem("respostas"));
		
		if(cont > 2){
			templateSend = `<div class="message sent">O atendimento será transferido para um atendente humano.<br/> Aguarde alguns instantes você será atendido.<span></span></div> `;
			$(".content_messages").append(templateSend);
			
			//historyAttendat("Atendimento iniciado");
			convAttendent = true;						
			var user = JSON.parse(localStorage.getItem('user'));
			
			console.log(user);
			var selectAttendant = setInterval(function(){
				console.log("na filaa----");
				$.get("controller.do", `command=insertRowCliente&id=${user.id}`)
				.done(function(data){
					console.log(data);
					var Data = JSON.parse(data.toString('utf8'));
					console.log(Data);
					if(Data[0]){
						clearInterval(selectAttendant);
						console.log(Data);
						if(Data[0].idFuncionario != '0'){
							console.log("idFuncionario diferente de 0");
							localStorage.setItem("atend", JSON.stringify(Data[0]));
							OpiningAttendat();
						}
						
						console.log("passou aqui ---------");
						console.log(JSON.parse(localStorage.getItem("atend")));
						
						
					}
				});

			},10000);
			
		}
			
		else{
			appendResp(data[cont].resp);
			feedBackResp();
		}
			
		//$(".content_messages").append(templateSend);
		cont++;
	});		
}
var OpiningAttendat = () => {
	console.log("opining attendat");
	//colocar aqui msg padrão para atendente
	//tirar do hardcode as msg,
	//chamar aqui o metodo verifyNewMessage
	 user = JSON.parse(localStorage.getItem('user'));
	 atend = JSON.parse(localStorage.getItem("atend"));
	//localStorage.setItem("atendimento", JSON.stringify(atend));
	console.log(atend);
	var msg = `Atendimento iniciado`;
	$.get("controller.do", `command=sendMessage&id_de=${user.id}&id_para=${atend.idFuncionario}&msg=${msg}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			verifyNewMessage();
		}
	});		
}

var finalizeService = (atendId) => {
	$.get("controller.do", `command=finalizeService&id=${atendId}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			console.log("atendimento finalizdo com sucesso!");
		}
	});	
}

var historyAttendat = (msg) => {
	console.log(msg);
	//appendPerg(msg);
	console.log("ativou historia com atendente");	
	//var user = JSON.parse(localStorage.getItem('user'));
	//var atend = JSON.parse(localStorage.getItem("atendimento"));
	console.log(atend);
	$.get("controller.do", `command=sendMessage&id_de=${user.id}&id_para=${atend.idFuncionario}&msg=${msg}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			//verifyNewMessage();
		}
	});	
}

var verifyNewMessage = () => {
	console.log("verifyMessage");
	setInterval(function(){
		$.get("controller.do", `command=searchMessage&id_para=2`)
		.done(function( data ) {
			var Data = JSON.parse(data);
			var _thisData = Data;
			if(Data){		
				$.get("controller.do", `command=alterStateMessage&idMsg=${Data[0].id}`)
				.done(function( data ) {
					console.log(data);
				});
				
				msgRecebida = true;
				appendResp(Data[0].mensagem);
			}			
		
		});

	},10000);
} 
var counter = 1;
var register = [];
var cpf;
//console.log(Data.length);

$("#sendMsg").on('click', function(){
	var Data = JSON.parse(localStorage.getItem("data"));
	console.log("clicou?");
	var pergunta = $("#perg").val();
	
	console.log(pergunta);
	if( pergunta != ""){		
		appendPerg(pergunta);
		if(controll == 0){
			register.push(pergunta);
			historyIdentify(pergunta);
			console.log("entroii aquiii");
			
		}else{
			if(!notIdentified){
				if(convCompleted){
					var notQuestion = pergunta.includes("não") || pergunta.includes("somente isso");
					if(notQuestion){
						controll = -1;
						appendResp("Foi um prazer conversar com você, até mais! :)");
						var templateFinished = `<div class="message sent"><div class="content_buttons"><button type="button" class="btn btn-success init">Iniciar nova conversa</button></div></div> `;						
						
						
						$(".init").on('click', function(){							
							startTalk(`começar`);
							convCompleted = false;
							notIdentified = true;
							register = [];
						});
						
					}else{
						historyQuestions(pergunta);
					}						
					
				}else if(convAttendent){
					console.log("enviu primeir msg");					
					historyAttendat(pergunta);
				}else{
					historyQuestions(pergunta);
				}
					
												
			}							
			
			else{			
				if(counter < Data.length){
					register.push(pergunta);
					if(counter < Data.length -1)
						appendResp(Data[counter].resp);
					
					counter++;
				}						
				if(counter >= Data.length){
					$.get("controller.do", `command=historyRegister&Cpf=${register[0]}&Nome=${register[2]}&Telefone=${register[3]}&Email=${register[4]}`)
					.done(function( data ) {
						var Data = JSON.parse(data.toString('utf8'));
						console.log(Data);
								
						localStorage.setItem("user", JSON.stringify(Data[0]));
						if(Data.length > 1){
							appendResp(Data[1].resp);
							appendResp(Data[2].resp);
						}else{
							appendResp(Data[1].resp);
						}
							
						
						notIdentified = Data[Data.length-1];
																	
					  });
				}
			}
		}	
				
										
	}
	console.log("register: " + register);
	controll++;
	$("#perg").val("");
});
console.log(register);



