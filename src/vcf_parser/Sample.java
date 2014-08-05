package vcf_parser;

import java.util.Map;
import java.util.TreeMap;


public class Sample {
	public String data;
	public Map<String, String> hash=new TreeMap<String, String>();
	
	public Sample(String field, String value){
		this.data=value;
		String[] fields=field.split(":");
		String[] values=value.split(":");
		for(int i=0;i<values.length;i++){ // data counts isn't equal to format fields
			hash.put(fields[i], values[i]);
		}
	}

	public String get(String key){
		return hash.get(key);
	}
	
	public String getName(String name) {
		
		return null;
	}
	
	
}
