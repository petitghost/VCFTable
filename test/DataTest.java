import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import vcf_parser.InfoData;
import vcf_parser.ParsedData;
import vcf_parser.Sample;
import vcf_parser.Variant;


public class DataTest {
	@Test
	public void singleLineTest() {
		String singleData="1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0";	

		Variant variant = new Variant(singleData);
		assertEquals("1", variant.chrom);
		assertEquals("69270", variant.pos);
		assertEquals("rs201219564", variant.id);
		assertEquals("A", variant.ref);
		assertEquals("G", variant.alt);
		assertEquals("1317.77", variant.qual);
		assertEquals(".", variant.filter);
		assertEquals("AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09", variant.info);
		assertEquals("GT:AD:DP:GQ:PL", variant.format);
		assertEquals("1/1:495,134:630:99:1346,129,0", variant.sampleList.get(0).data);
	}

	@Test
	public void multipleLineTest(){
		String multipleData="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	HG00406\n"+
				"1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0\n"+
				"1	69511	rs75062661	A	G	2385.77	.	AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148	GT:AD:DP:GQ:PL	1/1:1,120:122:99:2414,195,0";

		BufferedReader reader=new BufferedReader(new StringReader(multipleData));
		ParsedData data=new ParsedData(reader);
		
		
		assertEquals("1", data.variantList.get(0).chrom);
		assertEquals("69270", data.variantList.get(0).pos);
		assertEquals("rs201219564", data.variantList.get(0).id);
		assertEquals("A", data.variantList.get(0).ref);
		assertEquals("G", data.variantList.get(0).alt);
		assertEquals("1317.77", data.variantList.get(0).qual);
		assertEquals(".", data.variantList.get(0).filter);
		assertEquals("AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09", data.variantList.get(0).info);
		assertEquals("GT:AD:DP:GQ:PL", data.variantList.get(0).format);
		assertEquals("1/1:495,134:630:99:1346,129,0", data.variantList.get(0).sampleList.get(0).data);
	
		assertEquals("1", data.variantList.get(1).chrom);
		assertEquals("69511", data.variantList.get(1).pos);
		assertEquals("rs75062661", data.variantList.get(1).id);
		assertEquals("A", data.variantList.get(1).ref);
		assertEquals("G", data.variantList.get(1).alt);
		assertEquals("2385.77", data.variantList.get(1).qual);
		assertEquals(".", data.variantList.get(1).filter);
		assertEquals("AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148", data.variantList.get(1).info);
		assertEquals("GT:AD:DP:GQ:PL", data.variantList.get(1).format);
		assertEquals("1/1:1,120:122:99:2414,195,0", data.variantList.get(1).sampleList.get(0).data);
	}
	
	@Test
	public void multipleSampleSingleLine(){
		String multiSampleSingleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";
		
		Variant variant = new Variant(multiSampleSingleLine);
		assertEquals("20", variant.chrom);
		assertEquals("14370", variant.pos);
		assertEquals("rs6054257", variant.id);
		assertEquals("G", variant.ref);
		assertEquals("A", variant.alt);
		assertEquals("29", variant.qual);
		assertEquals("PASS", variant.filter);
		assertEquals("NS=3;DP=14;AF=0.5;DB;H2", variant.info);
		assertEquals("GT:GQ:DP:HQ", variant.format);
		assertEquals("0|0:48:1:51,51", variant.sampleList.get(0).data);
		assertEquals("1|0:48:8:51,51", variant.sampleList.get(1).data);
		assertEquals("1/1:43:5:.,.", variant.sampleList.get(2).data);
		
	}
	
	@Test
	public void multipleSampleMultipleLine(){
		String multiSampleMultiLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		ParsedData data=new ParsedData(reader);
		assertEquals("20", data.variantList.get(0).chrom);
		assertEquals("14370", data.variantList.get(0).pos);
		assertEquals("rs6054257", data.variantList.get(0).id);
		assertEquals("G", data.variantList.get(0).ref);
		assertEquals("A", data.variantList.get(0).alt);
		assertEquals("29", data.variantList.get(0).qual);
		assertEquals("PASS", data.variantList.get(0).filter);
		assertEquals("NS=3;DP=14;AF=0.5;DB;H2", data.variantList.get(0).info);
		assertEquals("GT:GQ:DP:HQ", data.variantList.get(0).format);
		assertEquals("0|0:48:1:51,51", data.variantList.get(0).sampleList.get(0).data);
		assertEquals("1|0:48:8:51,51", data.variantList.get(0).sampleList.get(1).data);
		assertEquals("1/1:43:5:.,.", data.variantList.get(0).sampleList.get(2).data);
		
		assertEquals("20", data.variantList.get(1).chrom);
		assertEquals("17330", data.variantList.get(1).pos);
		assertEquals(".", data.variantList.get(1).id);
		assertEquals("T", data.variantList.get(1).ref);
		assertEquals("A", data.variantList.get(1).alt);
		assertEquals("3", data.variantList.get(1).qual);
		assertEquals("q10", data.variantList.get(1).filter);
		assertEquals("NS=3;DP=11;AF=0.017", data.variantList.get(1).info);
		assertEquals("GT:GQ:DP:HQ", data.variantList.get(1).format);
		assertEquals("0|0:49:3:58,50", data.variantList.get(1).sampleList.get(0).data);
		assertEquals("0|1:3:5:65,3", data.variantList.get(1).sampleList.get(1).data);
		assertEquals("0/0:41:3", data.variantList.get(1).sampleList.get(2).data);
		
	}
	
	@Test
	public void numberOfVariant(){
		String multiSampleMultiLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3\n"+
				 "20	1234567	microsat1	GTC	G,GTCT	50	PASS	NS=3;DP=9;AA=G	GT:GQ:DP	0/1:35:4	0/2:17:2	1/1:40:3";
	
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		ParsedData data=new ParsedData(reader);
		
		assertEquals(3,data.count());
		
	}
	
//	@Test
//	public void getSampleName(){
//		String singleSample="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	HG00406\n"+
//				"1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0\n"+
//				"1	69511	rs75062661	A	G	2385.77	.	AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148	GT:AD:DP:GQ:PL	1/1:1,120:122:99:2414,195,0";
//		
//		Variant variant=new Variant(singleSample);
//		//assertEquals("HG00406", variant.sampleList.get(0).name);
//		
//	}
	
	@Test
	public void parsedInfo(){
		String singleInfo="NS=3;DP=11;AF=0.017";
		
		InfoData info=new InfoData();
		info.putData(singleInfo);
		
		assertEquals("3",info.get("NS"));
		assertEquals("11",info.get("DP"));
		assertEquals("0.017",info.alleleFrequency.get(0));
	}
	
	@Test
	public void parsedmultiInfo(){
		String singleInfo="NS=2;DP=10;AF=0.333,0.667;AA=T;DB";
		
		InfoData info=new InfoData();
		info.putData(singleInfo);
		
		assertEquals("2",info.get("NS"));
		assertEquals("10",info.get("DP"));
		assertEquals("0.333",info.alleleFrequency.get(0));
		assertEquals("0.667",info.alleleFrequency.get(1));
		assertEquals("T",info.get("AA"));
		assertEquals(null,info.get("DB"));
	}

	@Test
	public void parsedMultipleInfo(){
		String multipleLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		ParsedData data=new ParsedData(reader);
		
		Variant variant = data.variantList.get(0);
		assertEquals("3",variant.information.get("NS"));
		assertEquals("14",variant.information.get("DP"));
		assertEquals("0.5",variant.information.alleleFrequency.get(0));
		assertEquals(null,variant.information.get("DB"));
		assertEquals(null,variant.information.get("H2"));
		
		Variant variant2 = data.variantList.get(1);
		assertEquals("3",variant2.information.get("NS"));
		assertEquals("11",variant2.information.get("DP"));
		assertEquals("0.017",variant2.information.alleleFrequency.get(0));
		  
	}

	@Test
	public void parsedFormat(){
		String Format="GT:GQ:DP:HQ";
		String data="0|0:48:1:51,51";
		Sample sample= new Sample(Format, data);
	
		assertEquals("0|0",sample.get("GT"));
		assertEquals("48",sample.get("GQ"));
		assertEquals("1",sample.get("DP"));
		assertEquals("51,51",sample.get("HQ"));
	}
	
	@Test
	public void parsedMultiSampleFormat(){
		String multipleLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		ParsedData data=new ParsedData(reader);
	
		Sample sample = data.variantList.get(0).sampleList.get(0);
		assertEquals("0|0",sample.get("GT"));
		assertEquals("48",sample.get("GQ"));
		assertEquals("1",sample.get("DP"));
		assertEquals("51,51",sample.get("HQ"));
		
		Sample sample2 = data.variantList.get(0).sampleList.get(1);
		assertEquals("1|0",sample2.get("GT"));
		assertEquals("48",sample2.get("GQ"));
		assertEquals("8",sample2.get("DP"));
		assertEquals("51,51",sample2.get("HQ"));
		
		Sample sample3 = data.variantList.get(0).sampleList.get(2);
		assertEquals("1/1",sample3.get("GT"));
		assertEquals("43",sample3.get("GQ"));
		assertEquals("5",sample3.get("DP"));
		assertEquals(".,.",sample3.get("HQ"));
	}
	
	@Test
	public void parsedMultiLineMultiSampleFormat(){
		String multipleLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		ParsedData data=new ParsedData(reader);
	
		Sample variant1_sample1 = data.variantList.get(0).sampleList.get(0);
		assertEquals("0|0",variant1_sample1.get("GT"));
		assertEquals("48",variant1_sample1.get("GQ"));
		assertEquals("1",variant1_sample1.get("DP"));
		assertEquals("51,51",variant1_sample1.get("HQ"));
		
		Sample variant1_sample2 = data.variantList.get(0).sampleList.get(1);
		assertEquals("1|0",variant1_sample2.get("GT"));
		assertEquals("48",variant1_sample2.get("GQ"));
		assertEquals("8",variant1_sample2.get("DP"));
		assertEquals("51,51",variant1_sample2.get("HQ"));
		
		Sample variant1_sample3 = data.variantList.get(0).sampleList.get(2);
		assertEquals("1/1",variant1_sample3.get("GT"));
		assertEquals("43",variant1_sample3.get("GQ"));
		assertEquals("5",variant1_sample3.get("DP"));
		assertEquals(".,.",variant1_sample3.get("HQ"));
		
		Sample variant2_sample1 = data.variantList.get(1).sampleList.get(0);
		assertEquals("0|0",variant2_sample1.get("GT"));
		assertEquals("49",variant2_sample1.get("GQ"));
		assertEquals("3",variant2_sample1.get("DP"));
		assertEquals("58,50",variant2_sample1.get("HQ"));
		
		Sample variant2_sample2 = data.variantList.get(1).sampleList.get(1);
		assertEquals("0|1",variant2_sample2.get("GT"));
		assertEquals("3",variant2_sample2.get("GQ"));
		assertEquals("5",variant2_sample2.get("DP"));
		assertEquals("65,3",variant2_sample2.get("HQ"));
		
		Sample variant2_sample3 = data.variantList.get(1).sampleList.get(2);
		assertEquals("0/0",variant2_sample3.get("GT"));
		assertEquals("41",variant2_sample3.get("GQ"));
		assertEquals("3",variant2_sample3.get("DP"));
		assertEquals(null,variant2_sample3.get("HQ"));
	}
}
