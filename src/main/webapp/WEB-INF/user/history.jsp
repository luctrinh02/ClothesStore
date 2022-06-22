<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
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
	<p class="alert alert-warning">Bạn chưa có đơn hàng nào</p>
</c:if>
<div id="content">
	<c:if test="${!empty list.getContent() }">
		<div class="card">
			<h5 class="card-header">Lịch sử mua hàng</h5>
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<th>Ngày mua</th>
						<th>Tổng tiền</th>
						<th>Trạng thái</th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${list.getContent()}" var="obj">
							<tr class="align-middle">
								<td><fmt:formatDate value="${obj.date }"
										pattern="dd/MM/yyyy" /></td>
								<td><fmt:formatNumber pattern="###,###,###">${obj.totalMoney }</fmt:formatNumber>
									VNĐ</td>
								<td><c:choose>
										<c:when test="${obj.status==0 }">
											<span class="text-success">Đang xử lý</span>
										</c:when>
										<c:when test="${obj.status==1 }">
											<span class="text-primary">Đã chấp nhận</span>
										</c:when>
										<c:when test="${obj.status==2 }">
											<span class="text-danger">Đã bị huỷ</span>
										</c:when>
									</c:choose></td>
								<td><button class="btn btn-primary"
										onclick="detail(${obj.id})">Chi tiết</button></td>
								<td><c:if test="${obj.status==0 }">
										<button type="button" class="btn btn-danger"
											data-bs-toggle="modal" data-bs-target="#checkXoa${obj.id }">Huỷ
											đơn</button>
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
												<div class="modal-body">Bạn chắc chắn muốn huỷ đơn
													này?</div>
												<div class="modal-footer">
													<button type="button" class="btn btn-secondary"
														data-bs-dismiss="modal">Huỷ</button>
													<button onclick="userCancel(${obj.id})"
														class="btn btn-danger">Đồng ý</button>
												</div>
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
						href="/user/history?page=0"> <span
						class="fas fa-backward"></span>
					</a> <a
						class="btn btn-primary me-1 ${list.getNumber()==0?'disabled':'' }"
						href="/user/history?page=${list.getNumber()-1 }"> <span
						class="fas fa-backward-step"></span>
					</a>
					<h3>${list.getNumber()+1 }/${list.getTotalPages() }</h3>
					<a
						class="btn btn-primary me-1 ${list.getNumber()==list.getTotalPages()-1?'disabled':'' }"
						href="/user/history?page=${list.getNumber()+1 }"> <span
						class="fas fa-forward-step"></span>
					</a> <a
						class="btn btn-primary ${list.getNumber()==list.getTotalPages()-1?'disabled':'' } "
						href="/user/history?page=${list.getTotalPages()-1 }">
						<span class="fas fa-forward"></span>
					</a>
				</div>
			</div>
		</div>
	</c:if>
</div>