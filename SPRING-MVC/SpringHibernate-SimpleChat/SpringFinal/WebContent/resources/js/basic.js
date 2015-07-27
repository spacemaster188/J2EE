/**
 * Get updated messages list
 */
var messagesArr = [];
var messagesArea = document.getElementById('displayMessages');
var sendMessagesForm = document.getElementById('sendMsgForm');
var messagesField = document.getElementById('messageArea');
var messagesStr = '';
var isLongPolling = false;
var isShortPolling = true;
var int;
var lastIncomingMessageLong = 0;

changePollingImg();
startPolling();

function startPolling() {
	if(isLongPolling){
		getNewMessagesLong();
	}else{
		getNewMessagesShort();
		int = self.setInterval("getNewMessagesShort()", 1000);
	}
}

var $request;

function getNewMessagesLong() {
		console.log('Long Fishing starts...');
		var lastIncomingMessageLongJson = {"lastIncomingMessageLong":JSON.stringify(lastIncomingMessageLong)};
		$request = $.ajax({
			type: 'POST',
			url: "listenMessageLong",
			data: lastIncomingMessageLongJson,
			dataType: 'json',
			success: function(data) {
				if(data.length != 0){
					lastIncomingMessageLong = Number(lastIncomingMessageLong) + 1;
					messagesArr.push(data);
					var tmpLong = '<div class="msgCell"><span class="span-msg">' + data + '</span></div>';
					messagesArea.innerHTML = messagesArea.innerHTML + tmpLong;
					}
				console.log('Long Fishing ends...');
				}, complete: getNewMessagesLong, timeout: 30000})
}

function sendNewMessageLong() {
	var newMsg = messagesField.value;
	var jsonStr = {json:JSON.stringify(newMsg)};
	$.ajax({
		url: 'addMsgLong',
		data: jsonStr,
		type: 'POST',
		dataType: 'json'
	});
	messagesField.value = '';
}

function getNewMessagesShort() {
	console.log('Short Fishing starts...');
			$.ajax({
				url: 'listenMessagesShort',
				type: 'POST',
				dataType: 'json',
				success: function(data){
					messagesArr = [];
					for (var i = 0; i < data.length; i++) {
						messagesArr.push(data[i].msg);
				    }
					var tmpShort = '';
				    for (var i = 0; i < messagesArr.length; i++) {
				    	tmpShort += '<div class="msgCell"><span class="span-msg">' + messagesArr[i] + '</span></div>';
				    }
				    if(tmpShort != messagesStr){
				    	 messagesArea.innerHTML = tmpShort;
				    	 messagesStr = tmpShort;
				    }
				}
			});
}

function sendNewMessageShort() {
	var newMsg = messagesField.value;
	var jsonStr = {json:JSON.stringify(newMsg)};
	$.ajax({
		url: 'addMsgShort',
		data: jsonStr,
		type: 'POST',
		dataType: 'json',
		success: function(data){
			messagesArr = [];
			for (var i = 0; i < data.length; i++) {
				messagesArr.push(data[i].msg);
		    }	
			var tmp = '';
		    for (var i = 0; i < messagesArr.length; i++) {
		    	tmp += '<div class="msgCell"><span class="span-msg">' + messagesArr[i] + '</span></div>';
		    }
		    if(tmp != messagesStr){
		    	 messagesArea.innerHTML = tmp;
		    	 messagesStr = tmp;
		    }
		}
	});
	messagesField.value = ' ';
}

function changePolling() {
	if(isShortPolling){
		clearInterval(int);
		isLongPolling = true;
		isShortPolling = false;
		changePollingImg();
		getNewMessagesLong();
	}else{
		isLongPolling = false;
		isShortPolling = true;
		changePollingImg();
		int = self.setInterval("getNewMessagesShort()", 1000);
		if ($request != null){ 
		    $request.abort();
		    $request = null;
		}
	}
}

function changePollingImg() {
	if(isLongPolling){
		var el = document.getElementById("polling-img");
		el.src = "resources/img/long-polling.png";
	}else{
		var el = document.getElementById("polling-img");
		el.src = "resources/img/short-polling.png";
	}
}

function sendNewMessage() {
	if(isLongPolling){
		sendNewMessageLong();
	}else{
		sendNewMessageShort();
	}
}