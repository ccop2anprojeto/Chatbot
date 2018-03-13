
console.log("funcionou caralhooooo, seus merda de machista opressor");



$(".icon_close a").on('click', function(){
  $(".container_chat").removeClass("active");
  $(".icon_chat").addClass("active");
});


$(".icon_chat a").on('click', function(){
  $(".container_chat").addClass("active");
  $(".content_messages").addClass("expand");
  $(".content_area").addClass("expand");
  $(".container_chat").addClass("expand");
  $(".icon_chat").removeClass("active");
});

$(".container_chat header").on('click', function(){

  if($(".content_messages").hasClass("expand")){

    $(".content_messages").removeClass("expand");
    $(".content_area").removeClass("expand");
    $(".container_chat").removeClass("expand");
  }
  else{
    $(".content_messages").addClass("expand");
    $(".content_area").addClass("expand");
    $(".container_chat").addClass("expand");
  }
  
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