import static org.junit.Assert.*;

import org.junit.Test;

import vcf_parser.InfoData;


public class InfoColumnTest {

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

}
