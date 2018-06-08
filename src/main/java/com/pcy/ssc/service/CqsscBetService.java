package com.pcy.ssc.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcy.ssc.utils.HttpClientUtil;
import com.pcy.ssc.utils.JsonUtils;
import com.pcy.ssc.variable.CookieVariable;
import com.pcy.ssc.variable.SystemVariable;
/**
 * 重庆时时彩
 * --下注
 * @author 彭长云
 *
 */
@Service
public class CqsscBetService {
	@Autowired
	private LoginService loginService;
	
	private final static  String  AUTOTOU_URL ="https://pk10.xm9900.com/bill/postbet_jsssc.winer";
	private final static  String  SECKEY_URL="https://pk10.xm9900.com/gamessc/getseckey.winer";
	
	
	
	/**
	 * 下注
	 * @param buycode 下注编号
	 * @param betMoney
	 */
	public boolean postbet(String betcode,String betMoney){
		//如果未登陆，调用登陆方法
		if(CookieVariable.win_session==null){
			loginService.login("cypeng", "a5932439");
		}
		
			Map<String, String> seckeyMap=new HashMap<String, String>();
			seckeyMap.put("game_code", "207");
			seckeyMap.put("type", "0");
			
			//获取最新的key
			String seckey=null;
			try {
			 seckey=JsonUtils.get(HttpClientUtil.doPost(seckeyMap, SECKEY_URL).getJsonResult(),"newkey");
			} catch (Exception e) {
				e.printStackTrace();
				loginService.login("cypeng", "a5932439");
//				 seckey=JsonUtils.getString(HttpClientUtil.doPost(seckeyMap, SECKEY_URL).getJsonResult(),"newkey");
				 return false;
			}
			Map<String, String> paramterMap=new HashMap<String, String>();
			paramterMap.put(betcode, betMoney);//key:球号大小单双等，value投注金额;"newip_5-1006"--五球小
			paramterMap.put("seckey",seckey);
			paramterMap.put("game_code", "207");//游戏编码，2表示重庆时时彩
			paramterMap.put("typecode", "0");
			paramterMap.put("round", SystemVariable.currentPid);//投注的期数
			HttpClientUtil.doPost(paramterMap, AUTOTOU_URL);
			return true;
		
	}
	
}
