package test1;

public class Constants {
	public final static int MAX_TIME = 1681; // 总时间
	public final static int ESTIMATE_INTERVAL = 3; // 预估时间段
	public final static float LAMBDA_1 = 0.1f; // 权重
	public final static float LAMBDA_2 = -0.5f; // 权重

	public final static float[] TURN_PROBA = { 0.1f, 0.8f, 0.1f }; // 转弯概率，左中右
	public final static float[] TURN_PROBA_REV = { 0.1f, 0.8f, 0.1f }; // 相反的转弯概率，回馈用

	public final static float[] MAX_THROUGH = { 2.0f, 16.0f, 2.0f }; // 最大通过量，左中右
	public final static String LIGHT_NONE = "#";

	public final static String FILENAME_TRAFFIC = "/test1/TrafficLightTable.txt";
	public final static String FILENAME_FLOW_ADD = "/test1/flow0901.txt";

	public final static int MAX_LIGHT_INTERVAL = 2;
	
	public final static int flowD2L = 1 << 0;
	public final static int flowD2R = 1 << 1;
	public final static int flowD2U = 1 << 2;
	
	public final static int flowL2U = 1 << 3;
	public final static int flowL2D = 1 << 4;
	public final static int flowL2R = 1 << 5;
	
	public final static int flowR2D = 1 << 6;
	public final static int flowR2U = 1 << 7;
	public final static int flowR2L = 1 << 8;
	
	public final static int flowU2R = 1 << 9;
	public final static int flowU2L = 1 << 10;
	public final static int flowU2D = 1 << 11;
}
