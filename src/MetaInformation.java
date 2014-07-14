
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

class FilterColumn{
	String id;
	String description;
	
	FilterColumn(String content){
		String[] lines=content.split("\n");
		for(String line:lines){
			if(line.startsWith("##FILTER")){
				 this.id=line.substring(line.indexOf("ID")+"ID=".length(),line.indexOf(","));
				 this.description=line.substring(line.indexOf("\"")+1,line.lastIndexOf("\""));
			}
		}
	}
	
	

}
