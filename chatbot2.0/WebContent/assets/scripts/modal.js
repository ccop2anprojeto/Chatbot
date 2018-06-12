
$(".tooltip").on('mouseover', function(){
      $(".tooltip-text").addClass("active");
});

$(".tooltip").on('mouseout', function(){
    $(".tooltip-text").removeClass("active");
});


$(".tooltip").on('click', function(){
    if(!$("body").hasClass('overlay')){
        $("body").addClass('overlay');
    }
});

$(".modal .yes").on('click', function(){
    $(".modal").removeClass("active");
    finalizeService(JSON.parse(localStorage.getItem("atend")).id);
    
    //location.reload();
    
});

$(".modal .nop").on('click', function(){
    $(".modal").removeClass("active");
});

$(".mask, .modal .nop").on('click', function(){
    if( $("body").hasClass('overlay') ){
        $("body").removeClass('overlay');
    }
})