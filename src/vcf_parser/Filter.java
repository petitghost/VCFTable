package vcf_parser;

public class Filter {
	public String id;
	public String description;
	
	public Filter(String line) {
		this.id=line.substring(line.indexOf("ID")+"ID=".length(), line.indexOf(","));
		this.description=line.substring(line.indexOf("\"")+1, line.lastIndexOf("\""));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
