import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Test;

import vcf_parser.ParsedData;
import vcf_parser.Sample;
import vcf_parser.Variant;


public class SampleColumnTest {

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
	public void parsedSampleFormat(){
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

}
