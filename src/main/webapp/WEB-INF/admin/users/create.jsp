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
<form class="mb-3" method="post" action="/admin/users/store">
	<div class="card-body">
		<div class="row mt-2">
			<label>Họ tên <span class="text-danger">*</span></label>
			<div>
				<input name="fullname" type="text" class="form-control" required>
			</div>
		</div>
		<div class="row mt-2">
			<label>Địa chỉ <span class="text-danger">*</span></label>
			<div>
				<input type="text" class="form-control" name="address">
			</div>
		</div>
		<div class="mt-2">
			<label for="gtDangKy">Giới tính</label>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender"
					id="namDangKy" value="0" checked> <label
					class="form-check-label" for="nam">Nam</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="gender" value="1">
				<label class="form-check-label" for="nu">Nu</label>
			</div>
		</div>
		<div class="row mt-2">
			<label>Email <span class="text-danger">*</span></label>
			<div>
				<input type="email" class="form-control" name="email" required>
			</div>
		</div>
		<div class="row mt-2">
			<label for="passDangKy">Mật khẩu <span class="text-danger">*</span></label>
			<div>
				<input type="password" class="form-control" required name="password">
			</div>
		</div>
		<div class="mt-2">
			<label for="gtDangKy">Vai trò </label>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="role"
					id="namDangKy" value="0" checked> <label
					class="form-check-label" for="user">User</label>
			</div>
			<div class="form-check form-check-inline">
				<input class="form-check-input" type="radio" name="role" value="1">
				<label class="form-check-label" for="admin">Admin</label>
			</div>
		</div>
	</div>
	<div class="mt-2">
		<button type="reset" class="btn btn-secondary">Reset</button>
		<button type="button" class="btn btn-primary" data-bs-toggle="modal"
			data-bs-target="#exampleModal">Thêm</button>

		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
			aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="exampleModalLabel">Thông báo</h5>
						<button type="button" class="close" data-bs-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>
					<div class="modal-body">Bạn chắc chắn muốn thêm người dùng
						này?</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-secondary"
							data-bs-dismiss="modal">Nghĩ lại</button>
						<button type="submit" class="btn btn-primary">Thêm</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form>
