package vcf_parser;

public class Format {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public Format(String line) {
		MetaField data = new MetaField(line);
		this.id = data.getID();
		this.number = data.getNumber();
		this.type = data.getType();
		this.description = data.getDescription();
	}

}
