import static org.junit.Assert.*;

import org.junit.Test;


public class MetaInfoTest{
	String content =
			"##fileformat=VCFv4.1\n"+
			"##FILTER=<ID=LowQual,Description=\"Low quality\">\n"+
			"##FILTER=<ID=q10,Description=\"Quality below 10\">\n"+
			"##FILTER=<ID=s50,Description=\"Less than 50% of samples have data\">\n";
	
	@Test
	public void testBasicInfo() {
		MetaInformation info = new MetaInformation();
		assertEquals("VCFv4.1", info.fileformat(content));
	}
	
	@Test
	public void getFilterColumn(){
		int i=0;
		FilterColumn[] filterThreshold = new FilterColumn[10];
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.startsWith("##FILTER")){
				filterThreshold[i]=new FilterColumn(line);
				i++;
			}
		}
		
		assertEquals("LowQual", filterThreshold[0].id);
		assertEquals("Low quality", filterThreshold[0].description);
		
		assertEquals("q10", filterThreshold[1].id);
		assertEquals("Quality below 10", filterThreshold[1].description);
		
		assertEquals("s50", filterThreshold[2].id);
		assertEquals("Less than 50% of samples have data", filterThreshold[2].description);
	}
	

}
