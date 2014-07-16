package vcf_parser;

public class MetaInformation{
		
	public String fileformat(String content) {
		String[] lines=content.split("\n");
		
		for(String line:lines){
			if(line.startsWith("##fileformat")){
				return line.substring(line.indexOf("=")+1);
			}
		}
		return null;
	}	
}


