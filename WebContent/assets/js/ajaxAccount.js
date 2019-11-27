//I attempted to put all of these into one method, but it didn't seem possible
//due to the data portion. May try again later.
function updFirst() {
	var ajax_control = $.ajax({
		url: '/Apoco/account/updateFirst',
		type: 'POST',
		data: {firstName: $('#firstName').val()} //Sends the data for form processing. Same below.
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText); //Shows the updated page. Same below.
	});
}

function updLast() {
	var ajax_control = $.ajax({
		url: '/Apoco/account/updateLast',
		type: 'POST',
		data: {lastName: $('#lastName').val()}
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}

function updUser() {
	var ajax_control = $.ajax({
		url: '/Apoco/account/updateUser',
		type: 'POST',
		data: {username: $('#username').val()}
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}

function updEmail() {
	var ajax_control = $.ajax({
		url: '/Apoco/account/updateEmail',
		type: 'POST',
		data: {email: $('#email').val()}
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}

function updPass() {
	var ajax_control = $.ajax({
		url: '/Apoco/account/updatePass',
		type: 'POST',
		data: {oldPass: $('#oldPass').val(),
			pass: $('#pass').val(),
			rePass: $('#rePass').val()}
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}