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
	
	@Test
	public void multipleSampleSingleLine(){
		String multiSampleSingleLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
									 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.";
		
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleSingleLine));
		ParsedData data=new ParsedData(reader);
		assertEquals("20", data.chrom.get(0));
		assertEquals("14370", data.pos.get(0));
		assertEquals("rs6054257", data.id.get(0));
		assertEquals("G", data.ref.get(0));
		assertEquals("A", data.alt.get(0));
		assertEquals("29", data.qual.get(0));
		assertEquals("PASS", data.filter.get(0));
		assertEquals("NS=3;DP=14;AF=0.5;DB;H2", data.info.get(0));
		assertEquals("GT:GQ:DP:HQ", data.format.get(0));
		assertEquals("0|0:48:1:51,51", data.sampleList.get(0).data.get(0));
		assertEquals("1|0:48:8:51,51", data.sampleList.get(1).data.get(0));
		assertEquals("1/1:43:5:.,.", data.sampleList.get(2).data.get(0));
		
	}
	@Test
	public void multipleSampleMultipleLine(){
		String multiSampleMultiLine="#CHROM	POS	ID	REF	ALT	QUAL	FILTER	INFO	FORMAT	NA00001	NA00002	NA00003\n"+
				 "20	14370	rs6054257	G	A	29	PASS	NS=3;DP=14;AF=0.5;DB;H2	GT:GQ:DP:HQ	0|0:48:1:51,51	1|0:48:8:51,51	1/1:43:5:.,.\n"+
				 "20	17330	.	T	A	3	q10	NS=3;DP=11;AF=0.017	GT:GQ:DP:HQ	0|0:49:3:58,50	0|1:3:5:65,3	0/0:41:3";
		
		BufferedReader reader=new BufferedReader(new StringReader(multiSampleMultiLine));
		ParsedData data=new ParsedData(reader);
		assertEquals("20", data.chrom.get(0));
		assertEquals("14370", data.pos.get(0));
		assertEquals("rs6054257", data.id.get(0));
		assertEquals("G", data.ref.get(0));
		assertEquals("A", data.alt.get(0));
		assertEquals("29", data.qual.get(0));
		assertEquals("PASS", data.filter.get(0));
		assertEquals("NS=3;DP=14;AF=0.5;DB;H2", data.info.get(0));
		assertEquals("GT:GQ:DP:HQ", data.format.get(0));
		assertEquals("0|0:48:1:51,51", data.sampleList.get(0).data.get(0));
		assertEquals("1|0:48:8:51,51", data.sampleList.get(1).data.get(0));
		assertEquals("1/1:43:5:.,.", data.sampleList.get(2).data.get(0));
		
		assertEquals("20", data.chrom.get(1));
		assertEquals("17330", data.pos.get(1));
		assertEquals(".", data.id.get(1));
		assertEquals("T", data.ref.get(1));
		assertEquals("A", data.alt.get(1));
		assertEquals("3", data.qual.get(1));
		assertEquals("q10", data.filter.get(1));
		assertEquals("NS=3;DP=11;AF=0.017", data.info.get(1));
		assertEquals("GT:GQ:DP:HQ", data.format.get(1));
		assertEquals("0|0:49:3:58,50", data.sampleList.get(0).data.get(1));
		assertEquals("0|1:3:5:65,3", data.sampleList.get(1).data.get(1));
		assertEquals("0/0:41:3", data.sampleList.get(2).data.get(1));
		
	}
}
