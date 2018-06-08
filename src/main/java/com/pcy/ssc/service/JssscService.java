package com.pcy.ssc.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.pcy.ssc.utils.HttpClientUtil;
import com.pcy.ssc.variable.BetCode;
import com.pcy.ssc.variable.CookieVariable;
import com.pcy.ssc.variable.HttpClientResult;
import com.pcy.ssc.variable.SystemVariable;

@Service
public class JssscService {
	@Autowired
	private LoginService loginService;
	@Autowired
	private CqsscBetService betService;
	
	
	
	private final static Map<String, String> param;
	private String lastResultUrl = "https://pk10.xm9900.com/gamessc/lastresult.winer";//最后一次开奖结果
	private String getRoundUrl = "https://pk10.xm9900.com/gamessc/getround.winer";//当前期数信息
	private String leftinfoUrl = "https://pk10.xm9900.com/main/leftinfo?game_code=207.winer";//当前金额

	private static int num;
	private static int kjnum;
	private final static Integer[] betMoney= {5,15,45};
	static {
		param = new HashMap<>();
		param.put("game_code", "207");
	}

	public void jsssc() {
		int randNum = randomNum();
		//if(CookieVariable.win_session == null) {
			loginService.login("cypeng", "a5932439");
		//}
		getLastResult();
		getRound();
		//getMoney();
		
		if(SystemVariable.betPid == null || !SystemVariable.betPid.equals(SystemVariable.currentPid)) {
			
			String betCode = BetCode.TOTAL_MAX;
			if(randNum < 5) {
				betCode = BetCode.TOTAL_MIN;
			}
			float uptodaywin = Float.parseFloat(SystemVariable.uptodaywin);
			float todaywin = Float.parseFloat(SystemVariable.todaywin);
			if(Math.abs(uptodaywin) >= 1  && uptodaywin > todaywin){
				
				if(num < betMoney.length - 1) {
					num ++;
				}else {
					num = 0;
				}
				System.out.println("连续输了" + num +"次");
			}else if(uptodaywin == todaywin) {
				if(kjnum++ > 3) {
					SystemVariable.uptodaywin ="0";
					kjnum = 0;
				}
				System.out.println("开奖中。。。");
				return;
			}else {
				num = 0;
				System.out.println("赢了");
			}
			SystemVariable.betPid = SystemVariable.currentPid;
			SystemVariable.uptodaywin = SystemVariable.todaywin;
			SystemVariable.betMoney = betMoney[num];
			
			System.out.println("下注--" + betCode);
			betService.postbet(betCode, SystemVariable.betMoney.toString());
			SystemVariable.print();
		}
		
		SystemVariable.printCurrent();
		
	}

	/**
	 * 获取最后一次开奖结果
	 */
	public void getLastResult() {
		HttpClientResult result = HttpClientUtil.doPost(param, lastResultUrl);

		JSONObject jo = JSONObject.parseObject(result.getJsonResult());
		// 设置上期开奖结果
		SystemVariable.uppid = jo.getString("round");
		SystemVariable.upopcode = jo.getString("number");
		SystemVariable.todaywin = jo.getString("todaywin");

	}

	/**
	 * 获取当前期数，开奖倒计时
	 */
	public void getRound() {
		HttpClientResult result = HttpClientUtil.doPost(param, getRoundUrl);
		JSONObject jo = JSONObject.parseObject(result.getJsonResult());
		// 设置上期开奖结果
		SystemVariable.currentPid = jo.getString("round");
		SystemVariable.endtime = jo.getString("endtime");
	}
	/**
	 * 当前金额
	 */
	public void getMoney() {
		HttpClientResult result = HttpClientUtil.doPost(null, leftinfoUrl);
		JSONObject jo = JSONObject.parseObject(result.getJsonResult());
		// 设置上期开奖结果
		SystemVariable.money = jo.getString("money");
	}
	

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public int randomNum() {
		Random r = new Random();
		int randomNum = r.nextInt(10);
		return randomNum;
	}

}
