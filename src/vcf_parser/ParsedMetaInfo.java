package vcf_parser;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class ParsedMetaInfo {

	public Map<String, String> metaInfo=new TreeMap<String, String>();
	public ArrayList<Filter> filterList=new ArrayList<Filter>();
	public ArrayList<Format> formatList=new ArrayList<Format>();
	public ArrayList<Info> infoList=new ArrayList<Info>();
	
	public static void main(String[] args){
		
	}
	
	public ParsedMetaInfo(BufferedReader reader) {
		try{
			String line;
			while((line=reader.readLine())!=null){ 
				if(!line.startsWith("##")){
					break;
				}
				if(line.contains("=<")){
					if(line.startsWith("##FILTER")){
						Filter filterItem=new Filter(line);
						filterList.add(filterItem);
					}else if(line.startsWith("##FORMAT")){
						Format formatItem=new Format(line);
						formatList.add(formatItem);
					}else if(line.startsWith("##INFO")){
						Info infoItem=new Info(line);
						infoList.add(infoItem);
					}
				}else{
					StringBuffer sb=new StringBuffer(line);
					sb.delete(0, 2); //deleted ##
					line=sb.toString();
					String[] data=line.split("=");
					put(data[0],data[1]);
				}				
			}
			reader.close();
		}catch(java.io.IOException ex){
			System.out.println("MetaInfo error");
		}
			
	}

	public void put(String key, String value) {
		metaInfo.put(key, value);
	}
	
	public String get(String key){
		return metaInfo.get(key);
	}

}
