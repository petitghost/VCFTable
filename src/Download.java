

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<String> header=null;
		ArrayList<ArrayList<String>> data=null;
		BufferedWriter brWriter=null;
		try{
			brWriter=new BufferedWriter(new FileWriter("E:/JAVAupload/output.txt"));
			header = (ArrayList<String>) getServletContext().getAttribute("header");
			data = (ArrayList<ArrayList<String>>) getServletContext().getAttribute("userSelectedData");
			
			for(int i=0; i<header.size(); i++){
				brWriter.write(header.get(i)+"\t");
			}
			brWriter.newLine();
			for(int i=0; i<data.size(); i++){
				ArrayList<String> eachVariant=data.get(i);
				for(int j=0; j<eachVariant.size(); j++){
					brWriter.write(eachVariant.get(j)+"\t");
				}
				brWriter.newLine();
			}
			brWriter.flush();
			brWriter.close();
		}catch(IOException ex){
			System.out.println("Write file error\n");
		}
	}

}
