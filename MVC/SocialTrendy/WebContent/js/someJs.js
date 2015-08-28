$jq(document).ready(function() {
	$jq('.title').append('<span></span>');
	$jq('.post span').each(function() {
		var trigger = $jq(this), state = false, el = trigger.parent().next('.entry');
		trigger.click(function(){
			state = !state;
			el.slideToggle();
			trigger.parent().parent().toggleClass('inactive');
		});
	});
});