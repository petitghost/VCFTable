import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;
import java.util.ArrayList;

import org.junit.Test;

import vcf_parser.InfoData;
import vcf_parser.BodyData;
import vcf_parser.Sample;
import vcf_parser.Variant;


public class MandatoryColumnTest {
	@Test
	public void mandatoryColumnSingleSampleTest() {
		String singleSample="1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0";	
		Variant variant = new Variant(singleSample);
		
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
	public void mandatoryColumnMultiSample(){
		String multiSample="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51";		
		Variant variant = new Variant(multiSample);		
		ArrayList<Sample> sampleList = variant.sampleList;
		assertEquals("0|0:48:1:51,51", sampleList.get(0).data);
		
		multiSample += "	1|0:48:8:51,51	1/1:43:5:.,.";		
		variant = new Variant(multiSample);		
		sampleList = variant.sampleList;
		assertEquals("1|0:48:8:51,51", sampleList.get(1).data);
		assertEquals("1/1:43:5:.,.", sampleList.get(2).data);
		
		assertEquals(3,sampleList.size());
	}
	
	@Test
	public void mandatoryColumnWithoutSample(){
		String noSample="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2\n";
		Variant variant = new Variant(noSample);		
		assertEquals(null,variant.format);
		ArrayList<Sample> sampleList = variant.sampleList;
		assertEquals(0, sampleList.size());

		noSample="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ";
		variant = new Variant(noSample);		
		assertEquals("GT:GQ:DP:HQ",variant.format);
		assertEquals(0, sampleList.size());
	}
	
	@Test
	public void getSampleName(){
		String sample="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001";
		BufferedReader reader=new BufferedReader(new StringReader(sample));
		BodyData data=new BodyData(reader);
		ArrayList<String> sampleName = data.sampleName;
		assertEquals("NA00001", sampleName.get(0));
		
		sample += "	NA00002	NA00003\n";
		reader=new BufferedReader(new StringReader(sample));
		data=new BodyData(reader);
		sampleName = data.sampleName;
		assertEquals("NA00002", sampleName.get(1));
		assertEquals("NA00003", sampleName.get(2));
		
		assertEquals(3,sampleName.size());
	}
	
	@Test
	public void parsedMultiLineMultiSample(){
		String multipleLine="20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n";				 
		BufferedReader reader=new BufferedReader(new StringReader(multipleLine));
		BodyData data=new BodyData(reader);
		Variant variant = data.variantList.get(0);	
		assertEquals("14370", variant.pos);
		assertEquals("rs6054257", variant.id);
		
		InfoData information = variant.information;
		assertEquals("NS=3;DP=14;AF=0.5;DB;H2", information.data);
		
		Sample sample = variant.sampleList.get(0);
		assertEquals("0|0:48:1:51,51", sample.data);
		
		multipleLine += "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		reader=new BufferedReader(new StringReader(multipleLine));
		data=new BodyData(reader);
		variant = data.variantList.get(1);
		assertEquals(".", variant.id);
		
		sample = variant.sampleList.get(2);
		assertEquals("0/0:41:3", sample.data);
		
		assertEquals(2, data.variantList.size());
		
	}
	
}
