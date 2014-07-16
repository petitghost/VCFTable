package vcf_parser;

public class Format {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public Format(String line) {
		parserMetaField data = new parserMetaField(line);
		this.id = data.getID();
		this.number = data.getNumber();
		this.type = data.getType();
		this.description = data.getDescription();
	}

}
