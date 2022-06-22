package com.asm.clothesStore.controllers;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.CategoryService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.services.UserService;
import com.asm.clothesStore.uitilities.CookieUtil;
import com.asm.clothesStore.uitilities.EncryptUtil;

@RestController
public class HomeRestController {
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;

	@GetMapping("/loadmoreClothes")
	public String listClothes(int exits) {
		List<Category> categories = categoryService.getAll();
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = clothesService.getByCategory(categories.get(0), exits);
		for (Clothes clothes : clothess) {
			String tag = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tag + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@GetMapping("/loadmoreFootWear")
	public String listFootWear(int exits) {
		List<Category> categories = categoryService.getAll();
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = clothesService.getByCategory(categories.get(1), exits);
		for (Clothes clothes : clothess) {
			String tag = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"footWear col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tag + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@GetMapping("/loadmoreAccessory")
	public String listAccessory(int exits) {
		List<Category> categories = categoryService.getAll();
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = clothesService.getByCategory(categories.get(2), exits);
		for (Clothes clothes : clothess) {
			String tag = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"accessory col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tag + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@GetMapping("/footWearSearch")
	public String footWearSearch(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "tag", required = true) Integer tag) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = new ArrayList<>();
		if (tag != 0) {
			clothess = clothesService.getByCategoryAndTagAndName(categoryService.getById(2), tag, "%" + name + "%");
		} else {
			clothess = clothesService.getByCategoryAndName(categoryService.getById(2), "%" + name + "%");
		}
		for (Clothes clothes : clothess) {
			String tagString = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tagString + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@GetMapping("/clothesSearch")
	public String clothesSearch(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "tag", required = true) Integer tag) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = new ArrayList<>();
		if (tag != 0) {
			clothess = clothesService.getByCategoryAndTagAndName(categoryService.getById(1), tag, "%" + name + "%");
		} else {
			clothess = clothesService.getByCategoryAndName(categoryService.getById(1), "%" + name + "%");
		}
		for (Clothes clothes : clothess) {
			String tagString = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tagString + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@GetMapping("/accessorySearch")
	public String accessorySearch(@RequestParam(name = "name", required = false, defaultValue = "") String name,
			@RequestParam(name = "tag", required = true) Integer tag) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String data = "";
		List<Clothes> clothess = new ArrayList<>();
		if (tag != 0) {
			clothess = clothesService.getByCategoryAndTagAndName(categoryService.getById(3), tag, "%" + name + "%");
		} else {
			clothess = clothesService.getByCategoryAndName(categoryService.getById(3), "%" + name + "%");
		}
		for (Clothes clothes : clothess) {
			String tagString = clothes.getTag() == 1 ? "Sale" : (clothes.getTag() == 2 ? "New" : "Hot");
			data += "<div class=\"clothes col-md-6 col-lg-4 col-xl-3 mt-4 p-2\">\r\n"
					+ "					<div class=\"\">\r\n"
					+ "						<div class=\"img position-relative\">\r\n"
					+ "							<a href=\"/product?id=" + clothes.getId() + "\"><img\r\n"
					+ "								src=\"/imgUpload/" + clothes.getImage() + "\"\r\n"
					+ "								class=\"w-100 productImg\"></a> <span\r\n"
					+ "								class=\"position-absolute text-white d-flex align-items-center justify-content-center\">\r\n"
					+ "								" + tagString + "							</span>\r\n"
					+ "						</div>\r\n" + "						<div class=\"text-center\">\r\n"
					+ "							<div class=\"rating mt-3\">\r\n"
					+ "								<span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span> <span class=\"text-warning\"> <i class=\"fas fa-star\"></i>\r\n"
					+ "								</span>\r\n" + "							</div>\r\n"
					+ "							<p class=\"text-capitalize my-1\">" + clothes.getName() + "</p>\r\n"
					+ "							<p class=\"fw-bold\">\r\n"
					+ "								<fmt:formatNumber pattern=\"###,###,###\">"
					+ currencyVN.format(clothes.getPrice()) + "</fmt:formatNumber>\r\n"
					+ "								\r\n" + "							</p>\r\n"
					+ "						</div>\r\n" + "					</div>\r\n" + "				</div>";
		}
		return data;
	}

	@PostMapping("/login")
	public boolean login(HttpServletRequest request,HttpServletResponse response
			, @RequestParam(name = "email") String email,
			@RequestParam(name = "pass") String pass, @RequestParam(name = "save") boolean save) {
		User user = userService.getByEmail(email);
		int hour = save == false ? 0 : 30 * 24;
		if (user == null) {
			return false;
		} else {
			if (EncryptUtil.check(pass, user.getPassword())) {
				if (user.getStatus() == 1) {
					return false;
				}else {
					request.getSession().setAttribute("user", user);
					request.getSession().setAttribute("role", user.getRole());
					CookieUtil.add("email", email, hour, response);
					CookieUtil.add("pass", pass, hour, response);
					CookieUtil.add("save", "save", hour, response);
					return true;
				}
			} else {
				return false;
			}
		}
	}
}
