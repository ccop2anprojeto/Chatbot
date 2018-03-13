
$( document ).ready( function () {     
    $( window ).scroll( function () {
      if( $( this ).scrollTop() > 450 ) {
        $( '.navbar' ).addClass( 'solid' );
      } else {
          $( '.navbar' ).removeClass( 'solid' );
      }
    });
});