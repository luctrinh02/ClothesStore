<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<div class="d-flex flex-wrap justify-content-left mt-5">
	<div class="col-3">
		<select class="form-select me-2 mb-3" id="searchTag">
			<option value="0">Tất cả</option>
			<option value="1">Sale</option>
			<option value="2">New</option>
			<option value="3">Hot</option>
		</select>
	</div>
	<div class="col-3 ms-2 me-2 mb-3">
		<input class="form-control" type="text" placeholder="Nhập tên sản phẩm" id="searchInput">
	</div>
	<div class="col-2 mb-3">
		<button class="btn btn-outline-dark more" onclick="searchClothes()">Tìm kiếm</button>
	</div>
</div>
<div class="row gx-0 gy-3" id="contentClothes">
	<c:forEach items="${list }" var="obj">
		<div class="clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2">
			<div class="">
				<div class="img position-relative">
					<a
						href="/product?id=${obj.id }"><img
						src="/imgUpload/${obj.image }"
						class="w-100 productImg"></a> <span
						class="position-absolute text-white d-flex align-items-center justify-content-center">
						<c:choose>
							<c:when test="${obj.tag ==1}">Sale</c:when>
							<c:when test="${obj.tag ==2}">New</c:when>
							<c:when test="${obj.tag ==3}">Hot</c:when>
						</c:choose>
					</span>
				</div>
				<div class="text-center">
					<div class="rating mt-3">
						<span class="text-warning"> <i class="fas fa-star"></i>
						</span> <span class="text-warning"> <i class="fas fa-star"></i>
						</span> <span class="text-warning"> <i class="fas fa-star"></i>
						</span> <span class="text-warning"> <i class="fas fa-star"></i>
						</span> <span class="text-warning"> <i class="fas fa-star"></i>
						</span>
					</div>
					<p class="text-capitalize my-1">${obj.name }</p>
					<p class="fw-bold">
						<fmt:formatNumber pattern="###,###,###">${obj.price*(100-obj.discount)/100 }</fmt:formatNumber>
						₫
					</p>
				</div>
			</div>
		</div>
	</c:forEach>
</div>