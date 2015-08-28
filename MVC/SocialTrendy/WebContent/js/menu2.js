$jq(function(){
	$jq('.panelMenu1').tabSlideOut({							
		tabHandle: '.handle',						
		pathToTabImage: 'images/pic_flag.png',				
		imageHeight: '34px',						
		imageWidth: '42px',						
		tabLocation: 'left',						
		speed: 300,								
		action: 'click',								
		topPos: '300px',						
		fixedPosition: false						
	});
});