//This method works for minesweeper and editting the accounts.
//Could be updated to include other edits, but would start becoming
//more complex.
function ajaxPost(url, name, value, name2, name3){
	var dataObject = {};
	if(value == null){
		dataObject[name] = $("#" + name).val();
	}
	else{
		dataObject[name] = value;
	}
	if(name2 != null){
		dataObject[name2] = $("#" + name2).val();
	}
	if(name3 != null){
		dataObject[name3] = $("#" + name3).val();
	}
	var ajax_control = $.ajax({
		url: '/' + url,
		type: 'POST',
		data: dataObject
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText); //Updates the html so the new content is shown.
	});
}

//This method was separated to make the other method less complex.
function ajaxFeed(url, name, value, name2, value2){
	var dataObject = {};
	dataObject[name] = $(value).val();
	if(name2 != null){
		dataObject[name2] = $(value2).val();
	}
	var ajax_control = $.ajax({
		url: '/' + url,
		type: 'POST',
		data: dataObject
	});
	ajax_control.always(function(){
		$('#content').html(ajax_control.responseText);
	});
}

function clickDiv(count){
	$('#feedTarget' + count).attr('disabled', false);
	$('#target' + count).css('display', 'inline-block');
}