package test1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/***
 * 
 * 交通结构图
 *
 */
public class TrafficGraph {
	public Map<String, TrafficCrossroad> crosses;
	
	public Map<int[], Integer> cross2Int0;          //帮助输出，十字路口
	public Map<int[], Integer> cross2Int1;          //帮助输出，丁字路口
	public Map<Integer, int[]> int2Cross;          //帮助输出
	
	public int sc0[][], sc1[][];
	
	public TrafficGraph() {
		crosses = new HashMap<String, TrafficCrossroad>();
		
		cross2Int0 = new HashMap<int[], Integer>();
		cross2Int1 = new HashMap<int[], Integer>();
		int2Cross = new HashMap<Integer, int[]>();
		int a[] = new int[2];
		
		//帮助输出，十字路口
		a[0] = 0; a[1] = 1;
		cross2Int0.put(a, Constants.flowD2L); int2Cross.put(Constants.flowD2L, a);
		a[0] = 0; a[1] = 2;
		cross2Int0.put(a, Constants.flowD2R); int2Cross.put(Constants.flowD2R, a);
		a[0] = 0; a[1] = 3;
		cross2Int0.put(a, Constants.flowD2U); int2Cross.put(Constants.flowD2U, a);
		
		a[0] = 1; a[1] = 3;
		cross2Int0.put(a, Constants.flowL2U); int2Cross.put(Constants.flowL2U, a);
		a[0] = 1; a[1] = 0;
		cross2Int0.put(a, Constants.flowL2D); int2Cross.put(Constants.flowL2D, a);
		a[0] = 1; a[1] = 2;
		cross2Int0.put(a, Constants.flowL2R); int2Cross.put(Constants.flowL2R, a);
		
		a[0] = 2; a[1] = 0;
		cross2Int0.put(a, Constants.flowR2D); int2Cross.put(Constants.flowR2D, a);
		a[0] = 2; a[1] = 3;
		cross2Int0.put(a, Constants.flowR2U); int2Cross.put(Constants.flowR2U, a);
		a[0] = 2; a[1] = 1;
		cross2Int0.put(a, Constants.flowR2L); int2Cross.put(Constants.flowR2L, a);
		
		a[0] = 3; a[1] = 2;
		cross2Int0.put(a, Constants.flowU2R); int2Cross.put(Constants.flowU2R, a);
		a[0] = 3; a[1] = 1;
		cross2Int0.put(a, Constants.flowU2L); int2Cross.put(Constants.flowU2L, a);
		a[0] = 3; a[1] = 0;
		cross2Int0.put(a, Constants.flowU2D); int2Cross.put(Constants.flowU2D, a);
		
		//帮助输出，丁字路口
		a[0] = 0; a[1] = 1;
		cross2Int1.put(a, Constants.flowD2L);
		a[0] = 0; a[1] = 2;
		cross2Int1.put(a, Constants.flowD2R);
		
		a[0] = 1; a[1] = 0;
		cross2Int1.put(a, Constants.flowL2D);
		a[0] = 1; a[1] = 2;
		cross2Int1.put(a, Constants.flowL2R);	
		
		a[0] = 2; a[1] = 0;
		cross2Int1.put(a, Constants.flowR2D);
		a[0] = 2; a[1] = 1;
		cross2Int1.put(a, Constants.flowR2L);	
		
		sc0 = new int[4][3];
		sc0[0][0] = 0; sc0[0][1] = 1; sc0[0][2] = 2; 
		sc0[1][0] = 3; sc0[1][1] = 4; sc0[1][2] = 5; 
		sc0[2][0] = 6; sc0[2][1] = 7; sc0[2][2] = 8; 
		sc0[3][0] = 9; sc0[3][1] = 10; sc0[3][2] = 11; 
		
		sc1 = new int[3][3];
		sc1[0][0] = 0; sc1[0][1] = 1;
		sc1[1][0] = 4; sc1[1][1] = 5;
		sc1[2][0] = 6; sc1[2][1] = 8;
	}
	
	/***
	 * 从reader中读入交通结构
	 * 
	 * @param reader
	 *            - 输入
	 * @throws IOException
	 */
	public void load(BufferedReader reader) throws IOException {
		//System.out.println("liangsheng");
		Set<String> has = new HashSet<String>();

		int i, flag;
		String line = "";

		while (line != null) {
			line = reader.readLine();
			if (line == null) {
				break;
			}
			line = line.trim();
			String[] parts = line.split(",");

			if (parts.length != 5) {
				System.out.println(line);
				reader.close();
				throw new RuntimeException("logic error" + "part's length:"
						+ parts.length + line);
			}
			if (has.contains(parts[0])) continue;
			flag = -1;
			for (i = 1; i < 5; i++) {
				if (parts[i].compareTo(Constants.LIGHT_NONE) == 0) flag = i;
			}
			if (flag != -1 && flag != 4) continue;
			has.add(parts[0]);
			String cid = parts[0];
			TrafficCrossroad cross = new TrafficCrossroad(cid);
			String down, left, right, up;
			down = parts[1];
			left = parts[2];
			right = parts[3];
			if (flag == -1) {               //十字路口
				cross.type = 0;
				up = parts[4];
			} else {                        //丁字路口
				cross.type = 1;
				up = Constants.LIGHT_NONE;
			}
			cross.setNeightbours(down, left, right, up);
			crosses.put(cid, cross);
			//System.out.println("cid= " + cid + " type= " + cross.type);
		}
		//System.out.println("over");
		reader.close();
	}
	
	public void load(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		this.load(reader);
	}
	
	/***
	 * 读入各个路口突然出现的流量
	 * 
	 * @param reader
	 *            - 输入
	 * @throws IOException
	 */
	public void loadFlowAdd(BufferedReader reader) throws IOException {
		String line = reader.readLine();

		while (line != null) {
			line = line.trim();
			String parts[] = line.split(",");
			String frmId = parts[1];
			String dstId = parts[0];
			TrafficCrossroad vertex = this.crosses.get(dstId);
			if (vertex != null) {
				String[] flows = Arrays.copyOfRange(parts, 2, parts.length);
				for (int i = 0; i < 4; i++) {
					if (vertex.neighbours[i].compareTo(frmId) == 0) {
						for (int j = 0; j < flows.length; j++) {
							vertex.flowAdd[j][i] = Integer.parseInt(flows[j]);
							//if (frmId .compareTo("tl40") == 0 && dstId.compareTo("tl41") == 0 && j < 10) 
							//	System.out.println(vertex.flowAdd[i][j]);
						}
					}
				}
			}
			line = reader.readLine();
		}
	}
	
	public void loadFlowAdd(String filename) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filename));
		this.loadFlowAdd(reader);
		reader.close();
	}
	
	/***
	 * 取得当前流量
	 * 
	 * @return
	 */
	public Map<String, float[]> getCurrentFlow() {
		Map<String, float[]> ret = new HashMap<String, float[]>();
		for (TrafficCrossroad cross : this.crosses.values()) {
			float[] f = new float[4];
			for (int i = 0; i < f.length; i++) {
				f[i] = (float) cross.currentFlow[i];
			}
			ret.put(cross.id, f);
		}
		return ret;
	}
	
	/***
	 * 取得第time时刻的流量
	 * 
	 * @param time
	 * @return
	 */
	public Map<String, int[]> getFlow(int time) {
		Map<String, int[]> ret = new HashMap<String, int[]>();
		for (TrafficCrossroad cross : this.crosses.values()) {
			int[] f = new int[4];
			for (int i = 0; i < f.length; i++) {
				f[i] = (int) cross.flowHistory[time][i];
			}
			ret.put(cross.id, f);
		}
		return ret;
	}
	
	/**
	 * 取得cid在time的红绿灯情况，并转换为字符数组
	 * @param cid
	 * @param time
	 * @return
	 */
	public String getStatue(String cid, int time) {
		ArrayList<String> records = new ArrayList<String>();
		TrafficCrossroad p = this.crosses.get(cid);
		int sta = p.lightSettingHistory[time];
		if (p.type == 0) {
			for (int i = 0; i < 4; i++) {
				String h = p.id + "," + p.neighbours[i] + "," + ((sta >> sc0[i][0]) & 1) + "," + 
						((sta >> sc0[i][1]) & 1) + "," + ((sta >> sc0[i][2]) & 1);
				records.add(h);
			}
		} else {
			String h0 = p.id + "," + p.neighbours[0] + "," + ((sta >> sc1[0][0]) & 1) + "," + 
				      ((sta >> sc1[0][1]) & 1) + ",-1";
			records.add(h0);
			String h1 = p.id + "," + p.neighbours[1] + ",-1," + ((sta >> sc1[1][0]) & 1) + "," + 
				      ((sta >> sc1[1][1]) & 1);
			records.add(h1);
			String h2 = p.id + "," + p.neighbours[2] + "," + ((sta >> sc1[2][0]) & 1) + ",-1," + 
				      ((sta >> sc1[2][1]) & 1);
			records.add(h2);
			//if (p.id.compareTo("tl42") == 0 && p.neighbours[1].compareTo("tl44") == 0) System.out.println("FUCK " + h1);
		}
		String statueString = Utils.join(";", records);
		return statueString;
	}
	
	/**
	 * 获取全部红绿灯状态，用于输出答案
	 * @param time
	 * @return
	 */
	public String getStatusAll(int time) {
		ArrayList<String> records = new ArrayList<String>();
		for (String cid: this.crosses.keySet()) {
			records.add(getStatue(cid, time));
		}
		return Utils.join(";", records);
	}
	
	/**
	 * 设置cid在time时间的红绿灯状态，用一个int表示
	 * @param cid
	 * @param seting
	 * @param time
	 */
	public void setLight(String cid, int seting, int time) {
		this.crosses.get(cid).setLight(seting, time);
	}
	
	/**
	 * 根据flow解析出红绿灯，设置输入的流量
	 * @param flow
	 */
	public void setFlow(String flow, int time) {
		//System.out.println(flow);
		String[] h = flow.split(",");
		String cid = h[0];
		TrafficCrossroad pp =  this.crosses.get(cid);
		int id = pp.getInt(h[1]);
		pp.flowHistory[time][id] = Integer.parseInt(h[2]);
		//System.out.println("\nFUCK= " + h[2]);
	}
	
	public void setFlowAll(String flow, int time) {
		String[] h = flow.split(";");
		for (String str: h) setFlow(str, time);
	}
}
