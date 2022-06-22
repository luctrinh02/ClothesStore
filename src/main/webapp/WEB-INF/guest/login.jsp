<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<c:if test="${!empty sessionScope.error }">
	<div class="alert alert-danger">${sessionScope.error }</div>
	<c:remove var="error" scope="session"></c:remove>
</c:if>
<c:if test="${!empty sessionScope.message }">
	<div class="alert alert-success">${sessionScope.message }</div>
	<c:remove var="message" scope="session"></c:remove>
</c:if>
	<div class="card-header">
		<h5 class="card-title">
			<span class="fas fa-right-to-bracket"></span> Đăng nhập
		</h5>
	</div>
	<div class="card-body">
		<div>
			<label>Email <span class="text-danger">*</span></label> <input
				type="email" class="form-control" name="email" id="email" value="${email }" required>
		</div>
		<div class="mt-2">
			<label>Mật khẩu <span class="text-danger">*</span></label> <input
				name="pass" type="password" class="form-control" id="pass" value="${pass }" required="required">
		</div>
		<div class="mt-2">
			 <input type="checkbox" name="save" id="save" ${save==true?'checked':'' }>
			 <label>Nhớ mật khẩu</label>
		</div>
		<div>
			<a href="/forgetPasswordIndex"><small>Quên
					mật khẩu?</small></a>
		</div>
		<div>
			<small>Chưa có tài khoản?<a href="/registryIndex"><small> Đăng ký
						ngay</small></a></small>
		</div>
	</div>
	<div class="card-footer">
		<button class="btn btn-primary" onclick="login()">Đăng nhập</button>
	</div>
