<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%><!-- nhúng thưu viện -->
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<section class="py-5">
	<div class="container">
		<div class="title text-center">
			<a href="/clothes"
				class="link-dark"><h2 class="position-relative d-inline-block">
					Quần áo</h2></a>
		</div>
		<div class="row gx-0 gy-3" id="contentClothes">
			<c:forEach items="${listClothes }" var="obj">
				<div class="clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2">
					<div class="">
						<div class="img position-relative">
							<a href="/product?id=${obj.id }"><img
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
		<div class="d-flex justify-content-center">
			<button class="btn btn-outline-dark more mt-2" onclick="clothes()">Xem thêm</button>
		</div>
	</div>
</section>
<section class="py-5">
	<div class="container">
		<div class="title text-center">
			<a href="/footWear"
				class="link-dark"><h2 class="position-relative d-inline-block">
					Giày dép</h2></a>
		</div>
		<div class="row gx-0 gy-3" id="contentFootWear">
			<c:forEach items="${listFootWear }" var="obj">
				<div class="footWear col-md-6 col-lg-4 col-xl-3 mt-4 p-2">
					<div class="">
						<div class="img position-relative">
							<a href="/product?id=${obj.id }"><img
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
								<fmt:formatNumber pattern="###,###,###">${obj.price }</fmt:formatNumber>
								₫
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="d-flex justify-content-center">
			<button class="btn btn-outline-dark more mt-2" onclick="footWear()">Xem thêm</button>
		</div>
	</div>
</section>
<section class="py-5">
	<div class="container">
		<div class="title text-center">
			<a href="/accessory"
				class="link-dark"><h2 class="position-relative d-inline-block">
					Phụ kiện</h2></a>
		</div>
		<div class="row gx-0 gy-3" id="contentAccessory">
			<c:forEach items="${listAccessory }" var="obj">
				<div class="accessory col-md-6 col-lg-4 col-xl-3 mt-4 p-2">
					<div class="">
						<div class="img position-relative">
							<a href="/product?id=${obj.id }"><img
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
								<fmt:formatNumber pattern="###,###,###">${obj.price }</fmt:formatNumber>
								₫
							</p>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="d-flex justify-content-center">
			<button class="btn btn-outline-dark more mt-2" onclick="accessory()">Xem thêm</button>
		</div>
	</div>
</section>