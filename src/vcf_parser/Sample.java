package vcf_parser;

import java.util.ArrayList;

public class Sample {
	public ArrayList<String> data=new ArrayList<String>();
	
	public void addData(String line){
		data.add(line);		
	}
}
