package vcf_parser;

import java.io.BufferedReader;
import java.util.ArrayList;

public class BodyData {
	public ArrayList<String> sampleName=new ArrayList<String>();
	public ArrayList<Variant> variantList=new ArrayList<Variant>();
	
	public BodyData(BufferedReader reader) {
		try{
			String line;
			while((line=reader.readLine())!=null){
				if(line.startsWith("##")){
					continue;
				}else if(line.startsWith("#")){
					String[] columns=line.split("\t");
					for(int i=9;i<columns.length;i++){
						sampleName.add(columns[i]);
					}
				}else{
					Variant variant=new Variant(line);
					variantList.add(variant);					
				}
				
			}
		}catch(java.io.IOException ex){
			System.out.println("Data error");
		}
	}

}
