package com.asm.clothesStore.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.asm.clothesStore.dto.UserDTO;
import com.asm.clothesStore.entities.Category;
import com.asm.clothesStore.entities.Comment;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.CategoryService;
import com.asm.clothesStore.services.CommentService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.services.UserService;
import com.asm.clothesStore.uitilities.CookieUtil;
import com.asm.clothesStore.uitilities.EncryptUtil;

@Controller
public class HomeController {
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private UserService userService;
	@Autowired
	private CommentService commentService;
	@Autowired
	public JavaMailSender emailSender;

	@RequestMapping("/home")
	public String home(Model model) {
		List<Category> categories = categoryService.getAll();
		// get clothes
		List<Clothes> listClothes = clothesService.getByCategory(categories.get(0), 0);
		List<Clothes> listFootWear = clothesService.getByCategory(categories.get(1), 0);
		List<Clothes> listAccessory = clothesService.getByCategory(categories.get(2), 0);
		model.addAttribute("listClothes", listClothes);
		model.addAttribute("listFootWear", listFootWear);
		model.addAttribute("listAccessory", listAccessory);
		model.addAttribute("view", "/WEB-INF/guest/home.jsp");
		return "layout";
	}

	@GetMapping("/clothes")
	public String clothes(Model model, @RequestParam(name = "tag", required = false, defaultValue = "0") Integer tag) {
		List<Clothes> list = new ArrayList<>();
		switch (tag) {
		case 0:
			list = clothesService.getByCategory(categoryService.getById(1));
			break;
		default:
			list = clothesService.getByCategoryAndTag(categoryService.getById(1), tag);
			break;
		}
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/guest/clothes.jsp");
		return "layout";
	}

	@GetMapping("/footWear")
	public String footWear(Model model, @RequestParam(name = "tag", required = false, defaultValue = "0") Integer tag) {
		List<Clothes> list = new ArrayList<>();
		switch (tag) {
		case 0:
			list = clothesService.getByCategory(categoryService.getById(2));
			break;
		default:
			list = clothesService.getByCategoryAndTag(categoryService.getById(2), tag);
			break;
		}
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/guest/footWear.jsp");
		return "layout";
	}

	@GetMapping("/accessory")
	public String accessory(Model model,
			@RequestParam(name = "tag", required = false, defaultValue = "0") Integer tag) {
		List<Clothes> list = new ArrayList<>();
		switch (tag) {
		case 0:
			list = clothesService.getByCategory(categoryService.getById(3));
			break;
		default:
			list = clothesService.getByCategoryAndTag(categoryService.getById(3), tag);
			break;
		}
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/guest/accessory.jsp");
		return "layout";
	}

	@GetMapping("/product")
	public String detail(Model model, @RequestParam(name = "id", required = true) Integer id) {
		Clothes p = clothesService.getById(id);
		List<Comment> list = commentService.getByProduct(p);
		model.addAttribute("listComment", list);
		model.addAttribute("product", p);
		model.addAttribute("view", "/WEB-INF/guest/product.jsp");
		return "layout";
	}

	@GetMapping("/loginIndex")
	public String loginIndex(Model model, HttpServletRequest request) {
		String email = CookieUtil.read("email", request);
		String pass = CookieUtil.read("pass", request);
		String save = CookieUtil.read("save", request);
		model.addAttribute("save", save.equals("save") ? true : false);
		model.addAttribute("pass", pass);
		model.addAttribute("email", email);
		model.addAttribute("view", "/WEB-INF/guest/login.jsp");
		return "layout";
	}

	@GetMapping("/registryIndex")
	public String registryIndex(Model model) {
		model.addAttribute("view", "/WEB-INF/guest/registry.jsp");
		return "layout";
	}

	@PostMapping("/registry")
	public String registry(@Valid UserDTO userDTO, BindingResult result, HttpServletRequest request,
			@RequestParam(name = "confirm") String conf) {
		if (result.hasErrors()) {
			List<ObjectError> errors = result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
			return "redirect:registryIndex";
		} else {
			if (conf.equals("")) {
				request.getSession().setAttribute("error", "Chưa xác nhận mật khẩu");
				return "redirect:registryIndex";
			} else if (conf.equals(userDTO.getPassword())) {
				try {
					User user = new User();
					user.setAddress(userDTO.getAddress());
					user.setEmail(userDTO.getEmail());
					user.setFullname(userDTO.getFullname());
					user.setGender(userDTO.getGender());
					user.setRole(0);
					user.setStatus(0);
					user.setPassword(EncryptUtil.encrypt(user.getPassword()));
					userService.add(user);
					request.getSession().setAttribute("message", "Đăng ký thành công");
					return "redirect:loginIndex";
				} catch (Exception e) {
					request.getSession().setAttribute("error", "Trùng email");
					return "redirect:registryIndex";
				}
			} else {
				request.getSession().setAttribute("error", "Xác nhận không chính xác");
				return "redirect:registryIndex";
			}
		}
	}

	@GetMapping("/forgetPassword")
	public String forgetPassword(Model model) {
		model.addAttribute("view", "/WEB-INF/guest/forgetPassword.jsp");
		return "layout";
	}

	private String _email = null;
	private Integer _key = null;

	@GetMapping("/getKey")
	@ResponseBody
	public boolean getKey(@RequestParam("mail") String mail) {
		User user = userService.getByEmail(mail);
		System.out.println(mail);
		if (user == null) {
			return false;
		} else {
			SimpleMailMessage message = new SimpleMailMessage();

			_email = mail;
			message.setTo(_email);
			message.setSubject("Xác nhận đổi mật khẩu");
			int min = 1000;
			int max = 9999;
			_key = (int) ((Math.random()) * ((max - min) + 1)) + min;
			message.setText("Mã xác nhận là: " + _key);

			this.emailSender.send(message);
			return true;
		}
	}

	@PostMapping("/forgetPassword")
	public String forget(@RequestParam("key") Integer key, @RequestParam("newPass") String newPass,
			@RequestParam("confirm") String confirm, HttpServletRequest request) {
		System.out.println(key);
		System.out.println(_key);
		if (key.equals(_key)) {
			User user = userService.getByEmail(_email);
			if(newPass.length()<6) {
				request.getSession().setAttribute("error", "Chưa nhập password");
				return "redirect:forgetPassword";
			}else {
				if (confirm.equals(newPass)) {
					user.setPassword(EncryptUtil.encrypt(newPass));
					userService.add(user);
					_email = null;
					_key = null;
					request.getSession().setAttribute("error", "Đổi mật khẩu thành công");
					return "redirect:loginIndex";
				} else {
					request.getSession().setAttribute("error", "Mật khẩu không khớp");
					return "redirect:forgetPassword";
				}
			}
		} else {
			request.getSession().setAttribute("error", "Mã xác nhận sai");
			return "redirect:forgetPassword";
		}
	}
}
