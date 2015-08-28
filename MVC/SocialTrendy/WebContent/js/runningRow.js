$jq(document).ready(function() {
	(function() {
	//settings
	var fadeSpeed = 200, fadeTo = 0.5, topDistance = 30;
	var sibar = function() { $jq('.box').fadeTo(fadeSpeed,1); }, stbar = function() { $jq('.box').fadeTo(fadeSpeed,fadeTo); };
	var inside = false;
	//do
	$jq(window).scroll(function() {
		position = $(window).scrollTop();
		if(position > topDistance && !inside) {
			//add mouseover events
			stbar();
			$jq('.box').bind('mouseenter',sibar);
			$jq('.box').bind('mouseleave',stbar);
			inside = true;
		}
	});
	//close
	$jq('#close').live('click', function(event) {
		$jq('.box').toggle('show');
	});
	})();
});