(function() {
  $(function() {
    console.log('yo');
    return $.get("/cars", function(persons) {
      return $.each(persons, function(index, person) {
        return $("#cars").append($("<li>").text(person.name));
      });
    });
  });

}).call(this);

//# sourceMappingURL=index.js.map