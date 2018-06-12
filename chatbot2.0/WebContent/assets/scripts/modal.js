
$(".tooltip").on('mouseover', function(){
      $(".tooltip-text").addClass("active");
});

$(".tooltip").on('mouseout', function(){
    $(".tooltip-text").removeClass("active");
});

$(".tooltip").on('click', function(){
    $(".modal").addClass("active");
});