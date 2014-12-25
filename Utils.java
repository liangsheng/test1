package test1;

import java.util.ArrayList;

public class Utils {
	 //字符串数组拼接
	  public static String join(String join, ArrayList<String> strAry){
	        StringBuffer sb=new StringBuffer();
	        for(int i=0;i<strAry.size();i++){
	             if(i==(strAry.size()-1)){
	                 sb.append(strAry.get(i));
	             }else{
	                 sb.append(strAry.get(i)).append(join);
	             }
	        }
	        return new String(sb);
	    }
}
