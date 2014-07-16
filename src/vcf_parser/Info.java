package vcf_parser;

public class Info {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public Info(String line){
		String[] fields= line.split(",");
		for(String field:fields){
			if(field.indexOf("ID=")!=-1){
				this.id=field.substring(field.lastIndexOf("=")+1);
			}else if(field.indexOf("Number=")!=-1){
				this.number=field.substring(field.indexOf("=")+1);
			}else if(field.indexOf("Type=")!=-1){
				this.type=field.substring(field.indexOf("=")+1);
			}else if(field.indexOf("Description=")!=-1){
				this.description=field.substring(field.indexOf('"')+1,field.lastIndexOf('"'));
			}
		}
	}

}
