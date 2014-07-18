package vcf_parser;

public class ParsedMetaInfo {

	public Filter[] filter = new Filter[10];
	public Format[] format=new Format[10];
	public Info[] information=new Info[10];
	
	public ParsedMetaInfo(String content) {
		int filterCount=0, formatCount=0, infoCount=0;
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.contains("=<")){
				if(line.startsWith("##FILTER")){
					filter[filterCount]=new Filter(line);
					filterCount++;
				}else if(line.startsWith("##FORMAT")){
					format[formatCount]=new Format(line);
					formatCount++;
				}else if(line.startsWith("##INFO")){
					information[infoCount]=new Info(line);
					infoCount++;
				
				}
			}
		}		
	}

	

}
