package vcf_parser;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class InfoData {
	public String data;
	Map<String,String> hash = new TreeMap<String, String>();
	public ArrayList<String> alleleCount=new ArrayList<String>();
	public ArrayList<String> alleleFrequency = new ArrayList<String>();

	public InfoData() {
		
	}
	
	public void putData(String data){
		this.data=data;
		String[] fields = data.split(";");
		for(String field:fields){
			if(field.indexOf("=")==-1){
				hash.put(field, null);
				continue;		
			}
			String[] info=field.split("=");
			if(info[0].equals("AC")){
				multiInfoFields(alleleCount,info[1]);
			}else if(info[0].equals("AF")){
				multiInfoFields(alleleFrequency,info[1]);
			}else{
				hash.put(info[0], info[1]);
			}
		
		}
	}

	public String get(String key){
		return hash.get(key);
	}
	
	private void multiInfoFields(ArrayList<String> arrayList, String info){
		String[] cuts=info.split(",");
		for(String cut:cuts){
			arrayList.add(cut);
		}
	}
}
