import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

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
		BufferedReader reader=new BufferedReader(new StringReader(basicContent));
		ParsedMetaInfo metaInfo=new ParsedMetaInfo(reader);
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
		BufferedReader reader=new BufferedReader(new StringReader(content));
		ParsedMetaInfo metaInfo=new ParsedMetaInfo(reader);
		
		assertEquals("fileformat","VCFv4.1", metaInfo.get("fileformat"));
		assertEquals("fileDate","20090805", metaInfo.get("fileDate"));
		assertEquals("reference","file:///seq/references/1000GenomesPilot-NCBI36.fasta", metaInfo.get("reference"));	
		
		assertEquals("LowQual", metaInfo.filterList.get(0).id);
		assertEquals("Low quality", metaInfo.filterList.get(0).description);
		
		assertEquals("q10", metaInfo.filterList.get(1).id);
		assertEquals("Quality below 10", metaInfo.filterList.get(1).description);
		
		assertEquals("s50", metaInfo.filterList.get(2).id);
		assertEquals("Less than 50% of samples have data", metaInfo.filterList.get(2).description);
		
		assertEquals("AD", metaInfo.formatList.get(0).id);
		assertEquals(".", metaInfo.formatList.get(0).number);
		assertEquals("Integer", metaInfo.formatList.get(0).type);
		assertEquals("Allelic depths for the ref and alt alleles in the order listed", metaInfo.formatList.get(0).description);
		
		assertEquals("GT", metaInfo.formatList.get(1).id);
		assertEquals("1", metaInfo.formatList.get(1).number);
		assertEquals("String", metaInfo.formatList.get(1).type);
		assertEquals("Genotype", metaInfo.formatList.get(1).description);
		
		assertEquals("PL", metaInfo.formatList.get(2).id);
		assertEquals("G", metaInfo.formatList.get(2).number);
		assertEquals("Integer", metaInfo.formatList.get(2).type);
		assertEquals("Normalized Phred-scaled likelihoods for genotypes as defined in the VCF specification", metaInfo.formatList.get(2).description);
	
		
		assertEquals("NS", metaInfo.infoList.get(0).id);
		assertEquals("1", metaInfo.infoList.get(0).number);
		assertEquals("Integer", metaInfo.infoList.get(0).type);
		assertEquals("Number of Samples With Data", metaInfo.infoList.get(0).description);
		
		assertEquals("DP", metaInfo.infoList.get(1).id);
		assertEquals("1", metaInfo.infoList.get(1).number);
		assertEquals("Integer", metaInfo.infoList.get(1).type);
		assertEquals("Total Depth", metaInfo.infoList.get(1).description);
		
		assertEquals("AF", metaInfo.infoList.get(2).id);
		assertEquals("A", metaInfo.infoList.get(2).number);
		assertEquals("Float", metaInfo.infoList.get(2).type);
		assertEquals("Allele Frequency", metaInfo.infoList.get(2).description);
		
		assertEquals("AA", metaInfo.infoList.get(3).id);
		assertEquals("1", metaInfo.infoList.get(3).number);
		assertEquals("String", metaInfo.infoList.get(3).type);
		assertEquals("Ancestral Allele", metaInfo.infoList.get(3).description);
	}

}
