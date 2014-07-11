import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

public class ParseFile {
	public static void main(String[] args) {
		readFile("E:/JAVAupload/short.vcf");
	}
	
	public void saveFile(List<FileItem> itemList){
	    try{
	    	//ArrayList<String> col=new ArrayList<String>();
			for (FileItem item : itemList) {      
		        if(item.isFormField()){
//		        	String fieldName = item.getFieldName();
//		        	if(fieldName.equals("column")){
//		        		col.add(item.getString());
//		        	}      
		        }else{
	        	    String fileName = item.getName();
	        	    	        	    
	        	    if(fileName.endsWith(".vcf")){
	        	    	//ParseVCF file = new ParseVCF();
	        	    	if(item.isInMemory()){
	        	    		byte[] data = item.get();
	        	    		readFileInMemory(data);
	        	    	}else{
		        	    	File writeFile=new File("E:/JAVAupload/",fileName);
		        	    	item.write(writeFile);
		        	    	readFile("E:/JAVAupload/"+fileName);
	        	    	} 
	        	    }else{
	        	    	System.out.println("Upload VCF file only.\n");
	        	    }
		        }
			}
	    }catch(Exception ex){
	    	System.out.println("Save file error\n");
	    }
		
	}
	
	public static void readFile(String file){ // Read disk file
		try{
			FileReader fr=new FileReader(file);
			BufferedReader br=new BufferedReader(fr);
			String line=new String();
			while(br.ready()){
				line=line+br.readLine()+"\n";
			}
			br.close();
			fr.close();
			getVcfField(line);
		}catch(Exception ex){
			System.out.print("Open file error");
		}
		
	}
	
	public void readFileInMemory(byte[] data){
		String s=new String(data);
		getVcfField(s);
	}
	
	public static void getVcfField(String content){
			String[] lines = content.split("\n");
		for(String line:lines){
			if(line.startsWith("##")){
				parseLine(line);
			}
		}
	}
	private static void parseLine(String line) {
		String fields[] = line.split(",");
		if(fields[0].startsWith("##FORMAT")){
			//checkbox = checkbox +"<input type=\"checkbox\" name=\"format\" value=\""+metaInfoID[1]+"\">"+metaInfoDes[1]+"<br>"+"\n";
			String metaInfoIDs[]=fields[0].split("ID=");
			String metaInfoDes[]=fields[3].split("\"");
			System.out.println("[Format]" +metaInfoIDs[1]+ ":" +metaInfoDes[1]);
		}else if(fields[0].startsWith("##INFO")){
			//checkbox = checkbox + "<input type=\"checkbox\" name=\"info\" value=\""+metaInfoID[1]+"\">"+metaInfoDes[1]+"<br>"+"\n";
			String metaInfoIDs[]=fields[0].split("ID=");
			String metaInfoDes[]=fields[3].split("\"");
			System.out.println("[Info]" +metaInfoIDs[1]+ ":" +metaInfoDes[1]);
		}
	}
	
	public void parseFile(String content, ArrayList<String> columnList){
		
		String[] line = content.split("\n");
		for(String s:line){
			if(s.startsWith("##")){
				String[] field = s.split(",");
				String[] metaInfoID=field[0].split("ID=");
				String[] metaInfoDes=field[3].split("\"");
				if(field[0].startsWith("##FORMAT")){
					System.out.println("[Format]" +metaInfoID[1]+ ":" +metaInfoDes[1]);
				}else if(field[0].startsWith("##INFO")){
					System.out.println("[Info]" +metaInfoID[1]+ ":" +metaInfoDes[1]);
				}
			}else if(s.startsWith("#")){
				
			}else{
				String[] field = s.split("\t");
				if(!columnList.isEmpty()){
					for(int i=0;i< columnList.size();i++){
						System.out.print(field[Integer.parseInt(columnList.get(i))]+"\t");
					}
					System.out.print("\n");
				}else{
					System.out.println("no selected");
				}
				
				
			}
		}
		
	}
	

}
