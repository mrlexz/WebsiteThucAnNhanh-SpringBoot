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
function delay(callback, ms) {
	var timer = 0;
	return function() {
		var context = this, args = arguments;
		clearTimeout(timer);
		timer = setTimeout(function() {
			callback.apply(context, args);
		}, ms || 0);
	};
}
(function dangky(hoTen, diaChi, phoneNumber, tenDangNhap, pass, email) {

	const body = JSON.stringify(hoTen = hoTen, diaChi = diaChi, phoneNumber = phoneNumber, tenDangNhap = tenDangNhap, pass = pass, email = email);
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
		}
	})
})
// Popup nhà sản xuất select option
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


//Ajax check username trong đăng ký tài khoản
$('#usernameDK').keyup(delay(function(e) {
	$.ajax({
		url: 'http://localhost:8080/ajax/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: this.value,
		success: function(res) {
			if (res) {
				$(".status").html("<font color=red>Tài khoản đã tồn tại</font>");
			} else {
				$(".status").html("<font color=#00ff00>Tài khoản hợp lệ</font>");
			}
		},
		error: function(request, status, error) {
			if (request.responseJSON.status === 400) {
				$(".status").html(null);
			}
		}
	})
}, 500));
// Ajax check số điện thoại trong đăng ký tài khoản
$('#sdt').keyup(delay(function(e) {
	var a = this.value.length;
	if (a != 10) {
		$(".status1").html("<font color=red>Số điện thoại phải đủ 10 kí tự</font>");
	} else {
		$(".status1").html("<font color=#00ff00>Số điện thoại hợp lệ</font>");

	}

}, 500));

$('#password').keyup(delay(function(e) {
	var a = this.value.length;
	if (a < 6) {
		$(".status2").html("<font color=red>Mật khẩu tối thiểu 6 kí tự</font>");
	} else {
		$(".status2").html("<font color=#00ff00>Mật khẩu hợp lệ</font>");

	}

}, 500));
/*Ajax form đăng ký*/
$('#form').submit(function() {
	
	var pass = $('#password').val();
	var username = $('#usernameDK').val();
	var phone = $('#sdt').val();


	if (pass.length < 6) {
		e.preventDefault();
		$(".status2").html("<font color=red >Mật khẩu tối thiểu 6 kí tự</font>");
		return false;
	}
	if (phone.length != 10) {
		e.preventDefault();
		return false;
	}
	$.ajax({
		url: 'http://localhost:8080/ajax/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: username,
		success: function(res) {
			if (res) {
				$(".status").html("<font color=red>Tài khoản đã tồn tại</font>");
				return false;
			}
			
		},
		error: function(request, status, error) {
			if (request.responseJSON.status === 400) {
				return false;
			}
		}
	})
	
	


})
//Ajax check tên sản phẩm trong thêm sản phẩm
$('#tsp').keyup(delay(function(e) {

	$.ajax({
		url: 'http://localhost:8080/ajax/createsanpham',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: this.value,
		success: function(res) {
			if (res) {
				$(".status").html("<font color=red>Tên sản phẩm đã tồn tại</font>");
			} else {
				$(".status").html("<font color=#00ff00>Tên sản phẩm hợp lệ</font>");
			}
		},
		error: function(request, status, error) {
			if (request.responseJSON.status === 400) {
				$(".status").html(null);
			}
		}
	})
}, 500));


/*Form thêm sản phẩm*/
$('#formSanPhama').submit(function(e) {
	// Xóa trắng thẻ div show lỗi
	$('#showerror').html('');
	var motasp = $('#moTa1').val();
	var tensp = $('#tsp').val();
	if (tensp.length == 0) {
		$(".status").html("<font color=red>Tên sản phẩm không được bỏ trống</font>");
		e.preventDefault();
		return false;
	}

	if (motasp.length == 0) {
		$(".status4").html("<font color=red>Mô tả sản phẩm không được bỏ trống</font>");
		e.preventDefault();
		return false;
	}
	/*$.ajax({
		url: 'http://localhost:8080/ajax/dangky',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: username,
		success: function(res) {
			if (res) {
				e.preventDefault();
				$(".status").html("<font color=red>Tài khoản đã tồn tại</font>");
				return false;
			}
			else {
				return  true;
			}
		},
		error: function(request, status, error) {
			if (request.responseJSON.status === 400) {
				e.preventDefault();
				return false;
			}
		}
	})
	return false;*/


})




//Ajax check tên nhà sản xuất trong thêm nhà sản xuất
$('#tenNhaSanXuat1').keyup(delay(function(e) {
	$.ajax({
		url: 'http://localhost:8080/ajax/creatensx',
		dataType: "json",
		method: 'POST',
		contentType: "application/json; charset=utf-8",
		data: this.value,

		success: function(res) {
			if (res) {
				$(".status").html("<font color=red>Tên nhà sản xuất đã tồn tại</font>");
			} else {
				$(".status").html("<font color=#00ff00>Tên nhà sản xuất hợp lệ</font>");
			}
		},
		error: function(request, status, error) {
			if (request.responseJSON.status === 400) {
				$(".status").html(null);
			}
		}
	})
}, 500));

//Ajax form nhà sản xuất
$('#formNhaSanXuata').submit(function(e) {
	// Xóa trắng thẻ div show lỗi
	$('#showerror').html('');
	var diachi = $('#diaChi1').val();
	var tennsx = $('#tenNhaSanXuat1').val();


	if (tennsx.length == 0) {
		$(".status").html("<font color=red>Tên nhà sản xuất  không được bỏ trống</font>");
		e.preventDefault();
		return false;
	}

	if (diachi.length == 0) {
		$(".status1").html("<font color=red>Địa chỉ không được bỏ trống</font>");
		e.preventDefault();
		return false;
	}
})
