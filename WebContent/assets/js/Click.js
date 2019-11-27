//This is for blocking the contextmenu. Does not work < IE 9, but who uses that anyways.
document.addEventListener('contextmenu', function (e) {
    e.preventDefault();
}, false);

function Post(val) {
	var ajax_control = $.ajax({
		url: 'https://apoco-app.herokuapp.com/mines/right',
		type: 'POST',
		data: {btn: val}
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}
