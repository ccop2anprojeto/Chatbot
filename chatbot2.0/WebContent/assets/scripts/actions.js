$(".icon_close a").on('click', function(){
	finalizeService();
  
});

var finalizeService = () => {
	var teste = confirm("Tem certeza que deseja finalizar o atendimento?");
	if(teste){
	/*	var atendId = JSON.parse(localStorage.getItem("atend")).id;
		console.log(atendId);
		$.get("controller.do", `command=finalizeService&id=${atendId}`)
		.done(function(data){
			console.log(data);
			if(data[0]){
				console.log("atendimento finalizdo com sucesso!");
				$(".container_chat").removeClass("active");
				$(".icon_chat").addClass("active");
			}
		});	*/
		
	}
}
$(".icon_chat a").on('click', function(){
	console.log("clicou");
  $(".wisper").addClass("active");
});


$(".container_chat header").on('click', function(){

  if($(".content_messages").hasClass("expand")){   
    $("html").removeClass("fixed");
    $(".content_messages").removeClass("expand");
    $(".content_area").removeClass("expand");
    $(".container_chat").removeClass("expand");
  }
  else{
    $("html").addClass("fixed");
    $(".content_messages").addClass("expand");
    $(".content_area").addClass("expand");
    $(".container_chat").addClass("expand");
  }
  
});

$('#perg').keypress(function (e) {
	  var key = e.which;
	  if(key == 13){
	     
	    $('.box_area .btn-enviar').click();
	     return false;  
	   }
	 });
	 
	 $("#nav-btn").click(function() {
	  $('html, body').animate({
	      scrollTop: $("#elementtoScrollToID").offset().top
	  }, 2000);
	});


	$(".icon_main a").on('click', function(){

	  if($(".column_main").hasClass("active")){
	    $(".column_main").removeClass("active");
	    $(".overlay").removeClass("active");
	  }
	    
	  else{
	    $(".column_main").addClass("active");
	    $(".overlay").addClass("active");
	  }
	  
	});

	$(".icon_closeMain a").on('click', function(){
	  $(".column_main").removeClass("active");
	  $(".overlay").removeClass("active");
	});

	$(".overlay").on('click', function(){
	    $(".column_main").removeClass("active");
	    $(".overlay").removeClass("active");
	  });
