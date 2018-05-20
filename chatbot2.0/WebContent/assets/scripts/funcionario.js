
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
var msgRecebida = false;
setInterval(function(){
	$.get("controller.do", `command=searchMessage&id_para=${funcionario.id}`)
	.done(function( data ) {
		console.log(data);
		var Data = JSON.parse(data);
		console.log(Data);
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

var msgSuccess = () => {
	
}
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
console.log(ctx);
ctx.getContext('2d');

var ctx2 = document.getElementById('myChart2');
console.log(ctx2);
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

