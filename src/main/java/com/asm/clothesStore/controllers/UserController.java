package com.asm.clothesStore.controllers;

import java.util.List;

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

import com.asm.clothesStore.dto.UserDTO;
import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.Cart;
import com.asm.clothesStore.entities.Comment;
import com.asm.clothesStore.entities.CommentPK;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.BillService;
import com.asm.clothesStore.services.CartService;
import com.asm.clothesStore.services.CommentService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.services.UserService;

@Controller
@RequestMapping("user")
public class UserController {
	@Autowired
	private CartService cartService;
	@Autowired
	private BillService billService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	private ClothesService clothesService;
	@GetMapping("/logout")
	public String logout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "redirect:/home";
	}
	@GetMapping("/cart")
	public String cart(Model model,HttpServletRequest request) {
		User user=(User) request.getSession().getAttribute("user");
		List<Cart> list=cartService.getByUser(user);
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/user/cart.jsp");
		return "layout";
	}
	@GetMapping("/history")
	public String history(Model model,HttpServletRequest request,@RequestParam(name = "page",defaultValue = "0") Integer page) {
		User user=(User) request.getSession().getAttribute("user");
		Page<Bill> list=billService.getByUser(user,page);
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/user/history.jsp");
		return "layout";
	}
	@GetMapping("/changePasswordIndex")
	public String changepass(Model model) {
		model.addAttribute("view", "/WEB-INF/user/changePassword.jsp");
		return "layout";
	}
	@GetMapping("/profile")
	public String profile(Model model,HttpServletRequest request) {
		User user=(User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		model.addAttribute("view", "/WEB-INF/user/profile.jsp");
		return "layout";
	}
	@PostMapping("/update")
	public String update(HttpServletRequest request,@Valid UserDTO userDTO,BindingResult result) {
		if(result.hasErrors()) {
			List<ObjectError> errors=result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
		}else {
			try {
				User user=(User) request.getSession().getAttribute("user");
				user.setAddress(userDTO.getAddress());
				user.setEmail(userDTO.getEmail());
				user.setFullname(userDTO.getFullname());
				user.setGender(userDTO.getGender());
				user.setRole(userDTO.getRole());
				userService.add(user);
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("message", "Thay đổi hoàn tất");
			} catch (Exception e) {
				request.getSession().setAttribute("error", "Trùng lặp email");
			}
		}
		return "redirect:profile";
	}
	@PostMapping("/comment")
	public String comment(String content,Integer rate,Integer id,HttpServletRequest request) {
		User user=(User) request.getSession().getAttribute("user");
		Comment comment=new Comment();
		CommentPK commentPK=new CommentPK(user.getId(), id);
		Clothes clothes=clothesService.getById(id);
		comment.setContent(content);
		comment.setId(commentPK);
		comment.setClothes(clothes);
		comment.setRate(rate);
		comment.setUser(user);
		commentService.add(comment);
		return "redirect:/product?id="+id;
	}
}
