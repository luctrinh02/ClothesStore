<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<div class="card-header">
	<h5 class="card-title">
		<span class="fas fa-right-to-bracket"></span> Đổi mật khẩu
	</h5>
</div>
<div class="card-body">
	<div>
		<label>Mật khẩu cũ <span class="text-danger">*</span></label> <input
			type="password" class="form-control" name="email" id="oldPass"
			required>
	</div>
	<div class="mt-2">
		<label>Mật khẩu mới <span class="text-danger">*</span></label> <input
			name="pass" type="password" class="form-control" id="newPass"
			required="required">
	</div>
	<div class="mt-2">
		<label>Xác nhận mật khẩu mới <span class="text-danger">*</span></label>
		<input name="pass" type="password" class="form-control" id="confirm"
			required="required">
	</div>
</div>
<div class="card-footer">
	<button class="btn btn-primary" onclick="changePass()">Đổi
		mật khẩu</button>
</div>
