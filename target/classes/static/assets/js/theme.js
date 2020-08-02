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

(function dangky(hoTen, diaChi,phoneNumber,tenDangNhap,pass,email) {

	const body = JSON.stringify( hoTen = hoTen, diaChi= diaChi,phoneNumber=phoneNumber,tenDangNhap= tenDangNhap,pass= pass, email= email);
	$.ajax({
		url: 'http://localhost:8080/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: body,
		success: function(res) {

			if (res){
			alert("loi");
			}
		},
		error: function(request, status, error) {
			alert(request.responseText);
/*console.log("dmmmmmmm")*/
/*console.log(request.responseJSON)*/
		}
	})
})

$(function ab() {
	$.ajax({
		url: 'http://localhost:8080/ajax/nsx',
		dataType: "text",
		method: 'GET',
		success: function(data) {
			JSON.parse(data).forEach(item => {
				console.log(item.maNhaSanXuat)
				$("#nsx").append(`<option value = ${item.maNhaSanXuat}>${item.tenNhaSanXuat}</option>`)
			})
		}
	})
}
)


function delay(callback, ms) {
  var timer = 0;
  return function() {
    var context = this, args = arguments;
    clearTimeout(timer);
    timer = setTimeout(function () {
      callback.apply(context, args);
    }, ms || 0);
  };
}


$('#usernameDK').keyup(delay(function (e) {
	$.ajax({
		url: 'http://localhost:8080/ajax/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: this.value,
		success: function(res) {
			if(res){
				$(".status").html("<font color=red>Tài khoản đã tồn tại</font>");
			} else {
				$(".status").html("<font color=#00ff00>Tài khoản hợp lệ</font>");
			}
		},
		error: function(request, status, error) {
			if(request.responseJSON.status===400) {
				$(".status").html(null);
			}
		}
	})
}, 500));