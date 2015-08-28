$jq(function () {
		
		var filterList = {
		
			init: function () {
			
				// MixItUp plugin
				// http://mixitup.io
				$jq('#portfoliolist').mixitup({
					targetSelector: '.portfolio',
					filterSelector: '.filter',
					effects: ['fade'],
					easing: 'snap',
					// call the hover effect
					onMixEnd: filterList.hoverEffect()
				});				
			
			},
			
			hoverEffect: function () {
			
				// Simple parallax effect
				$jq('#portfoliolist .portfolio').hover(
					function () {
						$jq(this).find('.label').stop().animate({bottom: 0}, 200, 'easeOutQuad');
						$jq(this).find('img').stop().animate({top: -30}, 500, 'easeOutQuad');				
					},
					function () {
						$jq(this).find('.label').stop().animate({bottom: -40}, 200, 'easeInQuad');
						$jq(this).find('img').stop().animate({top: 0}, 300, 'easeOutQuad');								
					}		
				);				
			
			}

		};
		
		// Run the show!
		filterList.init();
		
		
	});	