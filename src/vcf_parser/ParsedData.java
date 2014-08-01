package vcf_parser;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ParsedData {
	int variantCount=0;
	public ArrayList<String> chrom = new ArrayList<String>();
	public ArrayList<String> pos = new ArrayList<String>();
	public ArrayList<String> id = new ArrayList<String>();
	public ArrayList<String> ref = new ArrayList<String>();
	public ArrayList<String> alt = new ArrayList<String>();
	public ArrayList<String> qual = new ArrayList<String>();
	public ArrayList<String> filter = new ArrayList<String>();
	public ArrayList<String> info = new ArrayList<String>();
	public ArrayList<String> format = new ArrayList<String>();
	public ArrayList<Sample> sampleList = new ArrayList<Sample>();
	
	//Map<String, ArrayList<String>> hash; 
	public ParsedData(BufferedReader reader) {
		try{
			String line;
			while((line=reader.readLine())!=null){
				String[] columns=line.split("\t");
				if(line.startsWith("#")){
//					hash=new TreeMap<String, ArrayList<String>>();
//					for(int i=0;i<columns.length;i++){
//						if(i<9){
//							//ArrayList<String> columns[i].toLowerCase();
//							hash.put(Integer.toString(i), columns[i].toLowerCase());
//						}
//						else{
//							Sample sample=new Sample();
//							sampleList.add(sample);
//						}
//					}
					for(int i=0;i<(columns.length-9);i++){
						Sample sample=new Sample();
						sampleList.add(sample);
					}
				}else{
					variantCount++;
					for(int i=0;i<columns.length;i++){
						if(i==0){
							chrom.add(columns[i]);
						}else if(i==1){ 
							pos.add(columns[i]);
						}else if(i==2){
							id.add(columns[i]);
						}else if(i==3){
							ref.add(columns[i]);
						}else if(i==4){
							alt.add(columns[i]);
						}else if(i==5){
							qual.add(columns[i]);
						}else if(i==6){
							filter.add(columns[i]);
						}else if(i==7){
							info.add(columns[i]);
						}else if(i==8){
							format.add(columns[i]);
						}else{
							sampleList.get(i-9).addData(columns[i]);
							//sampleList.get(0).data.add(columns[i]);
							
						}
					}
					
				}
			}
			reader.close();
		}catch(java.io.IOException ex){
			System.out.println("error");
		}
	}

	public int count() {
		return variantCount;
	}

}
