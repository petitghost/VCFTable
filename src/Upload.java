


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import vcf_parser.Format;
import vcf_parser.Info;
import vcf_parser.ParsedData;
import vcf_parser.ParsedMetaInfo;
import vcf_parser.Variant;

@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");    
		
	    PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head>");
		out.println("</head>");
		out.println("<body>");
		
		final int maxFileSize = 100 * 1024 *1024;
		final int maxMemSize = 4 * 1024;
		
		DiskFileItemFactory factory = new DiskFileItemFactory(maxMemSize, new File("D:/apache-tomcat-7.0.54/webapps/data"));
	
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax( maxFileSize );		
	 
		try{
			FileItemIterator fileItemIterator = upload.getItemIterator(request);
			while (fileItemIterator.hasNext()) {   
				FileItemStream item = fileItemIterator.next();
				InputStream inputStream = item.openStream();
				BufferedReader br=null;
        	    String fileName = item.getName();
        	    if(fileName.endsWith(".vcf")){
//        	    	br=new BufferedReader(new InputStreamReader(inputStream));
//        	    	ParsedMetaInfo metaInfo=new ParsedMetaInfo(br);
//        			ArrayList<Info> infoList = metaInfo.infoList;
//        	    	request.setAttribute("infoColumn", infoList);
//        	    	ArrayList<Format> formatList = metaInfo.formatList;
//        	    	request.setAttribute("formatColumn", formatList);
        	    	//RequestDispatcher dispatcher = request.getRequestDispatcher("/column.jsp");
        	    	//dispatcher.forward(request, response);
//        	    	br.close();
        	    	
        	    	br = new BufferedReader(new InputStreamReader(inputStream));
        	    	ParsedData data=new ParsedData(br);
        	    	getServletContext().setAttribute("parsedData", data);
        	    	br.close();
        	    	
        	    	RequestDispatcher dispatcher=request.getRequestDispatcher("/Download");
        	    	dispatcher.forward(request, response);
        	    }else{
        	    	out.println("Upload VCF file only.\n");
        	    }
	        
			}

		}catch(Exception ex){ //File large than MaxFileSize
			out.println("Unavailable file!<br>");
		}

		out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
	}

}
