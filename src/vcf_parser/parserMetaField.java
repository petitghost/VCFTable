package vcf_parser;

public class ParserMetaField {
	public String id;
	public String number;
	public String type;
	public String description;
	
	public ParserMetaField(String line) {
		cutLine(line);
	}
	
	public void cutLine(String line){
		StringBuffer sb=new StringBuffer(line);
		int pos;
		while((pos=line.indexOf(',', line.indexOf("Description")))>-1){
			sb.deleteCharAt(pos);
			line=sb.toString();
		}
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
	
	public String getID(){
		return this.id;
	}
	
	public String getNumber(){
		return this.number;
	}

	public String getType(){
		return this.type;
	}

	public String getDescription(){
		return this.description;
	}


}
