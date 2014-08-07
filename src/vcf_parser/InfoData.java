package vcf_parser;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class InfoData {
	public String data;
	Map<String,String> hash = new TreeMap<String, String>();
	public ArrayList<AlleleCount> alleleCountList = new ArrayList<AlleleCount>();
	public ArrayList<AlleleFrequency> alleleFrequencyList = new ArrayList<AlleleFrequency>();

	public InfoData(String allele, String data){
		this.data=data;
		String[] fields = data.split(";");
		for(String field:fields){
			if(field.indexOf("=")==-1){
				hash.put(field, "true");
				continue;		
			}
			String[] info=field.split("=");
			if(info[0].equals("AC")){
				createAlleleField(allele, info);
			}else if(info[0].equals("AF")){
				createAlleleField(allele, info);
			}else{
				hash.put(info[0], info[1]);
			}
		
		}
	}

	private void createAlleleField(String allele, String[] info) {
		String[] values=info[1].split(",");
		String[] alleles=allele.split(",");
		for(int i=0;i<alleles.length;i++){
			if(info[0].equals("AC")){
				AlleleCount alleleCount=new AlleleCount(alleles[i], values[i]);
				alleleCountList.add(alleleCount);
			}else if(info[0].equals("AF")){
				AlleleFrequency alleleFrequency=new AlleleFrequency(alleles[i], values[i]);
				alleleFrequencyList.add(alleleFrequency);
			}
		}
	}

	public String get(String key){
		return hash.get(key);
	}
	
}
