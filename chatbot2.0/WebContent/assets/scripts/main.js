var talk = [], interaction = {};
var notIdentified, controll = 0, cont = 1, convCompleted = false, convAttendent, 
	user, atend, countInterectionBot = 0, countInterectionH = 0;
var $input = $("#perg");

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
	$.get("controller.do", `command=HistoryIdentify&init=${init}`)
	.done(function( data ) {
		console.log(data[0]);
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		
		appendResp(Data[0].resp);
		$input.attr('placeholder','Digite seu cpf: 000.000.000-00');
		$input.attr('maxlength','14');
		$input.mask('000.000.000-00', {reverse: true});
	  });  
}
var historyIdentify = (cpf) => {
  	cpf = cpf.replace('.', "");
  	cpf = cpf.replace('.', "");
  	var cpfNumbers = cpf.replace(/-/g, "");
  	console.log(cpfNumbers);
  $.get("controller.do", `command=HistoryIdentify&init=${cpfNumbers}`)
	.done(function( data ) {
		var Data = JSON.parse(data.toString('utf8'));
		console.log(Data);
		notIdentified = Data[1];
		if(!notIdentified){
			// Registrando o atendimento com o bot
			console.log("Registrando o atendimento com o bot-------");
			$.get("controller.do", `command=StartService&idC=${Data[2].id}`)
			.done(function( data ) {
				// console.log(data[0]);
				var Data = JSON.parse(data.toString('utf8'));				
				console.log(Data);		
				atend = Data[0];
				localStorage.setItem("atend", JSON.stringify(Data[0]));
				
			  });  
			
		    
		}
		 localStorage.setItem("data", JSON.stringify(Data));
		    localStorage.setItem("user", JSON.stringify(Data[2]));
		appendResp(Data[0].resp);				
		
	  });	
  	$input.attr('placeholder','Digite aqui...');
	$input.attr('maxlength','255');
	$input.unmask();
	
}

var historyQuestions = (pergunta) => {
	cont = 1;
	$.get("controller.do", `command=NewChatBot&perg=${pergunta}`)
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
		countInterectionBot = countInterectionBot +1;
		$(this).addClass("disable");
		$("#nao:last-child").addClass("disable");
		console.log($("#sim"));
		
	});
	
	$(".nao").on('click', function(){		
		$(this).addClass("disable");
		$(".sim").addClass("disable");
		console.log($(".sim"));
		console.log($("#nao:last-child"));
		let data = JSON.parse(localStorage.getItem("respostas"));
		let maxCont = data.length;
		countInterectionBot = countInterectionBot +1;
		
		if(cont == maxCont){
			templateSend = `<div class="message sent">O atendimento será transferido para um atendente humano.<br/> Aguarde alguns instantes você será atendido.<span></span></div> `;
			$(".content_messages").append(templateSend);
			
			// historyAttendat("Atendimento iniciado");
			convAttendent = true;						
			var user = JSON.parse(localStorage.getItem('user'));
			
			
			console.log(user);
			
		// ----- inserindo cliente na fila de atendimento
			
			$.get("controller.do", `command=InsertRowCliente&id=${user.id}`)
			.done(function(data){
				console.log(data);
				var Data = JSON.parse(data.toString('utf8'));
				console.log(Data);
			// Se o cliente foi inserido na fila com sucesso disparar metodo que
			// busca um atendente disponivel
				if(Data[0]){					
					var selectAttendant = setInterval(function(){
						console.log("na filaa----");
						// busca atendente disponivel
						$.get("controller.do", `command=AlterService&id=${atend.id}`)
						.done(function(data){
							var Data = JSON.parse(data.toString('utf8'));
							console.log(Data);
							// quando encontrar já insere o id do funcionario no
							// atendimento
							// retorno esperado: obj atendimento com todos dados
							if(Data[0]){
								// se o retorno vier ok, parar o metodo
								clearInterval(selectAttendant);
								atend = localStorage.setItem("atend", JSON.stringify(Data[0]));
								OpiningAttendat();
								console.log("passou aqui ---------");
								console.log(JSON.parse(localStorage.getItem("atend")));
							}
						});

					},10000);
				}
			});
		}
			
		else{
			appendResp(data[cont].resp);
			feedBackResp();
		}
			
		// $(".content_messages").append(templateSend);
		cont++;
		
	});		
}


var OpiningAttendat = () => {
	console.log("opining attendat");
	// colocar aqui msg padrão para atendente
	// tirar do hardcode as msg,
	// chamar aqui o metodo verifyNewMessage
	 user = JSON.parse(localStorage.getItem('user'));
	 atend = JSON.parse(localStorage.getItem("atend"));
	// localStorage.setItem("atendimento", JSON.stringify(atend));
	console.log(atend);
	var msg = `Atendimento iniciado`;
	$.get("controller.do", `command=SendMessage&id_de=${user.id}&id_para=${atend.idFuncionario}&msg=${msg}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			verifyNewMessage();
		}
	});		
}

var finalizeService = (atendId) => {
	$.get("controller.do", `command=FinalizeService&id=${atendId}&cBot=${countInterectionBot}&cHuman=${countInterectionH}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			console.log("atendimento finalizdo com sucesso!");
			var user = JSON.parse(localStorage.getItem('user'));
			var atend = JSON.parse(localStorage.getItem('atend'));
			var msg = `Atendimento finalizado pelo Cliente`;
			$.get("controller.do", `command=SendMessage&id_de=${user.id}&id_para=${atend.idFuncionario}&msg=${msg}`)
			.done(function(data){
				console.log(data);
				if(data[0]){
					appendPerg(`Atendimento finalizado pelo Cliente`);
					//verifyNewMessage();
				}
			});		
		}
	});	
}

var historyAttendat = (msg) => {
	console.log(msg);
	// appendPerg(msg);
	console.log("ativou historia com atendente");	
	// var user = JSON.parse(localStorage.getItem('user'));
	// var atend = JSON.parse(localStorage.getItem("atendimento"));
	console.log(atend);
	$.get("controller.do", `command=SendMessage&id_de=${user.id}&id_para=${atend.idFuncionario}&msg=${msg}`)
	.done(function(data){
		console.log(data);
		if(data[0]){
			countInterectionH = countInterectionH + 1;
			// verifyNewMessage();
		}
	});	
}

var verifyNewMessage = () => {
	console.log("verifyMessage");
	setInterval(function(){
		$.get("controller.do", `command=SearchMessage&id_para=2`)
		.done(function( data ) {
			var Data = JSON.parse(data);
			var _thisData = Data;
			if(Data){		
				$.get("controller.do", `command=AlterStateMessage&idMsg=${Data[0].id}`)
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
// console.log(Data.length);

$("#sendMsg").on('click', function(){
	var Data = JSON.parse(localStorage.getItem("data"));
	console.log("clicou?");
	var pergunta = $("#perg").val();
	
	
	console.log(pergunta);
	if( pergunta != ""){		
		appendPerg(pergunta);
		if(controll == 0){
			pergunta = pergunta.replace('.', "");
			pergunta = pergunta.replace('.', "");
		  	var cpfNumbers = pergunta.replace(/-/g, "");
			register.push(cpfNumbers);
			historyIdentify(pergunta);
			console.log("entroii aquiii");
			
		}else{
			if(!notIdentified){
				if(convCompleted){
					var notQuestion = pergunta.includes("não") || pergunta.includes("somente isso");
					if(notQuestion){
						countInterectionBot = 0;
						countInterectionH = 0
						finalizeService(atend.id);
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
					if(counter == 2){
						$input.attr('placeholder','DDD-00000-0000');
						$input.attr('maxlength','13');
						$input.mask('00-00000-0000', {reverse: true});
						
						
						
					}else{
						$input.attr('placeholder','Digite aqui...');
						$input.attr('maxlength','255');
						$input.unmask();
					}
					pergunta = pergunta.replace(/-/g, "");
					console.log(pergunta);
					register.push(pergunta);
					if(counter < Data.length -1)
						appendResp(Data[counter].resp);
					
					counter++;
				}						
				if(counter >= Data.length){
					$.get("controller.do", `command=HistoryRegister&Cpf=${register[0]}&Nome=${register[2]}&Telefone=${register[3]}&Email=${register[4]}`)
					.done(function( data ) {
						var Data = JSON.parse(data.toString('utf8'));
						console.log(Data);
								
						localStorage.setItem("user", JSON.stringify(Data[0]));
						if(Data.length > 2){
							appendResp(Data[1].resp);
							appendResp(Data[2].resp);
						}else{
							appendResp(Data[0].resp);
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



