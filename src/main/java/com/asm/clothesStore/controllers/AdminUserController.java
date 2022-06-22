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
import com.asm.clothesStore.dto.UserUpdateDTO;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.UserService;
import com.asm.clothesStore.uitilities.EncryptUtil;

@Controller
@RequestMapping("/admin/users")
public class AdminUserController {
	@Autowired
	private UserService userService;
	private String _name = "";
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name = "name", defaultValue = "", required = false) String name,
			@RequestParam(name = "page", required = true, defaultValue = "0") int page) {
		_name=name;
		Page<User> list=userService.getPage(_name,page);
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/admin/users/index.jsp");
		return "layout";
	}
	@GetMapping("/page")
	public String index(Model model, @RequestParam(name = "page", required = true, defaultValue = "0") int page) {
		Page<User> list=userService.getPage(_name,page);
		model.addAttribute("name", _name);
		model.addAttribute("list", list);
		model.addAttribute("view", "/WEB-INF/admin/users/index.jsp");
		return "layout";
	}
	@GetMapping("/edit")
	public String edit(Model model,@RequestParam(name = "id") Integer id) {
		User user=userService.getById(id);
		model.addAttribute("user", user);
		model.addAttribute("view", "/WEB-INF/admin/users/edit.jsp");
		return "layout";
	}
	@GetMapping("/create")
	public String create(Model model) {
		model.addAttribute("view", "/WEB-INF/admin/users/create.jsp");
		return "layout";
	}
	@PostMapping("/store")
	public String store(@Valid UserDTO userDTO,BindingResult result,HttpServletRequest request) {
		if(result.hasErrors()) {
			List<ObjectError> errors=result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
			return "redirect:create";
		}else {
			try {
				User user=new User();
				user.setAddress(userDTO.getAddress());
				user.setEmail(userDTO.getEmail());
				user.setFullname(userDTO.getFullname());
				user.setGender(userDTO.getGender());
				user.setRole(userDTO.getRole());
				user.setStatus(0);
				user.setPassword(EncryptUtil.encrypt(user.getPassword()));
				userService.add(user);
				request.getSession().setAttribute("message", "Thêm thành công");
				return "redirect:index";
			} catch (Exception e) {
				request.getSession().setAttribute("error", "Trùng email");
				return "redirect:create";
			}
		}
	}
	@PostMapping("/update")
	public String update(@Valid UserUpdateDTO userDTO,BindingResult result,HttpServletRequest request) {
		if(result.hasErrors()) {
			List<ObjectError> errors=result.getAllErrors();
			request.getSession().setAttribute("error", errors.get(0).getDefaultMessage());
			return "redirect:edit?id="+userDTO.getId();
		}else {
			try {
				User u=userService.getById(userDTO.getId());
				u.setAddress(userDTO.getAddress());
				u.setEmail(userDTO.getEmail());
				u.setFullname(userDTO.getFullname());
				u.setGender(userDTO.getGender());
				u.setRole(userDTO.getRole());
				userService.add(u);
				request.getSession().setAttribute("message", "Sửa thành công");
				return "redirect:index";
			} catch (Exception e) {
				request.getSession().setAttribute("error", "Trùng lặp email");
				return "redirect:edit?id="+userDTO.getId();
			}
		}
	}
	@GetMapping("/delete")
	public String delete(@RequestParam(name = "id") Integer id,HttpServletRequest request) {
		User user=(User) request.getSession().getAttribute("user");
		if(user.getId()==id) {
			request.getSession().setAttribute("error", "Không thể vô hiệu bản thân");
		}else {
			User u=userService.getById(id);
			u.setStatus(1);
			userService.add(u);
			request.getSession().setAttribute("message", "Vô hiệu thành công");
		}
		return "redirect:index";
	}
	@GetMapping("/restore")
	public String restore(@RequestParam(name = "id") Integer id,HttpServletRequest request) {
		User u=userService.getById(id);
		u.setStatus(0);
		userService.add(u);
		request.getSession().setAttribute("message", "Khôi phục thành công");
		return "redirect:index";
	}
}
