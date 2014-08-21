import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import vcf_parser.BodyData;
import vcf_parser.Sample;


public class SampleColumnTest {

	@Test
	public void parsedSampleFormat(){
		Sample sample= new Sample("GT:GQ:DP:HQ", "0|0:48:1:51,51");
		assertEquals("0|0",sample.get("GT"));
		assertEquals("48",sample.get("GQ"));
		assertEquals("1",sample.get("DP"));
		assertEquals("51,51",sample.get("HQ"));
		
		sample= new Sample("GT:GQ:DP:HQ", "0/0:41:3");
		assertEquals("0/0",sample.get("GT"));
		assertEquals("41",sample.get("GQ"));
		assertEquals("3",sample.get("DP"));
		assertEquals(null,sample.get("HQ"));
	}
	
	@Test
	public void parsedMultiSampleFormat(){
		String multipleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		BodyData data=new BodyData(reader);
	
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
