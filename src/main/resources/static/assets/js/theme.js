// Custome theme code

if ($('.clean-gallery').length > 0) {
	baguetteBox.run('.clean-gallery', { animation: 'slideIn' });
}

if ($('.clean-product').length > 0) {
	$(window).on("load", function() {
		$('.sp-wrap').smoothproducts();
	});
}

$(function() {
	$(document).scroll(function() {
		var $nav = $(".nav-custom");
		$nav.toggleClass('scrolled', $(this).scrollTop() > $nav.height());
	});
});

$(function dangky(user, password) {

	const body = JSON.stringify({ email: "tanluc", pass: "cc" });
	$.ajax({
		url: 'http://localhost:8080/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: body,
		success: function(res) {
			console.log(res)
		},
		error: function(request, status, error) {
		/*	alert(request.responseText);	*/
			console.log(request.responseJSON)
		}
	})
})