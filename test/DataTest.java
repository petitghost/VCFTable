import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Test;

import vcf_parser.InfoData;
import vcf_parser.ParsedData;
import vcf_parser.Sample;
import vcf_parser.Variant;


public class DataTest {
	@Test
	public void singleMandatoryColumnTest() {
		String singleData="1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0";	
		Variant variant = new Variant(singleData);
		
		assertEquals("1", variant.chrom);
		assertEquals("69270", variant.pos);
		assertEquals("rs201219564", variant.id);
		assertEquals("A", variant.ref);
		assertEquals("G", variant.alt);
		assertEquals("1317.77", variant.qual);
		assertEquals(".", variant.filter);
		assertEquals("GT:AD:DP:GQ:PL", variant.format);
		
		InfoData information = variant.information;
		assertEquals("AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09", information.data);
		
		Sample sample = variant.sampleList.get(0);
		assertEquals("1/1:495,134:630:99:1346,129,0", sample.data);
	}

	@Test
	public void multipleMandatoryLineTest(){
		String multipleData="1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0\n";
		BufferedReader reader=new BufferedReader(new StringReader(multipleData));
		ParsedData data=new ParsedData(reader);		
		Variant variant = data.variantList.get(0);
		
		assertEquals("69270", variant.pos);
		assertEquals("rs201219564", variant.id);

		multipleData += "1	69511	rs75062661	A	G	2385.77	.	AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148	GT:AD:DP:GQ:PL	1/1:1,120:122:99:2414,195,0";
		reader=new BufferedReader(new StringReader(multipleData));
		data=new ParsedData(reader);
		variant = data.variantList.get(1);
		
		assertEquals("69511", variant.pos);
		assertEquals("rs75062661", variant.id);
	}
	
	@Test
	public void multipleSampleSingleLine(){
		String multiSampleSingleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";		
		Variant variant = new Variant(multiSampleSingleLine);		
		ArrayList<Sample> sampleList = variant.sampleList;
		
		assertEquals("0|0:48:1:51,51", sampleList.get(0).data);
		assertEquals("1|0:48:8:51,51", sampleList.get(1).data);
		assertEquals("1/1:43:5:.,.", sampleList.get(2).data);
		
	}
	
	@Test
	public void multipleSampleMultipleLine(){
		String multiSampleMultiLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n";
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		ParsedData data=new ParsedData(reader);
		ArrayList<Sample> sampleList = data.variantList.get(0).sampleList;
		
		assertEquals("1/1:43:5:.,.", sampleList.get(2).data);
		
		multiSampleMultiLine += "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		data=new ParsedData(reader);
		sampleList = data.variantList.get(1).sampleList;
		
		assertEquals("0/0:41:3", sampleList.get(2).data);
		
	}
	
	@Test
	public void numberOfVariant(){
		String multiSampleMultiLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3\n";				 	
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		ParsedData data=new ParsedData(reader);
		
		assertEquals(2,data.count());
		
		multiSampleMultiLine += "20	134567	microsat1	GTC	G,GTCT	50	PASS	NS=3;DP=9;AA=G	GT:GQ:DP	0/1:35:4	0/2:17:2	1/1:40:3\n";
		reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		data=new ParsedData(reader);
		
		assertEquals(3,data.count());
		
		multiSampleMultiLine += "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3\n";
		reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		data=new ParsedData(reader);
		
		assertEquals(5,data.count());
	}
	
	@Test
	public void getSampleName(){
		String multipleSample="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n";
		BufferedReader reader=new BufferedReader(new StringReader(multipleSample));
		ParsedData data=new ParsedData(reader);
		ArrayList<String> sampleName = data.sampleName;
		
		assertEquals("NA00001", sampleName.get(0));
		assertEquals("NA00002", sampleName.get(1));
		assertEquals("NA00003", sampleName.get(2));
	}
	
	@Test
	public void parsedInfo(){
		InfoData info=new InfoData();
		info.putData("NS=3;DP=11;AF=0.017;DB");
		
		assertEquals("3",info.get("NS"));
		assertEquals("11",info.get("DP"));
		assertEquals("0.017",info.alleleFrequency.get(0));
		assertEquals(null,info.get("DB"));
	}
	
	@Test
	public void parsedInfoMultiField(){
		String singleInfoMultiFields="NS=2;DP=10;AF=0.333,0.667;AA=T;DB";
		InfoData info=new InfoData();
		info.putData(singleInfoMultiFields);
		
		assertEquals("0.333",info.alleleFrequency.get(0));
		assertEquals("0.667",info.alleleFrequency.get(1));		
	}

	@Test
	public void parsedFormat(){
		Sample sample= new Sample("GT:GQ:DP:HQ", "0|0:48:1");
	
		assertEquals("0|0",sample.get("GT"));
		assertEquals("48",sample.get("GQ"));
		assertEquals("1",sample.get("DP"));
		assertEquals(null,sample.get("HQ"));
	}
	
	@Test
	public void parsedMultiSampleFormat(){
		String multipleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		ParsedData data=new ParsedData(reader);
	
		Sample sample = data.variantList.get(0).sampleList.get(0);
		assertEquals("0|0",sample.get("GT"));
		assertEquals("51,51",sample.get("HQ"));
		
		Sample sample2 = data.variantList.get(0).sampleList.get(1);
		assertEquals("1|0",sample2.get("GT"));
		assertEquals("51,51",sample2.get("HQ"));
		
		Sample sample3 = data.variantList.get(0).sampleList.get(2);
		assertEquals("1/1",sample3.get("GT"));
		assertEquals(".,.",sample3.get("HQ"));
	}
	
	@Test
	public void parsedMultiLineMultiSample(){
		String multipleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n";				 
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		ParsedData data=new ParsedData(reader);
		Variant variant = data.variantList.get(0);
		
		InfoData information = variant.information;
		assertEquals("3",information.get("NS"));
		assertEquals(null,information.get("H2"));
	
		Sample sample1 = variant.sampleList.get(0);
		assertEquals("0|0",sample1.get("GT"));
		
		Sample sample2 = variant.sampleList.get(1);
		assertEquals("1|0",sample2.get("GT"));
			
		Sample sample3 = variant.sampleList.get(2);
		assertEquals("1/1",sample3.get("GT"));
		
		multipleLine += "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		reader=new BufferedReader(new StringReader(multipleLine));
		data=new ParsedData(reader);
		variant = data.variantList.get(1);
		
		information = variant.information;
		assertEquals("3",information.get("NS"));
		
		sample1 = variant.sampleList.get(0);
		assertEquals("0|0",sample1.get("GT"));
			
		sample2 = variant.sampleList.get(1);
		assertEquals("0|1",sample2.get("GT"));
			
		sample3 = variant.sampleList.get(2);
		assertEquals("0/0",sample3.get("GT"));
		
	}
	
}
