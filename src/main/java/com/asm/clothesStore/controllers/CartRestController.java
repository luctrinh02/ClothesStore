package com.asm.clothesStore.controllers;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.asm.clothesStore.entities.Bill;
import com.asm.clothesStore.entities.CartPK;
import com.asm.clothesStore.entities.Detailbill;
import com.asm.clothesStore.entities.DetailbillPK;
import com.asm.clothesStore.entities.Clothes;
import com.asm.clothesStore.entities.User;
import com.asm.clothesStore.services.BillService;
import com.asm.clothesStore.services.CartService;
import com.asm.clothesStore.services.DetailBillService;
import com.asm.clothesStore.services.ClothesService;
import com.asm.clothesStore.uitilities.NumberWordConverter;

@RestController
public class CartRestController {
	@Autowired
	private CartService cartService;
	@Autowired
	private ClothesService clothesService;
	@Autowired
	private BillService billService;
	@Autowired
	private DetailBillService detailBillService;
	@GetMapping("/addToCart")
	public int addToCart(@RequestParam(name = "id",required = true) Integer id
			,@RequestParam(name = "amount",required = true) String amountString,
			HttpServletRequest request) {
		try {
			int amount= Integer.parseInt(amountString);
			Clothes clothes=clothesService.getById(id);
			User user=(User) request.getSession().getAttribute("user");
			return cartService.addToCart(user, clothes, amount);
		} catch (Exception e) {
			return 2;
		}
	}
	private Long total=(long) 0;
	private BigDecimal bigtotal =BigDecimal.ZERO;
	@GetMapping("/total")
	public String[] total(@RequestParam(name = "chk[]",required = false) Integer[] chk
			,@RequestParam(name = "amount[]",required = false,defaultValue = "0") Integer[] amount) {
		Locale localeVN = new Locale("vi", "VN");
		NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
		String[] mess=new String[2];
		int isNull=0;
		if(amount!=null) {
			for(Integer x:amount) {
				if(x!=null) isNull++;
			}
		}
		if(chk==null || isNull==0) {
			mess[0]="Không có sản phẩm nào";
			mess[1]="";
		}else {
			total=(long) 0;
			for(int i=0;i<chk.length;i++) {
				int a=amount[i]==null?0:amount[i];
				Clothes p=clothesService.getById(chk[i]);
				total+=(p.getPrice().intValue() * (100 - p.getDiscount()) / 100)*a;
				bigtotal=bigtotal.add((p.getPrice().multiply(new BigDecimal(100 - p.getDiscount()) ).multiply(new BigDecimal(a)).divide(new BigDecimal(100))));
			}
			if(total>0) {
				mess[0]="Bạn có chắc đặt đơn với giá trị "+currencyVN.format(total);
				mess[1]=NumberWordConverter.convert(total)+" đồng";
			}else {
				mess[0]="Bạn có chắc đặt đơn với giá trị "+bigtotal+" đ";
				mess[1]="";
			}
		}
		return mess;
	}
	@GetMapping("/order")
	public int order(@RequestParam(name = "chk[]",required = false) Integer[] chk
			,@RequestParam(name = "amount[]",required = false,defaultValue = "0") Integer[] amount,
			HttpServletRequest request) {
		//creating bill
		User user=(User) request.getSession().getAttribute("user");
		int check=1;
		for(int i=0;i<chk.length;i++) {
			if(chk[i]!=null && amount[i]!=null) {
				Clothes p=clothesService.getById(chk[i]);
				if(amount[i]>p.getAmount()) {
					check=0;
					return check;
				}
			}
		}
		Bill bill=new Bill();
		bill.setDate(new Date());
		bill.setStatus(0);
		bill.setTotalMoney(new BigDecimal(total));
		bill.setUser(user);
		bill.setUserId(user.getId());
		bill=billService.add(bill);
		List<Detailbill> list=new ArrayList<>();
		if(check==1) {
			for(int i=0;i<chk.length;i++) {
				Clothes p=clothesService.getById(chk[i]);
				Detailbill detailbill=new Detailbill();
				DetailbillPK detailbillPK=new DetailbillPK(bill.getId(), p.getId());
				detailbill.setBill(bill);
				detailbill.setAmount(amount[i]);
				detailbill.setClothes(p);
				detailbill.setId(detailbillPK);
				detailbill.setPrice(new BigDecimal(p.getPrice().intValue() * (100 - p.getDiscount()) / 100));
				CartPK cartPK=new CartPK(bill.getUserId(), p.getId());
				cartService.removeProductCard(cartPK);
				p.setAmount(p.getAmount()-amount[i]);
				clothesService.add(p);
				detailBillService.add(detailbill);
				list.add(detailbill);
			}
			bill.setDetailbills(list);
			billService.add(bill);
		}
		return check;
	}
	@GetMapping("/deleteCart")
	public int deleteCart(@RequestParam(name = "chk[]",required = false) Integer[] chk,
			HttpServletRequest request) {
		int check=1;
		User user=(User) request.getSession().getAttribute("user");
		if(chk==null) {
			check=0 ;
		}else {
			for(int i=0;i<chk.length;i++) {
				Clothes p=clothesService.getById(chk[i]);
				CartPK cartPK=new CartPK(user.getId(), p.getId());
				cartService.removeProductCard(cartPK);
			}
		}
		return check;
	}
}
