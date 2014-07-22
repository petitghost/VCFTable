package vcf_parser;

import java.util.Map;
import java.util.TreeMap;

public class ParsedMetaInfo {

	public Map<String, String> metaInfo=new TreeMap<String, String>();
	public Filter[] filter;
	public Format[] format;
	public Info[] information;
	
	public ParsedMetaInfo(String content) {
		dynamicArraySize(content);
		int filterIndex=0, formatIndex=0, infoSize=0;
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.contains("=<")){
				if(line.startsWith("##FILTER")){
					filter[filterIndex]=new Filter(line);
					filterIndex++;
				}else if(line.startsWith("##FORMAT")){
					format[formatIndex]=new Format(line);
					formatIndex++;
				}else if(line.startsWith("##INFO")){
					information[infoSize]=new Info(line);
					infoSize++;
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

	private void dynamicArraySize(String content){
		int filterSize=0, formatSize=0, infoSize=0;
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.startsWith("##")){
				if(line.startsWith("##FILTER")){
					filterSize++;
				}else if(line.startsWith("##FORMAT")){
					formatSize++;
				}else if(line.startsWith("##INFO")){
					infoSize++;
				
				}
			}else{
				break;
			}
		}
		filter = new Filter[filterSize];
		format=new Format[formatSize];
		information=new Info[infoSize];
	}
}
