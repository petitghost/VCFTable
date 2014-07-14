


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class Upload
 */
@WebServlet("/Upload")
public class Upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	
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
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
	    factory.setSizeThreshold(maxMemSize);
	    factory.setRepository(new File("D:/apache-tomcat-7.0.54/webapps/data"));
	
	    ServletFileUpload upload = new ServletFileUpload(factory);
	    upload.setSizeMax( maxFileSize );		
	 
		try{
			List<FileItem> itemList = upload.parseRequest(request);
			ParseFile input=new ParseFile();
			input.saveFile(itemList);

		}catch(Exception ex){ //File large than MaxFileSize
			out.println("Unavailable file!<br>");
		}

		out.println("</body>");
		out.println("</html>");
		
		out.flush();
		out.close();
	}

}
