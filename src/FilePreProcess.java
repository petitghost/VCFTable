import java.io.BufferedReader;
import java.io.StringReader;
import java.util.List;

import org.apache.commons.fileupload.FileItem;

import vcf_parser.ParsedData;
import vcf_parser.ParsedMetaInfo;

public class FilePreProcess {	
	public FilePreProcess(List<FileItem> itemList){
	    try{
			for (FileItem item : itemList) {      
		        if(item.isFormField()){ // if not a file
//		        	String fieldName = item.getFieldName();
		        }else{
	        	    String fileName = item.getName();
	        	    if(fileName.endsWith(".vcf")){
	        	    	String s=new String(item.get());
	        	    	BufferedReader br=new BufferedReader(new StringReader(s));
	        			ParsedMetaInfo metaInfo=new ParsedMetaInfo(br);
	        			br.close();
	        			
	        			br=new BufferedReader(new StringReader(s));
	        			ParsedData data=new ParsedData(br);
	        			br.close();
	        			
	        	    }else{
	        	    	System.out.println("Upload VCF file only.\n");
	        	    }
		        }
			}
	    }catch(Exception ex){
	    	System.out.println("Save file error\n");
	    }
		
	}

}
