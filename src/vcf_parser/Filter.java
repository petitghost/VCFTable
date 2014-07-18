package vcf_parser;

public class Filter {
	public String id;
	public String description;
	
	public Filter(String line) {
		ParserMetaField data=new ParserMetaField(line);
		this.id=data.getID();
		this.description=data.getDescription();
	}

}
