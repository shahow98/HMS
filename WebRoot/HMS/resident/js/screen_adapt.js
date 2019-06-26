$(document).ready(function() {
	var h = $("#main").height();
	var w = $(window).width();
	var fw = $("#menu").width();
	$("#menu").css("height", h + "px");
	$("#main").css("height", h + "px")
		.css("width", (w - fw) + "px");
});
