package test1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Set;

public class Main {
	
	public static String Process(String line, int time, TrafficGraph traffic) {
		traffic.setFlowAll(line, time);
		Algorithms.solve(traffic, time);

		return traffic.getStatusAll(time);
	}
	
	public static void main(String[] args) throws IOException {
		
		// initialize
		TrafficGraph traffic = new TrafficGraph();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				Main.class.getResourceAsStream(Constants.FILENAME_TRAFFIC)));
		BufferedReader readerFlow = new BufferedReader(new InputStreamReader(
				Main.class.getResourceAsStream(Constants.FILENAME_FLOW_ADD)));
		// 读入红绿灯的结构图
		traffic.load(reader);
		// 读入每个时刻突然出现的流量
		traffic.loadFlowAdd(readerFlow);
		reader.close();
		readerFlow.close();
//		Map<String, TrafficCrossroad> gg = traffic.crosses;
//		for (String cid: traffic.crosses.keySet()) {
//			traffic.setLight(cid, 1, 0);
//			System.out.println(cid + " " + gg.get(cid).lightSettingHistory[0]);
//			System.out.println(traffic.getStatue(cid, 0));
//		}
		System.out.println("init input over");
		
		// main process
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String flows_str = br.readLine();
		int time = 0;
		while (!"end".equalsIgnoreCase(flows_str)) {
			// TODO 你的代码,注意，数据输出需保证一行输出，除了数据结果，请不要将任何无关数据或异常打印输出
			System.out.println(Process(flows_str, time, traffic));
			//System.out.println("Algorithm over");
			// 获取下一个时间段的流量
			flows_str = br.readLine();
			time++;
		}
	}
}
