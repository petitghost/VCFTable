package vcf_parser;

import java.util.ArrayList;

public class Variant {
	public String chrom, pos, id,ref, alt, qual, filter, info, format;
	public ArrayList<Sample> sampleList = new ArrayList<Sample>();
	public InfoData information=new InfoData();
	
	public Variant(String line) {
		String[] columns=line.split("\t");
		for(int i=0;i<columns.length;i++){
			if(i==0){
				this.chrom=columns[i];
			}else if(i==1){ 
				this.pos=columns[i];
			}else if(i==2){
				this.id=columns[i];
			}else if(i==3){
				this.ref=columns[i];
			}else if(i==4){
				this.alt=columns[i];
			}else if(i==5){
				this.qual=columns[i];
			}else if(i==6){
				this.filter=columns[i];
			}else if(i==7){
				this.info=columns[i];
				information.putData(columns[i]);
			}else if(i==8){
				this.format=columns[i];
			}else{
				Sample sample=new Sample(columns[i]);
				sampleList.add(sample);
				
			}
		}
	}

}
