import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.Test;

import vcf_parser.ParsedData;


public class DataTest {

	
	@Test
	public void singleLineTest() {
		String singleData="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	HG00406\n"+
						"1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0";	

		BufferedReader reader=new BufferedReader(new StringReader(singleData));
		ParsedData data=new ParsedData(reader);
		assertEquals("1", data.chrom.get(0));
		assertEquals("69270", data.pos.get(0));
		assertEquals("rs201219564", data.id.get(0));
		assertEquals("A", data.ref.get(0));
		assertEquals("G", data.alt.get(0));
		assertEquals("1317.77", data.qual.get(0));
		assertEquals(".", data.filter.get(0));
		assertEquals("AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09", data.info.get(0));
		assertEquals("GT:AD:DP:GQ:PL", data.format.get(0));
		assertEquals("1/1:495,134:630:99:1346,129,0", data.sampleList.get(0).data.get(0));
	}

	@Test
	public void multipleLineTest(){
		String multipleData="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	HG00406\n"+
				"1	69270	rs201219564	A	G	1317.77	.	AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09	GT:AD:DP:GQ:PL	1/1:495,134:630:99:1346,129,0\n"+
				"1	69511	rs75062661	A	G	2385.77	.	AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148	GT:AD:DP:GQ:PL	1/1:1,120:122:99:2414,195,0";

		BufferedReader reader=new BufferedReader(new StringReader(multipleData));
		ParsedData data=new ParsedData(reader);
		assertEquals("1", data.chrom.get(0));
		assertEquals("69270", data.pos.get(0));
		assertEquals("rs201219564", data.id.get(0));
		assertEquals("A", data.ref.get(0));
		assertEquals("G", data.alt.get(0));
		assertEquals("1317.77", data.qual.get(0));
		assertEquals(".", data.filter.get(0));
		assertEquals("AC=2;AF=1.00;AN=2;DB;DP=630;Dels=0.00;FS=0.000;HaplotypeScore=0.0000;MLEAC=2;MLEAF=1.00;MQ=7.62;MQ0=582;QD=2.09", data.info.get(0));
		assertEquals("GT:AD:DP:GQ:PL", data.format.get(0));
		assertEquals("1/1:495,134:630:99:1346,129,0", data.sampleList.get(0).data.get(0));
		
		assertEquals("1", data.chrom.get(1));
		assertEquals("69511", data.pos.get(1));
		assertEquals("rs75062661", data.id.get(1));
		assertEquals("A", data.ref.get(1));
		assertEquals("G", data.alt.get(1));
		assertEquals("2385.77", data.qual.get(1));
		assertEquals(".", data.filter.get(1));
		assertEquals("AC=2;AF=1.00;AN=2;BaseQRankSum=0.331;DB;DP=122;Dels=0.00;FS=0.000;HaplotypeScore=4.3605;MLEAC=2;MLEAF=1.00;MQ=29.00;MQ0=17;MQRankSum=-1.732;QD=19.56;ReadPosRankSum=1.148", data.info.get(1));
		assertEquals("GT:AD:DP:GQ:PL", data.format.get(1));
		assertEquals("1/1:1,120:122:99:2414,195,0", data.sampleList.get(0).data.get(1));
	}
}
