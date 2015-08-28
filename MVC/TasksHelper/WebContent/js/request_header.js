$jq(function(){
	$jq('.panel1').tabSlideOut({
		tabHandle: '.handle',
		pathToTabImage: 'img/req_header.png',
		imageHeight: '34px',
		imageWidth: '42px',	
		tabLocation: 'left',
		speed: 300,	
		action: 'click',
		topPos: '300px',
		fixedPosition: false
	});
});