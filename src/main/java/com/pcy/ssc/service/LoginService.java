package com.pcy.ssc.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.cookie.Cookie;
import org.springframework.stereotype.Service;

import com.pcy.ssc.utils.HttpClientUtil;

@Service
public class LoginService {
	private final static  String  LOGIN_URL = "https://pk10.xm9900.com/login/login_ok.winer";
	
	/**
	 * 重庆时时彩登陆
	 * @param loginName
	 * @param loginPwd
	 * @return
	 */
	public boolean login(String loginName,String loginPwd){
		Map<String, String> map=new HashMap<String, String>();
		map.put("loginName", loginName);
		map.put("loginPwd", loginPwd);
		map.put("ValidateCode", "验证码");
		map.put("Submit", "登陆");
		List<Cookie> cookies = HttpClientUtil.doPost(map, LOGIN_URL).getCookies();
		
		if(cookies==null|| cookies.isEmpty()){
			System.out.println("登陆失败,账号或密码错误！");
			return false;
		}else{
			System.out.println("登陆成功");
			return true;
		}
	}
	
	
	
}
