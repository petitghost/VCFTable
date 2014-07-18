package vcf_parser;

import java.util.Map;
import java.util.TreeMap;

public class BasicInfo{
	public Map<String, String> metaInfo=new TreeMap<String, String>();
	
	public BasicInfo(String content){
		String[] lines=content.split("\n");
		for(String line:lines){
			StringBuffer sb=new StringBuffer(line);
			sb.delete(0, 2); //deleted ##
			line=sb.toString();
			String[] data=line.split("=");
			put(data[0],data[1]);
			
		}
	}
		
	public void put(String key, String value) {
		metaInfo.put(key, value);
	}
	
	public String get(String key){
		return metaInfo.get(key);
	}
	
}


