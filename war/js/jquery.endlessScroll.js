!function($) {

	/* 
	* smartscroll: debounced scroll event for jQuery *
	* https://github.com/lukeshumard/smartscroll
	* Based on smartresize by @louis_remi: https://github.com/lrbabe/jquery.smartresize.js *
	* Copyright 2011 Louis-Remi & Luke Shumard * Licensed under the MIT license. *
	*/

	var event = $.event,
		scrollTimeout;

	event.special.smartscroll = {
		setup: function () {
			$(this).bind("scroll", event.special.smartscroll.handler);
		},
		teardown: function () {
			$(this).unbind("scroll", event.special.smartscroll.handler);
		},
		handler: function (event, execAsap) {
			// Save the context
			var context = this,
			args = arguments;

			// set correct event type
			event.type = "smartscroll";

			if (scrollTimeout) { clearTimeout(scrollTimeout); }
			scrollTimeout = setTimeout(function () {
				$.event.handle.apply(context, args);
			}, execAsap === "execAsap" ? 0 : 100);
		}
	};

	$.fn.smartscroll = function (fn) {
		return fn ? this.bind("smartscroll", fn) : this.trigger("smartscroll", ["execAsap"]);
	};

	/**
		jQuery endless page scroll
		Options:
		callback: function to invoke when invoking. Return true if there are more results to retrieve
		callInProcess: a function that should return true if there is an invocation in process. If not set, on Ajax requests more than one scroll event will definitely happen.
		bottomPixels: Number of pixels at the bottom that will trigger a scroll. Default 100
		parent: the scroll container. Defaults to this.parent
		unbindListeners. set to true to unbind other endlessScroll (or any other scroll) listeners
	*/
	$.fn.endlessScroll = function(options) {
		options = $.extend({
			bottomPixels: 100,
			parent: $(this).parent(),
			callInProcess: function() {}
		}, options);
		
		var $parent = options.parent, 
			$document = $(document),
			$window = $(window),
			newDataRequired,
			eofReached,
			$this = $(this);

		options.unbindListeners && $this.unbind('smartscroll');
		$this.smartscroll(function() {
			if (this == document) {
				newDataRequired = $document.height() - $window.height() <= $window.scrollTop() + options.bottomPixels;
			} else {
				// calculates the actual height of the scrolling container
				newDataRequired = $this.scrollTop() >= $this[0].scrollHeight - $parent.height() - 100; //$parent.height() - $this.height() <= $this.scrollTop() + options.bottomPixels;
			}
			if (newDataRequired && !eofReached && !options.callInProcess()) {
				eofReached = !options.callback();
			}
		});

		// retrieve the first page of results
		options.callback();
		return this;
	}
}(jQuery);