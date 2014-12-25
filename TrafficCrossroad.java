package test1;

import java.util.ArrayList;
import java.util.List;

/***
 * 
 * 交通十字路口
 *
 */
public class TrafficCrossroad {
	
	//      1
	//	0	x	2       2   0	3
	// 		3				1
	
	public String id; // 路口id
	public String[] neighbours; // 相邻的路口,顺序为下左右上
	public int type;  //0： 十字路口  1: 丁字路口
	
	public int[] currentFlow; // 当前流量
	public int currentTime; // 当前时间
	public int[][] flowHistory; // 历史流量
	public int[] lightSettingHistory; // 历史设定状态
	public int[][] flowAdd; // 每个时间段突然出现的流量（预估）

	public void setNeightbours(String down, String left, String right, String up) {
		this.neighbours = new String[4];
		neighbours[0] = down;
		neighbours[1] = left;
		neighbours[2] = right;
		neighbours[3] = up;
	}

	public TrafficCrossroad(String id) {
		this.id = id;

		this.flowAdd = new int[Constants.MAX_TIME][];
		for (int i = 0; i < flowAdd.length; i++) {
			flowAdd[i] = new int[4];
		}
		this.flowHistory = new int[Constants.MAX_TIME][];
		for (int i = 0; i < flowHistory.length; i++) {
			flowHistory[i] = new int[4];
		}

		this.currentFlow = new int[4];                   //源代码可能没用到就没初始化
		this.lightSettingHistory = new int[Constants.MAX_TIME + 1];
	}

	public void setLight(int setting, int time) {
		this.lightSettingHistory[time] = setting;
	}
	
	public int getInt(String str) {
		for (int i = 0; i < 4; i++) {
			if (this.neighbours[i].compareTo(str) == 0) return i;
		}
		return -1;
	}
}
