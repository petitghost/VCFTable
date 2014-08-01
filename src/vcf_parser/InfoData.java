package vcf_parser;

import java.util.Map;
import java.util.TreeMap;

public class InfoData {
	Map<String,String> hash = new TreeMap<String, String>();

	public InfoData() {
		
	}
	
	public void putData(String data){
		String[] fields = data.split(";");
		for(String field:fields){
			if(field.indexOf("=")==-1){
				hash.put(field, null);
				continue;
			}
			String[] info=field.split("=");
			hash.put(info[0], info[1]);
		}
	}

	public String get(String key){
		return hash.get(key);
	}
}
