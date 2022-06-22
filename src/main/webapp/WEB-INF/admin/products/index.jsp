<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<a class="btn btn-primary mt-3 mb-3" href="/admin/clothes/create">Thêm
	mới</a>
<c:if test="${!empty sessionScope.error }">
	<div class="alert alert-danger">${sessionScope.error }</div>
	<c:remove var="error" scope="session"></c:remove>
</c:if>
<c:if test="${!empty sessionScope.message }">
	<div class="alert alert-success">${sessionScope.message }</div>
	<c:remove var="message" scope="session"></c:remove>
</c:if>
<c:if test="${empty list }">
	<!-- điều kiện -->
	<p class="alert alert-warning">Không có gì</p>
</c:if>
<c:if test="${!empty list }">
	<form action="/admin/clothes/index">
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
						<a class="btn btn-secondary" href="/admin/clothes/index">Tất cả</a>
					</div>
				</div>
			</div>
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<th>Tên</th>
						<th>Category</th>
						<th>Tag</th>
						<th>Kích thước</th>
						<th>Số lượng</th>
						<th>Đơn giá</th>
						<th>Giảm giá</th>
						<th>Màu sắc</th>
						<th>Hình ảnh</th>
						<th>Trạng thái</th>
						<th>Người thêm</th>
						<th>Ngày tạo</th>
						<th>Sửa cuối</th>
						<th colspan="2">Thao tác</th>
					</thead>
					<tbody>
						<c:forEach items="${list.getContent()}" var="obj">
							<tr class="align-middle">
								<td>${obj.name }</td>
								<td>${obj.category.name }</td>
								<td><c:choose>
										<c:when test="${obj.tag==1 }">Sale</c:when>
										<c:when test="${obj.tag==2 }">New</c:when>
										<c:when test="${obj.tag==3 }">Hot</c:when>
									</c:choose></td>
								<td><c:choose>
										<c:when test="${obj.size==1}">XS</c:when>
										<c:when test="${obj.size==2}">S</c:when>
										<c:when test="${obj.size==3}">M</c:when>
										<c:when test="${obj.size==4}">L</c:when>
										<c:when test="${obj.size==5}">XL</c:when>
										<c:when test="${obj.size==6}">XXL</c:when>
										<c:when test="${obj.size==7}">XXXL</c:when>
									</c:choose></td>
								<td>${obj.amount }</td>
								<td>${obj.price }</td>
								<td>${obj.discount }%</td>
								<td>${obj.color }</td>

								<td><img class="imgIcon" src="/imgUpload/${obj.image }">
								</td>
								<td><c:if test="${obj.status==0 }">
						Đang kinh doanh
						</c:if> <c:if test="${obj.status==1 }">
						Ngừng kinh doanh
						</c:if></td>
								<td>${obj.user.fullname }</td>
								<td>
									<fmt:formatDate value="${obj.createdDate }" pattern="dd/MM/yyyy"/>
								</td>
								<td>
									<fmt:formatDate value="${obj.lastChangedDate }" pattern="dd/MM/yyyy"/>
								</td>
								<td><a href="/admin/clothes/edit?id=${obj.id }"
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
													<div class="modal-body">Bạn chắc chắn muốn ngừng kinh
														doanh sản phẩm này?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Huỷ</button>
														<a href="/admin/clothes/delete?id=${obj.id }"
															class="btn btn-danger">Đồng ý</a>
													</div>
												</c:if>
												<c:if test="${obj.status==1 }">
													<div class="modal-body">Bạn chắc chắn muốn kinh doanh
														tiếp sản phẩm này?</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-bs-dismiss="modal">Huỷ</button>
														<a href="/admin/clothes/restore?id=${obj.id }"
															class="btn btn-primary">Đồng ý</a>
													</div>
												</c:if>
											</div>
										</div>
									</div> <!-- end modal --></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer">
				<div class="d-flex justify-content-center">
					<a
						class="btn btn-primary me-1 ${list.getNumber()==0?'disabled':'' }"
						href="/admin/clothes/page?page=0"> <span
						class="fas fa-backward"></span>
					</a> <a
						class="btn btn-primary me-1 ${list.getNumber()==0?'disabled':'' }"
						href="/admin/clothes/page?page=${list.getNumber()-1 }"> <span
						class="fas fa-backward-step"></span>
					</a>
					<h3>${list.getNumber()+1 }/${list.getTotalPages() }</h3>
					<a
						class="btn btn-primary me-1 ${list.getNumber()==list.getTotalPages()-1?'disabled':'' }"
						href="/admin/clothes/page?page=${list.getNumber()+1 }"> <span
						class="fas fa-forward-step"></span>
					</a> <a
						class="btn btn-primary ${list.getNumber()==list.getTotalPages()-1?'disabled':'' } "
						href="/admin/clothes/page?page=${list.getTotalPages()-1 }">
						<span class="fas fa-forward"></span>
					</a>
				</div>
			</div>
		</div>
	</form>
</c:if>