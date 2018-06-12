var data = 0, Atends;
$.get("controller.do", `command=relatorio&data=${data}`)
	.done(function(data){
		console.log(data);
		localStorage.setItem("relatorio", data);
		formatRelatorio();
		if(data[0]){
			console.log("sucesso----------");
			
		}
	});	
var td;
Atends = JSON.parse(localStorage.getItem("relatorio", data));
console.log(Atends);
var formatRelatorio = () => {
for(var i = 0; i < Atends.length; i++){
	var row = $('<tr></tr>');
	for (var key in Atends[i]) {
	    if (Atends[i].hasOwnProperty(key)) {
	        console.log(key + " ---> " + Atends[i][key]);
	        td = `<td>${Atends[i][key]}<td>`
	        row.append(td);
	         
	    }
	}
	
	$("#customers").append(row);
}
}
