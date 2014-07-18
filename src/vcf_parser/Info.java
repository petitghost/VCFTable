package vcf_parser;

public class Info {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public Info(String line){
		MetaField data = new MetaField(line);
		this.id = data.getID();
		this.number = data.getNumber();
		this.type = data.getType();
		this.description = data.getDescription();
	}
	

}
