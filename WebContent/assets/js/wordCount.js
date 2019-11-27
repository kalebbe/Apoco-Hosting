$(document).ready(function() {
  $("#word_count").on('keyup', function() {
    var words = this.value.match(/\S+/g).length;
    if (words > 200) {
      var trimmed = $(this).val().split(/\s+/, 200).join(" ");
      $(this).val(trimmed + " ");
    }
    else {
      $('#display_count').text(words);
    }
  });
});