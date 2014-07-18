import static org.junit.Assert.*;

import org.junit.Test;

import vcf_parser.Filter;
import vcf_parser.Format;
import vcf_parser.Info;
import vcf_parser.BasicInfo;


public class MetaInfoTest{
	
	static final String content =
			"##fileformat=VCFv4.1\n"+
			"##fileDate=20090805\n"+
			"##reference=file:///seq/references/1000GenomesPilot-NCBI36.fasta\n"+
			"##FILTER=<ID=LowQual,Description=\"Low quality\">\n"+
			"##FILTER=<ID=q10,Description=\"Quality below 10\">\n"+
			"##FILTER=<ID=s50,Description=\"Less than 50% of samples have data\">\n"+
			"##INFO=<ID=NS,Number=1,Type=Integer,Description=\"Number of Samples With Data\">\n"+
			"##INFO=<ID=DP,Number=1,Type=Integer,Description=\"Total Depth\">\n"+
			"##INFO=<ID=AF,Number=A,Type=Float,Description=\"Allele Frequency\">\n"+
			"##INFO=<ID=AA,Number=1,Type=String,Description=\"Ancestral Allele\">\n"+
			"##FORMAT=<ID=AD,Number=.,Type=Integer,Description=\"Allelic depths for the ref and alt alleles in the order listed\">\n"+
			"##FORMAT=<ID=GT,Number=1,Type=String,Description=\"Genotype\">\n"+
			"##FORMAT=<ID=PL,Number=G,Type=Integer,Description=\"Normalized, Phred-scaled likelihoods for genotypes as defined in the VCF specification\">\n";		
	
	
	
	@Test
	public void testBasicInfo() {
		String basicContent=
				"##fileformat=VCFv4.1\n"+
				"##fileDate=20090805\n"+
				"##reference=file:///seq/references/1000GenomesPilot-NCBI36.fasta\n";
		BasicInfo basic = new BasicInfo(basicContent);
		assertEquals("fileformat","VCFv4.1", basic.get("fileformat"));
		assertEquals("fileDate","20090805", basic.get("fileDate"));
		assertEquals("reference","file:///seq/references/1000GenomesPilot-NCBI36.fasta", basic.get("reference"));
	}
	
	@Test
	public void parsedFilterTest(){		
		int filterCount=0;
		String filterContent= "##FILTER=<ID=LowQual,Description=\"Low quality\">\n"+
				"##FILTER=<ID=q10,Description=\"Quality below 10\">\n"+
				"##FILTER=<ID=s50,Description=\"Less than 50% of samples have data\">\n";
		Filter[] filter = new Filter[10];
		String[] lines=filterContent.split("\n");
		for(String line:lines){
			filter[filterCount]=new Filter(line);
			filterCount++;
		}
		
		assertEquals("LowQual", filter[0].id);
		assertEquals("Low quality", filter[0].description);
		
		assertEquals("q10", filter[1].id);
		assertEquals("Quality below 10", filter[1].description);
		
		assertEquals("s50", filter[2].id);
		assertEquals("Less than 50% of samples have data", filter[2].description);
	}
	
	@Test
	public void parsedInfoTest(){
		int infoCount=0;
		String infoContent= "##INFO=<ID=NS,Number=1,Type=Integer,Description=\"Number of Samples With Data\">\n"+
				"##INFO=<ID=DP,Number=1,Type=Integer,Description=\"Total Depth\">\n"+
				"##INFO=<ID=AF,Number=A,Type=Float,Description=\"Allele Frequency\">\n"+
				"##INFO=<ID=AA,Number=1,Type=String,Description=\"Ancestral Allele\">\n";
		Info[] information=new Info[10];
		String[] lines=infoContent.split("\n");
		for(String line:lines){
			information[infoCount]=new Info(line);
			infoCount++;
		}
		assertEquals("NS", information[0].id);
		assertEquals("1", information[0].number);
		assertEquals("Integer", information[0].type);
		assertEquals("Number of Samples With Data", information[0].description);
		
		assertEquals("DP", information[1].id);
		assertEquals("1", information[1].number);
		assertEquals("Integer", information[1].type);
		assertEquals("Total Depth", information[1].description);
		
		assertEquals("AF", information[2].id);
		assertEquals("A", information[2].number);
		assertEquals("Float", information[2].type);
		assertEquals("Allele Frequency", information[2].description);
		
		assertEquals("AA", information[3].id);
		assertEquals("1", information[3].number);
		assertEquals("String", information[3].type);
		assertEquals("Ancestral Allele", information[3].description);
	}
	
	@Test
	public void parsedFormatTest(){
		int formatCount=0;
		String formatContent=
				"##FORMAT=<ID=AD,Number=.,Type=Integer,Description=\"Allelic depths for the ref and alt alleles in the order listed\">\n"+
				"##FORMAT=<ID=GT,Number=1,Type=String,Description=\"Genotype\">\n"+
				"##FORMAT=<ID=PL,Number=G,Type=Integer,Description=\"Normalized, Phred-scaled likelihoods for genotypes as defined in the VCF specification\">\n";		
		
		Format[] format=new Format[10];
		String[] lines=formatContent.split("\n");
		for(String line:lines){
			format[formatCount]=new Format(line);
			formatCount++;
		}
		assertEquals("AD", format[0].id);
		assertEquals(".", format[0].number);
		assertEquals("Integer", format[0].type);
		assertEquals("Allelic depths for the ref and alt alleles in the order listed", format[0].description);
		
		assertEquals("GT", format[1].id);
		assertEquals("1", format[1].number);
		assertEquals("String", format[1].type);
		assertEquals("Genotype", format[1].description);
		
		assertEquals("PL", format[2].id);
		assertEquals("G", format[2].number);
		assertEquals("Integer", format[2].type);
		assertEquals("Normalized Phred-scaled likelihoods for genotypes as defined in the VCF specification", format[2].description);
	}
	
	@Test
	public void allMetaInfoTest(){
		BasicInfo info = new BasicInfo(content);
		assertEquals("fileformat","VCFv4.1", info.get("fileformat"));
		assertEquals("fileDate","20090805", info.get("fileDate"));
		assertEquals("reference","file:///seq/references/1000GenomesPilot-NCBI36.fasta", info.get("reference"));	
		
		int filterCount=0, formatCount=0, infoCount=0;
		Filter[] filter = new Filter[10];
		Format[] format=new Format[10];
		Info[] information=new Info[10];
		
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.contains("=<")){
				if(line.startsWith("##FILTER")){
					filter[filterCount]=new Filter(line);
					filterCount++;
				}else if(line.startsWith("##FORMAT")){
					format[formatCount]=new Format(line);
					formatCount++;
				}else if(line.startsWith("##INFO")){
					information[infoCount]=new Info(line);
					infoCount++;
				
				}
			}
		}		
		
		assertEquals("LowQual", filter[0].id);
		assertEquals("Low quality", filter[0].description);
		
		assertEquals("q10", filter[1].id);
		assertEquals("Quality below 10", filter[1].description);
		
		assertEquals("s50", filter[2].id);
		assertEquals("Less than 50% of samples have data", filter[2].description);
		
		assertEquals("AD", format[0].id);
		assertEquals(".", format[0].number);
		assertEquals("Integer", format[0].type);
		assertEquals("Allelic depths for the ref and alt alleles in the order listed", format[0].description);
		
		assertEquals("GT", format[1].id);
		assertEquals("1", format[1].number);
		assertEquals("String", format[1].type);
		assertEquals("Genotype", format[1].description);
		
		assertEquals("PL", format[2].id);
		assertEquals("G", format[2].number);
		assertEquals("Integer", format[2].type);
		assertEquals("Normalized Phred-scaled likelihoods for genotypes as defined in the VCF specification", format[2].description);
	
		
		assertEquals("NS", information[0].id);
		assertEquals("1", information[0].number);
		assertEquals("Integer", information[0].type);
		assertEquals("Number of Samples With Data", information[0].description);
		
		assertEquals("DP", information[1].id);
		assertEquals("1", information[1].number);
		assertEquals("Integer", information[1].type);
		assertEquals("Total Depth", information[1].description);
		
		assertEquals("AF", information[2].id);
		assertEquals("A", information[2].number);
		assertEquals("Float", information[2].type);
		assertEquals("Allele Frequency", information[2].description);
		
		assertEquals("AA", information[3].id);
		assertEquals("1", information[3].number);
		assertEquals("String", information[3].type);
		assertEquals("Ancestral Allele", information[3].description);
	}

}
