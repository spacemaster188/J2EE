$jq(function(){
	$jq('.panel2').tabSlideOut({
		tabHandle: '.handle2',
		pathToTabImage: 'img/req_body.png',
		imageHeight: '34px',
		imageWidth: '42px',	
		tabLocation: 'left',
		speed: 300,	
		action: 'click',
		topPos: '360px',
		fixedPosition: false
	});
});