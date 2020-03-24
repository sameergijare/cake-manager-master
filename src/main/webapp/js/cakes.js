(function() {
  var cakesApi = "http://localhost:8282/cakes?token=abcd";
  $.getJSON( cakesApi, {
        format: "json"
  }).done(function( data ) {
    $.each( data, function( i, cake ) {
        $("#cakes").append('<div id="cake"><p>' + cake.title + '</p><p>' + cake.desc + '</p><img src="' + cake.image + '"/></div>');
      });
    });
})();

$('form').submit(function (evt) {
    $.ajax({
        url: 'http://localhost:8282/cakes?token=abcd',
        data: $('form').serialize(),
        processData: false,
        type: 'PUT'
    });
})