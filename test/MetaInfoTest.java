import static org.junit.Assert.*;

import org.junit.Test;


public class MetaInfoTest{
	String content =
			"##fileformat=VCFv4.1\n"+
			"##FILTER=<ID=LowQual,Description=\"Low quality\">\n";
	
	@Test
	public void testBasicInfo() {
		MetaInformation info = new MetaInformation();
		assertEquals("VCFv4.1", info.fileformat(content));
	}
	
	@Test
	public void getFilterColumn(){
		FilterColumn filterThreshold = new FilterColumn(content);
		assertEquals("LowQual", filterThreshold.id);
		assertEquals("Low quality", filterThreshold.description);
	}

}
