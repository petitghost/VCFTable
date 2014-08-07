package vcf_parser;

import java.util.ArrayList;

public class Variant {
	public String chrom, pos, id,ref, alt, qual, filter, info, format;
	public ArrayList<Sample> sampleList = new ArrayList<Sample>();
	public InfoData information;
	
	public Variant(String line) {
		String[] columns=line.split("\t");
		this.chrom=columns[0];
		this.pos=columns[1];
		this.id=columns[2];
		this.ref=columns[3];
		this.alt=columns[4];
		this.qual=columns[5];
		this.filter=columns[6];
		this.info=columns[7];
		information=new InfoData(this.alt, columns[7]);
		if(columns.length>8)
			this.format=columns[8];
			for(int i=9;i<columns.length;i++){
				Sample sample=new Sample(this.format, columns[i]);
				sampleList.add(sample);
			}

		}
}
