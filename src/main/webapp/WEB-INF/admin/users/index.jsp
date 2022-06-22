<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<a class="btn btn-primary mt-3 mb-3" href="/admin/users/create">Thêm
	mới</a>
<c:if test="${!empty sessionScope.error }">
	<div class="alert alert-danger">${sessionScope.error }</div>
	<c:remove var="error" scope="session"></c:remove>
</c:if>
<c:if test="${!empty sessionScope.message }">
	<div class="alert alert-success">${sessionScope.message }</div>
	<c:remove var="message" scope="session"></c:remove>
</c:if>
<c:if test="${empty list.getContent() }">
	<!-- điều kiện -->
	<p class="alert alert-warning">Không có gì</p>
</c:if>
<c:if test="${!empty list.getContent() }">
	<form action="/admin/users/index">
		<div class="card">
			<div class="card-header">
				<div class="row d-flex justify-content-start">
					<div class="col-3">
						<input type="text" name="name" class="form-control"
							value="${name }">
					</div>
					<div class="col-2">
						<button class="btn btn-primary">Tìm kiếm</button>
					</div>
					<div class="col-2">
						<a class="btn btn-secondary" href="/admin/users/index">Tất
							cả</a>
					</div>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<th>Họ tên</th>
						<th>Email</th>
						<th>Địa chỉ</th>
						<th>Giới tính</th>
						<th>Vai trò</th>
						<th>Trạng thái</th>
						<th colspan="2">Thao tác</th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${list.getContent()}" var="obj">
							<tr class="align-middle">
								<td>${obj.fullname }</td>
								<td>${obj.email }</td>
								<td>${obj.address }</td>
								<td><c:if test="${obj.gender==0 }">Nam</c:if> <c:if
										test="${obj.gender==1 }">Nữ</c:if></td>
								<td><c:if test="${obj.role==0 }">User</c:if> <c:if
										test="${obj.role==1 }">Admin</c:if></td>
								<td><c:if test="${obj.status==0 }">
						Đang hoạt động
						</c:if> <c:if test="${obj.status==1 }">
						Vô hiệu hoá
						</c:if></td>
								<td><a href="/admin/users/edit?id=${obj.id }"
									class="btn btn-primary">Cập nhật</a></td>
								<td><c:if test="${obj.status==0 }">
										<button type="button" class="btn btn-danger"
											data-bs-toggle="modal" data-bs-target="#checkXoa${obj.id }">Vô
											hiệu</button>
									</c:if> <c:if test="${obj.status==1 }">
										<button type="button" class="btn btn-success"
											data-bs-toggle="modal" data-bs-target="#checkXoa${obj.id }">
											Hiệu lực</button>
									</c:if> <!-- Modal -->
									<div class="modal fade" id="checkXoa${obj.id }" tabindex="-1"
										aria-labelledby="exampleModalLabel" aria-hidden="true">
										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<h5 class="modal-title" id="exampleModalLabel">Warning</h5>
													<button type="button" class="btn-close"
														data-bs-dismiss="modal" aria-label="Close"></button>
												</div>
												<c:if test="${obj.status==0 }">
													<div class="modal-body">Bạn chắc chắn muốn vô hiệu
														tài khoản này?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Huỷ</button>
														<a href="/admin/users/delete?id=${obj.id }"
															class="btn btn-danger">Đồng ý</a>
													</div>
												</c:if>
												<c:if test="${obj.status==1 }">
													<div class="modal-body">Bạn chắc chắn muốn khôi phục
														tài khoản này?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Huỷ</button>
														<a href="/admin/users/restore?id=${obj.id }"
															class="btn btn-primary">Đồng ý</a>
													</div>
												</c:if>
											</div>
										</div>
									</div> <!-- end modal --></td>
								<c:if test="${obj.role==0 }">
									<td><a href="/admin/bills/history?id=${obj.id }"
										class="btn btn-primary">Lịch sử</a></td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center">
					<a
						class="btn btn-primary me-1 ${list.getNumber()==0?'disabled':'' }"
						href="/admin/users/page?page=0"> <span class="fas fa-backward"></span>
					</a> <a
						class="btn btn-primary me-1 ${list.getNumber()==0?'disabled':'' }"
						href="/admin/users/page?page=${list.getNumber()-1 }"> <span
						class="fas fa-backward-step"></span>
					</a>
					<h3>${list.getNumber()+1 }/${list.getTotalPages() }</h3>
					<a
						class="btn btn-primary me-1 ${list.getNumber()==list.getTotalPages()-1?'disabled':'' }"
						href="/admin/users/page?page=${list.getNumber()+1 }"> <span
						class="fas fa-forward-step"></span>
					</a> <a
						class="btn btn-primary ${list.getNumber()==list.getTotalPages()-1?'disabled':'' } "
						href="/admin/users/page?page=${list.getTotalPages()-1 }"> <span
						class="fas fa-forward"></span>
					</a>
				</div>
			</div>
		</div>
	</form>
</c:if>