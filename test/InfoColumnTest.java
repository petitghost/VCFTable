import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import vcf_parser.AlleleCount;
import vcf_parser.AlleleFrequency;
import vcf_parser.InfoData;


public class InfoColumnTest {

	@Test
	public void parsedInfo(){
		InfoData info=new InfoData("G","NS=3;DP=11;AF=0.017;DB");
		assertEquals("3",info.get("NS"));
		assertEquals("11",info.get("DP"));
		
		AlleleFrequency alleleFrequency = info.alleleFrequencyList.get(0);
		assertEquals("G",alleleFrequency.allele);
		assertEquals("0.017",alleleFrequency.value);
		
		assertEquals("true",info.get("DB")); //It's string not boolean
		assertEquals(null,info.get("AA"));
	}
	
	@Test
	public void parsedInfoMultiField(){
		InfoData info=new InfoData("G,T","NS=2;DP=10;AC=3,6;AF=0.333,0.667;AA=T;DB");
		
		ArrayList<AlleleCount> alleleCountList = info.alleleCountList;
		assertEquals("T",alleleCountList.get(1).allele);
		assertEquals("6",alleleCountList.get(1).value);
		
		ArrayList<AlleleFrequency> alleleFrequencyList = info.alleleFrequencyList;
		assertEquals("G",alleleFrequencyList.get(0).allele);
		assertEquals("0.333",alleleFrequencyList.get(0).value);
		
		assertEquals("T",alleleFrequencyList.get(1).allele);
		assertEquals("0.667",alleleFrequencyList.get(1).value);
		
	}

}
