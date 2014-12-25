package test1;

import java.awt.Container;
import java.util.Map;

public class Algorithms {
	public static int math0[];          //十字路口
	public static int math1[];          //丁字路口
	
	public static void solve(TrafficGraph traffic, int time) {
		math0 = new int[4];
		//tmp为右拐
		int tmp = Constants.flowD2R | Constants.flowR2U | Constants.flowU2L | Constants.flowL2D; 
//		math0[0] = tmp | Constants.flowD2U | Constants.flowU2D;      //竖行
//		math0[1] = tmp | Constants.flowL2R | Constants.flowR2L;      //横行
//		math0[2] = tmp | Constants.flowD2L | Constants.flowU2R;      //竖左拐
//		math0[3] = tmp | Constants.flowL2U | Constants.flowR2D;      //横左拐
		math0[0] = Constants.flowD2U | Constants.flowD2L | Constants.flowD2R | Constants.flowU2R | Constants.flowU2D | Constants.flowU2L;      //竖行
		math0[1] = ~math0[0];      //竖行
		
		math1 = new int[3];
		//tmp为右拐
		tmp = Constants.flowD2R | Constants.flowL2D;
//		math1[0] = tmp | Constants.flowL2R | Constants.flowR2L;      //横行
//		math1[1] = tmp | Constants.flowD2L;                          //竖左拐
//		math1[2] = tmp | Constants.flowR2D;                          //横左拐
		math1[0] = Constants.flowD2R | Constants.flowD2L;
		math1[1] = ~math1[0];
//		int x = math1[0];
//		while (x != 0) {
//			System.out.printf("%d", x % 2);
//			x /= 2;
//		}
		Map<String, TrafficCrossroad> gg = traffic.crosses;
		for (String cid: gg.keySet()) {
			if (gg.get(cid).type == 0) traffic.setLight(cid, math0[time % 2], time);
			else traffic.setLight(cid, math1[time % 2], time);
		}
		//System.out.println("done");
	}
}
