package vcf_parser;

import java.util.Map;
import java.util.TreeMap;

public class ParsedMetaInfo {

	public Map<String, String> metaInfo=new TreeMap<String, String>();
	public Filter[] filter = new Filter[10];
	public Format[] format=new Format[10];
	public Info[] information=new Info[10];
	
	public ParsedMetaInfo(String content) {
		int filterCount=0, formatCount=0, infoCount=0;
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.contains("=<")){
				if(line.startsWith("##FILTER")){
					filter[filterCount]=new Filter(line);
					filterCount++;
				}else if(line.startsWith("##FORMAT")){
					format[formatCount]=new Format(line);
					formatCount++;
				}else if(line.startsWith("##INFO")){
					information[infoCount]=new Info(line);
					infoCount++;
				
				}
			}else{
				StringBuffer sb=new StringBuffer(line);
				sb.delete(0, 2); //deleted ##
				line=sb.toString();
				String[] data=line.split("=");
				put(data[0],data[1]);
			}
		}		
	}

	public void put(String key, String value) {
		metaInfo.put(key, value);
	}
	
	public String get(String key){
		return metaInfo.get(key);
	}

}
