/**
 * Get updated messages list
 */
var messagesArr = [];
var messagesArea = document.getElementById('displayMessages');
var sendMessagesForm = document.getElementById('sendMsgForm');
var messagesField = document.getElementById('messageArea');
var messagesStr = '';

sendAjaxQuery();
var int = self.setInterval("sendAjaxQuery()", 1000);

function sendAjaxQuery() {
			$.ajax({
				url: 'update',
				type: 'POST',
				dataType: 'json',
				success: function(data){
					messagesArr = data;	
					var tmp = '';
				    for (var i = 0; i < messagesArr.length; i++) {
				    	tmp += '<div class="msgCell"><span class="span-id"> UserId-' + messagesArr[i].usersId + ' : </span><span class="span-msg">' + messagesArr[i].msg + '</span></div>';
				    }
				    console.log('vipolnilsya metod');
				    if(tmp != messagesStr){
				    	 messagesArea.innerHTML = tmp;
				    	 messagesStr = tmp;
				    	 console.log(messagesArr.length);
				    }
				}
			});
}

function sendNewMessage() {
	var newMsg = messagesField.value;
	console.log(newMsg);
	var jsonStr = {json:JSON.stringify(newMsg)};
	$.ajax({
		url: 'updateMsg',
		data: jsonStr,
		type: 'POST',
		dataType: 'json',
		success: function(data){
			messagesArr = data;	
			var tmp = '';
		    for (var i = 0; i < messagesArr.length; i++) {
		    	tmp += '<div class="msgCell"><span class="span-id"> UserId-' + messagesArr[i].usersId + ' : </span><span class="span-msg">' + messagesArr[i].msg + '</span></div>';
		    }
		    console.log('vipolnilsya metod');
		    if(tmp != messagesStr){
		    	 messagesArea.innerHTML = tmp;
		    	 messagesStr = tmp;
		    	 console.log(messagesArr.length);
		    }
		}
	});
	messagesField.value = '';
}