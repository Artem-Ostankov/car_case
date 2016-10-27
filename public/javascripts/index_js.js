
// I used http://handlebarsjs.com/ to do some fast and efficient templating

var source   = $("#car-entry-template").html();
var template = Handlebars.compile(source);

(function() {
  $(function() {
    return $.get("/cars", function(cars) {
      return $.each(cars, function(index, car) {
        var context = {title: "My New Post", body: "This is my first post!"};
        console.log(car);
        var html    = template(car);
        return $("#cars_list").append(html);
      });
    });
  });

}).call(this);

function deleteCar(uuid){
    console.log(uuid);
    $.post("/delete_car", {uuid:uuid}, function(data) {
        console.log(data);
        if(data.result != 'ok')
            console.log("deletion didn't work");
        location.reload();
    });
};
