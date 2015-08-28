$jq(function(){
	$jq('.panelMenu2').tabSlideOut({							
		tabHandle: '.handle2',						
		pathToTabImage: 'images/sound_flag.png',		
		imageHeight: '34px',						
		imageWidth: '42px',						
		tabLocation: 'left',						
		speed: 300,								
		action: 'click',								
		topPos: '360px',							
		fixedPosition: false						
	});
});