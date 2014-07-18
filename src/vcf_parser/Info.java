package vcf_parser;

public class Info {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public Info(String line){
		ParserMetaField data = new ParserMetaField(line);
		this.id = data.getID();
		this.number = data.getNumber();
		this.type = data.getType();
		this.description = data.getDescription();
	}
	

}
