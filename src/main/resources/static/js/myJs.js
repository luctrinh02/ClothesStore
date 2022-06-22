function clothes() {
	var amount = document.getElementsByClassName("clothes").length;
	$.ajax({
		url: "/loadmoreClothes",
		type: "get",
		data: {
			exits: Math.ceil(amount / 4)
		},
		success: function(data) {
			console.log(data);
			var row = document.getElementById("contentClothes");
			row.innerHTML += data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}
function accessory() {
	var amount = document.getElementsByClassName("accessory").length;
	$.ajax({
		url: "/loadmoreAccessory",
		type: "get",
		data: {
			exits: Math.ceil(amount / 4)
		},
		success: function(data) {
			var row = document.getElementById("contentAccessory");
			row.innerHTML += data;
		},
		error: function(xhr) {
			//Do Something to handle error
		}
	});
}
function footWear() {
	var amount = document.getElementsByClassName("footWear").length;
	$.ajax({
		url: "/loadmoreFootWear",
		type: "get",
		data: {
			exits: Math.ceil(amount / 4)
		},
		success: function(data) {
			var row = document.getElementById("contentFootWear");
			row.innerHTML += data;
		},
		error: function(xhr) {
		}
	});
}
function cong() {
	var amount = document.getElementById("amount");
	amount.value = Number(amount.value) + 1;
}
function tru() {
	var amount = document.getElementById("amount");
	if (amount.value > 1) {
		amount.value = Number(amount.value) - 1;
	}
}
function searchClothes() {
	var nameInput = document.getElementById("searchInput");
	var tagSelect = document.getElementById("searchTag");
	$.ajax({
		url: "/clothesSearch",
		type: "get",
		data: {
			name: nameInput.value,
			tag: tagSelect.value
		},
		success: function(data) {
			var row = document.getElementById("contentClothes");
			row.innerHTML = data;
			console.log(data);
		},
		error: function(xhr) {
		}
	});
}
function searchFootWear() {
	var nameInput = document.getElementById("searchInput");
	var tagSelect = document.getElementById("searchTag");
	console.log(tagSelect.value)
	$.ajax({
		url: "/footWearSearch",
		type: "get",
		data: {
			name: nameInput.value,
			tag: tagSelect.value
		},
		success: function(data) {
			var row = document.getElementById("contentFootWear");
			row.innerHTML = data;
		},
		error: function(xhr) {
		}
	});
}
function searchAccessory() {
	var nameInput = document.getElementById("searchInput");
	var tagSelect = document.getElementById("searchTag");
	$.ajax({
		url: "/accessorySearch",
		type: "get",
		data: {
			name: nameInput.value,
			tag: tagSelect.value
		},
		success: function(data) {
			var row = document.getElementById("contentAccessory");
			row.innerHTML = data;
		},
		error: function(xhr) {
		}
	});
}
const Toast = Swal.mixin({
	toast: true,
	position: 'top-end',
	showConfirmButton: false,
	timer: 1000,
	timerProgressBar: true,
	didOpen: (toast) => {
		toast.addEventListener('mouseenter', Swal.stopTimer)
		toast.addEventListener('mouseleave', Swal.resumeTimer)
	}
})
function addToCart() {
	var idProduct = document.getElementById("productId");
	var amountInput = document.getElementById("amount");
	if (isNaN(amountInput.value)) {
		Toast.fire({
			icon: 'error',
			title: 'Số lượng là số'
		})
	} else {
		$.ajax({
			url: "/addToCart",
			type: "get",
			data: {
				id: idProduct.innerHTML,
				amount: amountInput.value
			},
			success: function(data) {
				if (data == 0) {
					Toast.fire({
						icon: 'error',
						title: 'Sản phẩm không đủ'
					})
				} else {
					if (data == 1) {
						Toast.fire({
							icon: 'success',
							title: 'Thêm thành công'
						})
					} else {
						Toast.fire({
							icon: 'error',
							title: 'Số lượng là số'
						})
					}
				}
			},
			error: function(xhr) {
				Toast.fire({
					icon: 'error',
					title: 'Hệ thống bận!'
				})
			}
		});
	}
}
function selectAll() {
	var chkAll = document.getElementById("chkAll");
	var chk = document.getElementsByName("chk");
	if (chkAll.checked == true) {
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = true;
		}
	} else {
		for (var i = 0; i < chk.length; i++) {
			chk[i].checked = false;
		}
	}
}
function total() {
	$("#totalModal").modal("show");
	var chkArray = [];
	var amountArray = [];
	var chkInput = document.getElementsByName("chk");
	var amountInput = document.getElementsByName("amount");
	var mess = document.getElementById("mess");
	var check = 0;
	for (let i = 0; i < chkInput.length; i++) {
		if (chkInput[i].checked == true) {
			chkArray.push(chkInput[i].value);
			amountArray.push(amountInput[i].value);
			if (isNaN(amountInput[i].value)) {
				check++;
			}
		}
	}
	if (check > 0) {
		Toast.fire({
			icon: 'error',
			title: 'Số lượng là số'
		});
		$("#totalModal").modal("hide");
	} else {
		$.ajax({
			url: "/total",
			type: "get",
			data: {
				'chk': chkArray,
				'amount': amountArray
			},
			datatype: 'json',
			success: function(data) {
				if (data[1] == "") {
					mess.innerHTML = data[0];
				} else {
					mess.innerHTML = data[0] + "<br> Bằng chữ: <span class='text-uppercase'>" + data[1] + "<span>";
				}
			},
			error: function(xhr) {
			}
		});
	}
}
function order() {
	var chkArray = [];
	var amountArray = [];
	var chkInput = document.getElementsByName("chk");
	var amountInput = document.getElementsByName("amount");
	for (let i = 0; i < chkInput.length; i++) {
		if (chkInput[i].checked == true) {
			chkArray.push(chkInput[i].value);
			amountArray.push(amountInput[i].value);
		}
	}
	$.ajax({
		url: "/order",
		type: "get",
		data: {
			'chk': chkArray,
			'amount': amountArray
		},
		datatype: 'json',
		success: function(data) {
			if (data == 1) {
				$("#totalModal").modal("hide");
				window.location.href = "/user/cart";
				Toast.fire({
					icon: 'success',
					title: 'Đặt hàng thành công'
				})
			} else {
				$("#totalModal").modal("hide");
				Toast.fire({
					icon: 'error',
					title: 'Có sản phẩm vượt quá số lượng'
				})
			}
		},
		error: function(xhr) {
		}
	});
}
function deleteCart() {
	var chkArray = [];
	var chkInput = document.getElementsByName("chk");
	for (let i = 0; i < chkInput.length; i++) {
		if (chkInput[i].checked == true) {
			chkArray.push(chkInput[i].value);
		}
	}
	$.ajax({
		url: "/deleteCart",
		type: "get",
		data: {
			'chk': chkArray,
		},
		datatype: 'json',
		success: function(data) {
			if (data == 0) {
				$("#delete").modal("hide");
				Toast.fire({
					icon: 'error',
					title: 'Chưa chọn sản phẩm'
				})
			} else {
				$("#delete").modal("hide");
				window.location.href = "/user/cart";
				Toast.fire({
					icon: 'success',
					title: 'Xoá thành công'
				})
			}
		},
		error: function(xhr) {
		}
	});
}
function login() {
	var email = document.getElementById("email");
	var pass = document.getElementById("pass");
	var save = document.getElementById("save");
	$.ajax({
		url: "/login",
		type: "post",
		data: {
			email: email.value,
			pass: pass.value,
			save: save.checked
		},
		success: function(data) {
			if (data == false) {
				Toast.fire({
					icon: 'error',
					title: 'Tài khoản hoặc mật khẩu không chính xác hoặc đã bị vô hiệu'
				})
			} else {
				window.location.href = "/home";
				Toast.fire({
					icon: 'success',
					title: 'Đăng nhập thành công'
				})
			}
		},
		error: function(xhr) {
			Toast.fire({
				icon: 'error',
				title: 'Hệ thống bận!'
			})
		}
	});
}
function detail(id) {
	$.ajax({
		url: "/user/detail",
		type: "get",
		data: {
			idBill: id
		},
		success: function(data) {
			var row = document.getElementById("content");
			row.innerHTML = data;
		},
		error: function(xhr) {
			Toast.fire({
				icon: 'error',
				title: 'Hệ thống bận!'
			})
		}
	});
}
function userCancel(id) {
	$.ajax({
		url: "/user/cancel",
		type: "get",
		data: {
			id: id
		},
		success: function(data) {
			window.location.href = "/user/history";
		},
		error: function(xhr) {
			Toast.fire({
				icon: 'error',
				title: 'Hệ thống bận!'
			})
		}
	});
}
function changePass() {
	var old = document.getElementById("oldPass")
	var newP = document.getElementById("newPass")
	var conf = document.getElementById("confirm")
	if (old.value == "" || newP.value == "" || conf.value == "") {
		Toast.fire({
			icon: 'warning',
			title: 'Vui lòng nhập đủ dữ liệu'
		})
	} else {
		$.ajax({
			url: "/user/changePassword",
			type: "get",
			data: {
				old: old.value,
				new: newP.value,
				conf: conf.value
			},
			success: function(data) {
				if (data == 0) {
					Toast.fire({
						icon: 'success',
						title: 'Đổi mật khẩu thành công'
					})
					window.location.href = "/loginIndex";
				} else if (data == 1) {
					Toast.fire({
						icon: 'error',
						title: 'Mật khẩu cũ sai'
					})
				} else {
					Toast.fire({
						icon: 'error',
						title: 'Mật khẩu xác nhận không khớp'
					})
				}
			},
			error: function(xhr) {
				Toast.fire({
					icon: 'error',
					title: 'Hệ thống bận!'
				})
			}
		});
	}
}
function getKey() {
	var mail = document.getElementById("email")
	if (mail.value == "") {
		Toast.fire({
			icon: 'warning',
			title: 'Nhập hộ em cái email'
		})
	} else {
		$.ajax({
			url: "/getKey",
			type: "get",
			data: {
				mail: mail.value
			},
			success: function(data) {
				if (data == true) {
					Toast.fire({
						icon: 'success',
						title: 'Hãy kiểm tra mail'
					})
				} else {
					Toast.fire({
						icon: 'error',
						title: 'Email không tồn tại hoặc bị vô hiệu'
					})
				}
			},
			error: function(xhr) {
				Toast.fire({
					icon: 'error',
					title: 'Hệ thống bận!'
				})
			}
		});
	}
}
