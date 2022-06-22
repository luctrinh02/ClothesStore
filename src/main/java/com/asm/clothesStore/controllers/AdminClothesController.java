package com.asm.clothesStore.controllers;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.asm.clothesStore.dto.CLothesDTO;
import com.asm.clothesStore.entities.Cart;
import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.CartService;
import com.asm.clothesStore.services.CategoryService;
import com.asm.clothesStore.services.ClothesService;

@Controller
@RequestMapping("/admin/clothes")
public class AdminClothesController {
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private ServletContext app;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CartService cartService;
	private String _name = "";

	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "page", required = true, defaultValue = "0") int page) {
		_name = name;
		Page<Clothes> list = clothesService.getByName(_name, page);
		model.addAttribute("list", list);
		model.addAttribute("name", _name);
		model.addAttribute("view", "/WEB-INF/admin/products/index.jsp");
		return "layout";
	}

	@GetMapping("/page")
	public String index(Model model, @RequestParam(name = "page", required = true, defaultValue = "0") int page) {
		Page<Clothes> list = clothesService.getByName(_name, page);
		model.addAttribute("list", list);
		model.addAttribute("name", _name);
		model.addAttribute("view", "/WEB-INF/admin/products/index.jsp");
		return "layout";
	}
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Integer id) {
		Clothes clothes=clothesService.getById(id);
		clothes.setStatus(1);
		clothesService.add(clothes);
		return "redirect:index";
	}
	@GetMapping("/restore")
	public String restore(@RequestParam(name = "id") Integer id) {
		Clothes clothes=clothesService.getById(id);
		clothes.setStatus(0);
		clothesService.add(clothes);
		return "redirect:index";
	}
	@GetMapping("/create")
	public String create(Model model) {
		List<Category> listCategory=categoryService.getAll();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("view", "/WEB-INF/admin/products/create.jsp");
		return "layout";
	}
	@GetMapping("/edit")
	public String edit(Model model,@RequestParam("id") Integer id) {
		Clothes clothes=clothesService.getById(id);
		List<Category> listCategory=categoryService.getAll();
		model.addAttribute("listCategory", listCategory);
		model.addAttribute("product", clothes);
		model.addAttribute("view", "/WEB-INF/admin/products/edit.jsp");
		return "layout";
	}
	@PostMapping("/store")
	public String store(@Valid CLothesDTO cLothesDTO,BindingResult result,@RequestParam(name = "img") MultipartFile img,
			HttpServletRequest request) throws IllegalStateException, IOException {
		if(result.hasErrors()) {
			List<ObjectError> errors=result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
			return "redirect:create";
		}else {
			Clothes clothes=new Clothes();
			clothes.setAmount(Integer.parseInt(cLothesDTO.getAmount()));
			clothes.setCategory(cLothesDTO.getCategory());
			clothes.setColor(cLothesDTO.getColor());
			clothes.setDescription(cLothesDTO.getDescription());
			clothes.setDiscount(Integer.parseInt(cLothesDTO.getDiscount()));
			clothes.setName(cLothesDTO.getName());
			clothes.setPrice(new BigDecimal(cLothesDTO.getPrice()));
			clothes.setSize(Integer.parseInt(cLothesDTO.getSize()));
			clothes.setCreatedDate(new Date());
			clothes.setLastChangedDate(new Date());
			clothes.setStatus(0);
			clothes.setTag(cLothesDTO.getTag());
			User user=(User) request.getSession().getAttribute("user");
			clothes.setUser(user);
			if(!img.isEmpty()) {
				String fileName=img.getOriginalFilename();
				File file=new File(app.getRealPath("/imgUpload/"+fileName));
				img.transferTo(file);
				clothes.setImage(fileName);
			}
			clothesService.add(clothes);
			return "redirect:index";
		}
	}
	@PostMapping("/update")
	public String update(@Valid CLothesDTO cLothesDTO,BindingResult result,@RequestParam(name = "imgEdit") MultipartFile imgEdit
			,HttpServletRequest request) throws IllegalStateException, IOException {
		if(result.hasErrors()) {
			List<ObjectError> errors=result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
			return "redirect:edit?"+cLothesDTO.getId();
		}else {
			Clothes clothes=clothesService.getById(cLothesDTO.getId());
			User user=(User) request.getSession().getAttribute("user");
			clothes.setAmount(Integer.parseInt(cLothesDTO.getAmount()));
			clothes.setCategory(cLothesDTO.getCategory());
			clothes.setColor(cLothesDTO.getColor());
			clothes.setDescription(cLothesDTO.getDescription());
			clothes.setDiscount(Integer.parseInt(cLothesDTO.getDiscount()));
			clothes.setName(cLothesDTO.getName());
			clothes.setPrice(new BigDecimal(cLothesDTO.getPrice()));
			clothes.setSize(Integer.parseInt(cLothesDTO.getSize()));
			clothes.setTag(cLothesDTO.getTag());
			clothes.setUser(user);
			clothes.setLastChangedDate(new Date());
			clothes.setStatus(0);
			clothes.setCreatedDate(clothes.getCreatedDate());
			if(!imgEdit.isEmpty()) {
				String fileName=imgEdit.getOriginalFilename();
				File file=new File(app.getRealPath("/imgUpload/"+fileName));
				imgEdit.transferTo(file);
				clothes.setImage(fileName);
			}else {
				clothes.setImage(clothes.getImage());
			}
			clothes=clothesService.add(clothes);
			List<Cart> carts=cartService.getByClothes(clothes);
			for(Cart x:carts) {
				x.setPrice(new BigDecimal(clothes.getPrice().intValue() * (100 - clothes.getDiscount()) / 100));
				cartService.add(x);
			}
			return "redirect:index";
		}
	}
}
