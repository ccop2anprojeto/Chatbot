var data = 0, Atends;
$.get("controller.do", `command=Relatorio&data=${data}`)
	.done(function(data){
		localStorage.setItem("relatorio", data);
		Data = JSON.parse(data);
		console.log(Data);
		fillBlocks(Data);
		formatRelatorio(Data[1]);
		if(data[0]){
			console.log("sucesso----------");
		}
	});	
//Atends = JSON.parse(localStorage.getItem("relatorio", data));
//console.log(Atends);

var fillBlocks = (data) => {
	let daily = JSON.parse(data[0]).daily;
	let week = JSON.parse(data[0]).week;
	let month = JSON.parse(data[0]).month;
	console.log(daily);
	
	$(".diario p").append(daily);
	$(".semanal p").append(week);
	$(".mensal p").append(month);
}

var formatRelatorio = (Atends) => {
	
	//codigo para popular a tabela
	var teste = Atends;
	console.log(Atends[0]);	
	//aqui faço um for no array Atends pois temos 40 atendimentos, cada index do array é um atendimento
	for(var i = 0; i < Atends.length; i++){
		var row = $('<tr></tr>');
		var obj = JSON.parse(Atends[i]);
		console.log(obj);
		//aqui é um for in em cada index do array
		for(var key in obj) {
	       var td = `<td>${obj[key]}</td>`
	    	console.log(td);
	        row.append(td);
		}
	
		$("#customers tbody").append(row);
		
	}
}

