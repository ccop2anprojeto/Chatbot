
var funcionario = JSON.parse(sessionStorage.getItem('user'));
console.log(funcionario);
if(!funcionario){
	window.location.href = "http://localhost:8080/chatbot2.0/index.jsp";
}

$("#funcId").text(funcionario.id);
$("#funcName").text(funcionario.nome);

if(funcionario.cargo == "atendente"){
	$(".gerenciamento").css("display", "none");	
}
setInterval(function(){
	$.get("controller.do", `command=searchMessage&id_para=${funcionario.id}`)
	.done(function( data ) {
		var Data = JSON.parse(data);
		var _thisData = Data;
		console.log(Data);
		if(Data){		
			$.get("controller.do", `command=alterStateMessage&idMsg=${Data[0].id}`)
			.done(function( data ) {
				console.log(data);
			});
			appendResp(Data[0].mensagem);
			if(Data[0].mensagem == "Atendimento iniciado"){
				console.log("Atendimento iniciado");
				$.get("controller.do", `command=searchData&idC=${Data[0].id_de}&idA=${funcionario.id}`)
				.done(function( data ) {
					var Data = JSON.parse(data);
					localStorage.setItem("user", JSON.stringify(Data[0]));
					localStorage.setItem("atendimento", JSON.stringify(Data[1]));
					console.log(Data);
					var msgPadrao = `Olá ${Data[0].nome}! Meu nome é ${funcionario.nome}, como posso te ajudar?`;
					sendMessage(msgPadrao);
					appendPerg(msgPadrao);
				});
			}
			
			
		}
		
	
	});

},10000);



var msgSuccess = () => {
	
}
var sendMessage = (msg) => {
	var user = JSON.parse(localStorage.getItem('user'));
	var atend = JSON.parse(localStorage.getItem("atendimento"));
	console.log(msg);
	// appendPerg(msg);
	console.log("ativou historia com atendente");
	$.get("controller.do", `command=sendMessage&id_de=${funcionario.id}&id_para=${user.id}&msg=${msg}`)
	.done(function(data){
		console.log(data);
		
	});
	
}
$("#sendMsg").on('click', function(){
	var pergunta = $("#perg").val();
	
	console.log(pergunta);
	if( pergunta != ""){		
		appendPerg(pergunta);
						
			sendMessage(pergunta);							
	}
	
	$("#perg").val(" ");
});

var appendPerg = (perg) => {
	var templateReceived = `<div class="message received"><span>${perg}</span></div>`;
	$(".content_messages").append(templateReceived);

}

var appendResp = (resp) => {		
	var templateSend = `<div class="message sent"><span>${resp}</span></div> `;
	$(".content_messages").append(templateSend);
};


$(".sair").on('click', function(){
	window.location.href = "http://localhost:8080/chatbot2.0/index.jsp";
	console.log("sair");
	sessionStorage.clear();
	
});

var ctx = document.getElementById('myChart');
//console.log(ctx);
ctx.getContext('2d');

var ctx2 = document.getElementById('myChart2');
//console.log(ctx2);
ctx2.getContext('2d');

var myPieChart = new Chart(ctx,{
  type: 'pie',
  data: {
    labels: ["Ótimo", "Bom", "Regular", "Irregular"],
    datasets: [{
      label: "",
      backgroundColor: ["#0b1141", "#143272","#0198cd","#93cbe9","#d4edf4"],
      data: [2,2,1,1]
    }]
  },
  options: {
    title: {
      display: true,
      text: 'Status de Desempenho Semanal:'
    }
  }
});

var myPieChart2 = new Chart(ctx2,{
  type: 'pie',
  data: {
    labels: ["Ótimo", "Bom", "Regular", "Irregular"],
    datasets: [{
      label: "",
      backgroundColor: ["#0b1141", "#143272","#0198cd","#93cbe9","#d4edf4"],
      data: [2,2,1,0]
    }]
  },
  options: {
    title: {
      display: true,
      text: 'Status de Desempenho Mensal:'
    }
  }
});

