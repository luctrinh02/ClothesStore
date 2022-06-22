package com.asm.clothesStore.uitilities;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	//tạo vào gửi cookie về client để lưu
	public static Cookie add(String name,String value,int hours,HttpServletResponse response) {
		Cookie cookie=new Cookie(name, value);
		cookie.setMaxAge(hours*60*60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
	//đọc cookie gửi từ client
	public static String read(String name,HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for(Cookie cookie:cookies) {
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
	
}

