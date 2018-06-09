package com.pcy.ssc.variable;

public class BetCode {
	public static final String Q1_MAX="newip_1-1005";
	public static final String Q1_MIN="newip_1-1006";
	public static final String Q2_MAX="newip_2-1005";
	public static final String Q2_MIN="newip_2-1006";
	public static final String Q3_MAX="newip_3-1005";
	public static final String Q3_MIN="newip_3-1006";
	public static final String Q4_MAX="newip_4-1005";
	public static final String Q4_MIN="newip_4-1006";
	public static final String Q5_MAX="newip_5-1006";
	public static final String Q5_MIN="newip_5-1005";
	//单双
	public static final String Q1_DAN="newip_1-1007";
	public static final String Q1_SHUANG="newip_1-1008";
	//总大小
	public static final String TOTAL_MAX="newip_1109";
	public static final String TOTAL_MIN="newip_1110";

	public static final String[] BET_CODE_ARR = {
			Q1_DAN,
			Q1_MAX,
			Q1_MIN,
			Q1_SHUANG,
			Q2_MAX,
			Q2_MIN,
			Q3_MAX,
			Q3_MIN,
			Q4_MAX,
			Q4_MIN,
			Q5_MAX,
			Q5_MIN,
			TOTAL_MAX,
			TOTAL_MIN
		};
	
	public static void main(String[] args) {
		int k=1;
		for(int i=0;i<10;i++){
			
			System.out.println(k);
			k=(int) (k*2.3);
		}
	}
}
