package vcf_parser;

import java.io.BufferedReader;
import java.util.ArrayList;

public class ParsedData {
	int variantCount=0;
	public ArrayList<Variant> variantList=new ArrayList<Variant>();
	
	public ParsedData(BufferedReader reader) {
		try{
			String line;
			while((line=reader.readLine())!=null){
				if(!line.startsWith("#")){
					variantCount++;
					Variant variant=new Variant(line);
					variantList.add(variant);
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
