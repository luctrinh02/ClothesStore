package com.asm.clothesStore.controllers;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.Detailbill;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.BillService;
import com.asm.clothesStore.services.DetailBillService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.services.UserService;
import com.asm.clothesStore.uitilities.CookieUtil;
import com.asm.clothesStore.uitilities.EncryptUtil;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private BillService billService;
	@Autowired
	private DetailBillService detailBillService;
	@Autowired
	private UserService userService;
	@Autowired
	private ClothesService clothesService;

	@GetMapping("/detail")
	public String detail(@RequestParam(name = "idBill", required = true) Integer idBill) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yyyy");
		Bill bill = billService.getById(idBill);
		String data = "<a href=\"/user/history\"\r\n"
				+ "	class=\"btn btn-primary\"> <span class=\"fas fa-backward\"></span> Trở\r\n" + "	lại\r\n" + "</a>"
				+ "<div class=\"card mt-2 mb-3\">\r\n" + "		<h5 class=\"card-header\">\r\n"
				+ "			Ngày mua:\r\n" + fm.format(bill.getDate()) + "		</h5>\r\n"
				+ "		<div class=\"card-body\">\r\n" + "			<table class=\"table table-hover\">\r\n"
				+ "				<thead>\r\n" + "					<th>Tên sản phẩm</th>\r\n"
				+ "					<th>Màu sắc</th>\r\n" + "					<th>Kích thước</th>\r\n"
				+ "					<th>Số lượng</th>\r\n" + "					<th>Đơn giá</th>\r\n"
				+ "				</thead>\r\n" + "				<tbody>";
		List<Detailbill> list = detailBillService.getByBill(bill);
		for (Detailbill x : list) {
			data += "<tr class=\"align-middle\">\r\n" + "							<td>" + x.getClothes().getName()
					+ "</td>\r\n" + "							<td>" + x.getClothes().getColor() + "\r\n"
					+ "							<td>" + getSize(x.getClothes().getSize())
					+ "								</td>\r\n" + "							<td>" + x.getAmount()
					+ "</td>\r\n" + "							<td>"
					+ currencyVN
							.format(x.getClothes().getPrice().intValue() * (100 - x.getClothes().getDiscount()) / 100)
					+ "								</td>\r\n" + "						</tr>";
		}
		String status = bill.getStatus() == 0 ? "Đang xử lý" : (bill.getStatus() == 1 ? "Đã chấp nhận" : "Đã huỷ");
		data += "</tbody>\r\n" + "				<tfoot>\r\n" + "					<tr>\r\n"
				+ "						<td colspan=\"5\">Trạng thái : " + status
				+ "							</td>\r\n" + "					</tr>\r\n" + "				</tfoot>\r\n"
				+ "			</table>\r\n" + "		</div>\r\n" + "		<div class=\"card-footer\">\r\n"
				+ "			Tổng tiền:\r\n" + currencyVN.format(bill.getTotalMoney()) + "			\r\n"
				+ "		</div>\r\n" + "	</div>";
		return data;
	}

	public String getSize(int key) {
		String size = "";
		switch (key) {
		case 1:
			size = "XS";
			break;
		case 2:
			size = "S";
			break;
		case 3:
			size = "M";
			break;
		case 4:
			size = "L";
			break;
		case 5:
			size = "XL";
			break;
		case 6:
			size = "XXL";
			break;
		case 7:
			size = "XXL";
			break;

		}
		return size;
	}

	@GetMapping("/cancel")
	public boolean cancel(@RequestParam(name = "id") Integer id, HttpServletRequest request) {
		Bill bill = billService.getById(id);
		if (bill.getStatus() == 0) {
			bill.setStatus(2);
			billService.add(bill);
			List<Detailbill> list = detailBillService.getByBill(bill);
			for (Detailbill x : list) {
				Clothes p = x.getClothes();
				p.setAmount(p.getAmount() + x.getAmount());
				clothesService.add(p);
			}
		} else {
			String cancel = "Đơn này đã bị huỷ!";
			String access = "Đơn này đã được xác nhận";
			String error = bill.getStatus() == 1 ? access : cancel;
			request.getSession().setAttribute("error", error);
		}
		return true;
	}

	@GetMapping("/changePassword")
	public int changePass(@RequestParam(name = "old") String old, @RequestParam(name = "new") String newP,
			@RequestParam(name = "conf") String conf, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		if (EncryptUtil.check(old, user.getPassword())) {
			if (conf.equals(newP)) {
				user.setPassword(EncryptUtil.encrypt(newP));
				userService.add(user);
				request.getSession().invalidate();
				return 0;
			} else {
				return 2;
			}
		} else {
			return 1;
		}
	}
}
