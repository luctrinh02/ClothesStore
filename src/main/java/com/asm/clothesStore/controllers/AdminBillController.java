package com.asm.clothesStore.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.Detailbill;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.BillService;
import com.asm.clothesStore.services.DetailBillService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.services.UserService;

@Controller
@RequestMapping("/admin/bills")
public class AdminBillController {
	@Autowired
	private BillService billService;
	@Autowired
	private UserService userService;
	@Autowired
	private DetailBillService detailBillService;
	@Autowired
	private ClothesService clothesService;
	@GetMapping("/history")
	public String history(Model model,HttpServletRequest request,@RequestParam(name = "id") Integer id,
			@RequestParam(name = "page",defaultValue = "0") Integer page) {
		User user=userService.getById(id);
		Page<Bill> list=billService.getByUser(user,page);
		model.addAttribute("list", list);
		model.addAttribute("id", id);
		model.addAttribute("fullname", user.getFullname());
		model.addAttribute("view", "/WEB-INF/admin/bills/history.jsp");
		return "layout";
	}
	@GetMapping("/detail")
	public String detail(Model model,@RequestParam(name = "id") Integer id) {
		model.addAttribute("isIndex", 0);
		Bill bill=billService.getById(id);
		List<Detailbill> list=detailBillService.getByBill(bill);
		model.addAttribute("id", bill.getUser().getId());
		model.addAttribute("list", list);
		model.addAttribute("total", bill.getTotalMoney());
		model.addAttribute("status", bill.getStatus());
		model.addAttribute("view", "/WEB-INF/admin/bills/detail.jsp");
		return "layout";
	}
	@GetMapping("/more")
	public String more(Model model,@RequestParam(name = "id") Integer id) {
		model.addAttribute("isIndex", 1);
		Bill bill=billService.getById(id);
		List<Detailbill> list=detailBillService.getByBill(bill);
		model.addAttribute("list", list);
		model.addAttribute("total", bill.getTotalMoney());
		model.addAttribute("status", bill.getStatus());
		model.addAttribute("view", "/WEB-INF/admin/bills/detail.jsp");
		return "layout";
	}
	@GetMapping("/cancel")
	public String cancel(Model model,@RequestParam(name = "idBill") Integer id,HttpServletRequest request) {
		Bill bill=billService.getById(id);
		changeStatus(model, bill, 2, request);
		return "redirect:history?id="+bill.getUserId();
	}
	@GetMapping("/success")
	public String success(Model model,@RequestParam(name = "idBill") Integer id,HttpServletRequest request) {
		Bill bill=billService.getById(id);
		changeStatus(model, bill, 1, request);
		return "redirect:history?id="+bill.getUserId();
	}
	@GetMapping("/CancelIndex")
	public String cancelIndex(Model model,@RequestParam(name = "idBill") Integer id,HttpServletRequest request) {
		Bill bill=billService.getById(id);
		changeStatus(model, bill, 2, request);
		return "redirect:index";
	}
	@GetMapping("/SuccessIndex")
	public String successIndex(Model model,@RequestParam(name = "idBill") Integer id,HttpServletRequest request) {
		Bill bill=billService.getById(id);
		changeStatus(model, bill, 1, request);
		return "redirect:index";
	}
	@GetMapping("/index")
	public String index(Model model,HttpServletRequest request,@RequestParam(name = "page",defaultValue = "0") Integer page) {
		User admin=(User) request.getSession().getAttribute("user");
		Page<Bill> list=billService.getAll(page);
		model.addAttribute("list", list);
		model.addAttribute("fullname", admin.getFullname());
		model.addAttribute("view", "/WEB-INF/admin/bills/index.jsp");
		return "layout";
	}
	public void changeStatus(Model model,Bill bill,Integer status,HttpServletRequest request) {
		System.out.println(bill.getStatus());
		if(bill.getStatus()==0) {
			bill.setStatus(status);
			billService.add(bill);
			if(status==2) {
				List<Detailbill> list=detailBillService.getByBill(bill);
				for(Detailbill x:list) {
					Clothes p=x.getClothes();
					p.setAmount(p.getAmount()+x.getAmount());
					clothesService.add(p);
				}
			}
		}else {
			String cancel="Đơn này đã bị huỷ!";
			String access="Đơn này đã được xác nhận";
			String error=bill.getStatus()==1?access:cancel;
			request.getSession().setAttribute("error", error);
		}
	}
}
