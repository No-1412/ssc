package com.pcy.ssc.variable;

public class SystemVariable {
	public static String uppid;//上期彩票期数
	public static String currentPid;//上期彩票期数
	public static String betPid;//下注期数
	public static Integer betMoney;//下注金额
	public static String upopcode;//上期开奖号码
	public static String money;//当前金额
	public static String uptodaywin = "-9999";//上次今日输赢
	public static String todaywin;//今日输赢
	public static String endtime;//最后开奖时间
	public static Long uplogintime;//最后开奖时间
	public static Integer totaldx;//总和大小
	

	
	
	public static void print() {
		System.out.println("**************************************第"+ currentPid +"期--倒计时 ："+ endtime +" **************************************");
		System.out.print("** [上期期数:" + uppid + "] ");
		System.out.println(" [上期号码:" + upopcode + "] ");
		System.out.print("** [下注期数:" + betPid + "] ");
		System.out.println(" [下注金额:" + betMoney + "] ");
		System.out.println("** [今日输赢:" + todaywin + "] ");
		System.out.println("*************************************************************************************************");
	}


	public static void printCurrent() {
		System.out.println("--------------------------第"+ currentPid +"期--倒计时 ："+ endtime +"--今日输赢 " + todaywin + " -------------------------");
	}
	
	
}
