<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="row mt-3">
	<div class="col-5">
		<img src="/imgUpload/${product.image }" alt="" class="w-100">
	</div>
	<div class="col-7">
		<p class="fw-bold fs-3">
			<span class="badge bg-danger">${product.tag==1?"Sale":(product.getTag()==2?"New":"Hot") }</span>
			Mã: <span id="productId">${product.id }</span> - ${product.name }
		</p>
		<p>
			<strike><fmt:formatNumber pattern="###,###,###">${product.price } </fmt:formatNumber>
				VNĐ</strike> <span class="text-danger fw-bold fs-3"> <fmt:formatNumber
					pattern="###,###,###">${product.price*(100-product.discount)/100 } </fmt:formatNumber>
				VNĐ
			</span>
		</p>
		<p>
			<span>Màu sắc: ${product.color }</span>| Kích cỡ: <span> <c:choose>
					<c:when test="${product.size==1}">XS</c:when>
					<c:when test="${product.size==2}">S</c:when>
					<c:when test="${product.size==3}">M</c:when>
					<c:when test="${product.size==4}">L</c:when>
					<c:when test="${product.size==5}">XL</c:when>
					<c:when test="${product.size==6}">XXL</c:when>
					<c:when test="${product.size==7}">XXXL</c:when>
				</c:choose>
			</span>
		</p>
		<label for="">Mô tả</label>
		<textarea class="form-control" rows="5" disabled>${product.description }</textarea>
		<div>
			<div class="row mt-2">
				<label for="">Số lượng</label>
				<div class="col-3">
					<div class="input-group mb-3">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon1" onclick="tru()">-</button>
						<input type="text" class="form-control" name="amount" id="amount"
							value="1" min="1" max="${product.amount }">
						<button class="btn btn-outline-secondary" type="button"
							id="button-addon2" onclick="cong()">+</button>
					</div>
				</div>
				<div class="col-4 text-muted fs-6 align-middle">${product.amount }
					sản phẩm có sẵn</div>
			</div>
		</div>
		<div class="col-3">
			<button class="btn btn-primary" onclick="addToCart()">
				<span class="fas fa-cart-plus"></span> Thêm vào giỏ hàng
			</button>
		</div>
	</div>
</div>
<div class="row mt-3">
	<label class="fw-bolder">Bình luận</label>
	<div class="comment">
		<c:forEach items="${listComment }" var="obj">
			<div class="row mt-2">
				<label>Người dùng: ${obj.user.fullname } | <span
					class="rating"> <c:forEach begin="1" end="${obj.rate }">
							<span class="text-warning"> <i class="fas fa-star"></i>
							</span>
						</c:forEach>
				</span>
				</label>

				<textarea name="comment" class="form-control" rows="3" disabled>${ obj.content}</textarea>
			</div>
		</c:forEach>
	</div>
	<hr>
	<hr>
	<form action="/user/comment?id=${product.id }" method="post">
		<div class="stars">
			<input class="star star-5" id="star-5" type="radio" name="rate"
				value="5" /> <label class="star star-5" for="star-5"></label> <input
				class="star star-4" id="star-4" type="radio" name="rate" value="4" />
			<label class="star star-4" for="star-4"></label> <input
				class="star star-3" id="star-3" type="radio" name="rate" value="3" />
			<label class="star star-3" for="star-3"></label> <input
				class="star star-2" id="star-2" type="radio" name="rate" value="2" />
			<label class="star star-2" for="star-2"></label> <input
				class="star star-1" id="star-1" type="radio" name="rate" value="1" />
			<label class="star star-1" for="star-1"></label>
		</div>
		<textarea name="content" class="form-control" rows="3"></textarea>
		<button class="btn btn-primary mt-2 mb-2">Đăng</button>
	</form>
</div>