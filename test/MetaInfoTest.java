import static org.junit.Assert.*;

import org.junit.Test;

import vcf_parser.Filter;
import vcf_parser.Format;
import vcf_parser.Info;
import vcf_parser.ParsedMetaInfo;


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
		//BasicInfo basic = new BasicInfo(basicContent);
		ParsedMetaInfo metaInfo=new ParsedMetaInfo(basicContent);
		assertEquals("fileformat","VCFv4.1", metaInfo.get("fileformat"));
		assertEquals("fileDate","20090805", metaInfo.get("fileDate"));
		assertEquals("reference","file:///seq/references/1000GenomesPilot-NCBI36.fasta", metaInfo.get("reference"));
	}
	
	@Test
	public void parsedFilterTest(){	
		String filterContent= "##FILTER=<ID=LowQual,Description=\"Low quality\">\n";		
		Filter filter = new Filter(filterContent);
		
		assertEquals("LowQual", filter.id);
		assertEquals("Low quality", filter.description);
		
	}
	
	@Test
	public void parsedInfoTest(){
		String infoContent= "##INFO=<ID=NS,Number=1,Type=Integer,Description=\"Number of Samples With Data\">\n";		
		Info information=new Info(infoContent);
		
		assertEquals("NS", information.id);
		assertEquals("1", information.number);
		assertEquals("Integer", information.type);
		assertEquals("Number of Samples With Data", information.description);
		
	}
	
	@Test
	public void parsedFormatTest(){
		String formatContent="##FORMAT=<ID=AD,Number=.,Type=Integer,Description=\"Allelic depths for the ref and alt alleles in the order listed\">\n";
		Format format=new Format(formatContent);
		
		assertEquals("AD", format.id);
		assertEquals(".", format.number);
		assertEquals("Integer", format.type);
		assertEquals("Allelic depths for the ref and alt alleles in the order listed", format.description);
	}
	
	@Test
	public void allMetaInfoTest(){
		ParsedMetaInfo metaInfo=new ParsedMetaInfo(content);
		
		assertEquals("fileformat","VCFv4.1", metaInfo.get("fileformat"));
		assertEquals("fileDate","20090805", metaInfo.get("fileDate"));
		assertEquals("reference","file:///seq/references/1000GenomesPilot-NCBI36.fasta", metaInfo.get("reference"));	
				
		assertEquals("LowQual", metaInfo.filter[0].id);
		assertEquals("Low quality", metaInfo.filter[0].description);
		
		assertEquals("q10", metaInfo.filter[1].id);
		assertEquals("Quality below 10", metaInfo.filter[1].description);
		
		assertEquals("s50", metaInfo.filter[2].id);
		assertEquals("Less than 50% of samples have data", metaInfo.filter[2].description);
		
		assertEquals("AD", metaInfo.format[0].id);
		assertEquals(".", metaInfo.format[0].number);
		assertEquals("Integer", metaInfo.format[0].type);
		assertEquals("Allelic depths for the ref and alt alleles in the order listed", metaInfo.format[0].description);
		
		assertEquals("GT", metaInfo.format[1].id);
		assertEquals("1", metaInfo.format[1].number);
		assertEquals("String", metaInfo.format[1].type);
		assertEquals("Genotype", metaInfo.format[1].description);
		
		assertEquals("PL", metaInfo.format[2].id);
		assertEquals("G", metaInfo.format[2].number);
		assertEquals("Integer", metaInfo.format[2].type);
		assertEquals("Normalized Phred-scaled likelihoods for genotypes as defined in the VCF specification", metaInfo.format[2].description);
	
		
		assertEquals("NS", metaInfo.information[0].id);
		assertEquals("1", metaInfo.information[0].number);
		assertEquals("Integer", metaInfo.information[0].type);
		assertEquals("Number of Samples With Data", metaInfo.information[0].description);
		
		assertEquals("DP", metaInfo.information[1].id);
		assertEquals("1", metaInfo.information[1].number);
		assertEquals("Integer", metaInfo.information[1].type);
		assertEquals("Total Depth", metaInfo.information[1].description);
		
		assertEquals("AF", metaInfo.information[2].id);
		assertEquals("A", metaInfo.information[2].number);
		assertEquals("Float", metaInfo.information[2].type);
		assertEquals("Allele Frequency", metaInfo.information[2].description);
		
		assertEquals("AA", metaInfo.information[3].id);
		assertEquals("1", metaInfo.information[3].number);
		assertEquals("String", metaInfo.information[3].type);
		assertEquals("Ancestral Allele", metaInfo.information[3].description);
	}

}
