<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<c:if test="${empty list }">
	<!-- điều kiện -->
	<p class="alert alert-warning">Giỏ hàng trống</p>
</c:if>
<c:if test="${!empty list }">
	<div id="content">
		<div class="card mt-5">
			<h5 class="card-header">${sessionScope.user.fullname }</h5>
			<div class="card-body">
				<table class="table table-hover">
					<thead>
						<th><input type="checkbox" id="chkAll" onchange="selectAll()">
						</th>
						<th>Tên sản phẩm</th>
						<th>Hình ảnh</th>
						<th>Đơn giá</th>
						<th>Số lượng</th>
						<th></th>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="obj" varStatus="loop">
							<tr class="align-middle">
								<td><input type="checkbox" value="${obj.clothes.id }"
									name="chk"></td>
								<td>${obj.clothes.name }</td>
								<td><img class="imgIcon"
									src="/imgUpload/${obj.clothes.image }"></td>
								<td><span class="money"><fmt:formatNumber
											pattern="###,###,###">${obj.price} </fmt:formatNumber></span> VNĐ</td>
								<td><input type="number" name="amount" class="cartAmount"
									min="1" max="${obj.clothes.amount }" value=${obj.amount }>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="card-footer">
				<button type="button" class="btn btn-primary" data-bs-toggle="modal"
					data-bs-target="#totalModal" onclick="total()">Đặt hàng</button>
					<button type="button" class="btn btn-danger" data-bs-toggle="modal"
					data-bs-target="#delete">Xoá</button>

			</div>
			<!-- Modal -->
			<div class="modal" id="totalModal" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Xác nhận đặt
								hàng</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body" id="mess"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Suy nghĩ lại</button>
							<button id="order" type="button" class="btn btn-primary"
								onclick="order()">Đặt hàng</button>
						</div>
					</div>
				</div>
			</div>
			<div class="modal fade" id="delete" tabindex="-1"
				aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<h5 class="modal-title" id="exampleModalLabel">Xác nhận đặt
								hàng</h5>
							<button type="button" class="btn-close" data-bs-dismiss="modal"
								aria-label="Close"></button>
						</div>
						<div class="modal-body">Bạn có chắc muốn xoá?</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-bs-dismiss="modal">Suy nghĩ lại</button>
							<button id="order" type="button" class="btn btn-primary"
								onclick="deleteCart()">Xoá</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>