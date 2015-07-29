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

$('#displayMessages').animate({scrollTop: $('#displayMessages')[0].scrollHeight});
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
		pollingFishingStarts();
		var lastIncomingMessageLongJson = {"lastIncomingMessageLong":JSON.stringify(lastIncomingMessageLong)};
        $request = $.ajax({
			type: 'POST',
			url: "listenMessageLong",
			data: lastIncomingMessageLongJson,
			dataType: 'json',
			success: function(data) {
				console.log('Long Fishing ends...');
				if(data.length != 0){
					lastIncomingMessageLong = Number(lastIncomingMessageLong) + 1;
					messagesArr.push(data[0]);
					var tmpLong = '<p class="msgCell"><span class="span-id">UserID:' + data[0].usersId + '</span><span class="span-msg"> ' + data[0].msg + '</span></p>';
					messagesArea.innerHTML = messagesArea.innerHTML + tmpLong;
					$('#displayMessages').animate({scrollTop: $('#displayMessages')[0].scrollHeight});
					}
				}, complete: function() {
		            getNewMessagesLong();
		            pollingFishingEnds();
		            pollingFishingStarts();
		        }})
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
	pollingFishingStarts();
			$.ajax({
				url: 'listenMessagesShort',
				type: 'POST',
				dataType: 'json',
				success: function(data){
					if(data.length != 0){
						messagesArr = data;
						var tmpShort = '';
					    for (var i = 0; i < messagesArr.length; i++) {
					    	tmpShort += '<p class="msgCell"><span class="span-id">UserID:' + messagesArr[i].usersId + '</span><span class="span-msg"> ' + messagesArr[i].msg + '</span></p>';
					    }
					    if(tmpShort != messagesStr){
					    	 messagesArea.innerHTML = tmpShort;
					    	 $('#displayMessages').animate({scrollTop: $('#displayMessages')[0].scrollHeight});
					    	 messagesStr = tmpShort;
					    }	
					}	
				    console.log('Short Fishing ends...');
					pollingFishingEnds();
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
			if(data.length != 0){
				messagesArr = data;
				var tmp = '';
			    for (var i = 0; i < messagesArr.length; i++) {
			    	tmp += '<p class="msgCell"><span class="span-id">UserID:' + messagesArr[i].usersId + '</span><span class="span-msg"> ' + messagesArr[i].msg + '</span></p>';
			    }
			    if(tmp != messagesStr){
			    	 messagesArea.innerHTML = tmp;
			    	 $('#displayMessages').animate({scrollTop: $('#displayMessages')[0].scrollHeight});
			    	 messagesStr = tmp;
			    }	
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

function pollingFishingStarts() {
	document.getElementById("fishing-start").src = "resources/img/fishing-start.png";
	document.getElementById("fishing-end").src = "resources/img/fishing-end-empty.png";
}

function pollingFishingEnds() {
	document.getElementById("fishing-end").src = "resources/img/fishing-end.png";
	document.getElementById("fishing-start").src = "resources/img/fishing-start-empty.png";
}