$(document).ready(function() {
    $('#puzzle').submit(function(e) {
        e.preventDefault();
        $.getJSON(
            '/search', $('#puzzle').serializeArray(),
            function(data) {
                var items = [];

                $.each(data, function(i) {
                    items.push('<li>' + data[i] + '</li>');
                });


                $("#solutions").empty();
                $("#solutions").append(
                    $('<ul/>', {
                        html: items.join('')
                    }));
            });
    });
});
