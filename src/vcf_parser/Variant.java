package vcf_parser;

import java.util.ArrayList;

public class Variant {
	public String chrom, pos, id,ref, alt, qual, filter, info, format;
	public ArrayList<Sample> sampleList = new ArrayList<Sample>();
	public InfoData information=new InfoData();
	
	public Variant(String line) {
		String[] columns=line.split("\t");
		for(int i=0;i<columns.length;i++){
			switch (i){
			case 0: this.chrom=columns[i];
					break;
			case 1: this.pos=columns[i];
					break;
			case 2: this.id=columns[i];
					break;
			case 3: this.ref=columns[i];
					break;
			case 4: this.alt=columns[i];
					break;
			case 5: this.qual=columns[i];
					break;
			case 6: this.filter=columns[i];
					break;
			case 7: this.info=columns[i];
					information.putData(columns[i]);
					break;
			case 8: this.format=columns[i];
					break;
			default:
				Sample sample=new Sample(this.format, columns[i]);
				sampleList.add(sample);
			}

		}
	}

}
