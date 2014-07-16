package vcf_parser;

public class Filter {
	public String id;
	public String description;
	
	public Filter(String line) {
		parserMetaField data=new parserMetaField(line);
		this.id=data.getID();
		this.description=data.getDescription();
	}

}
