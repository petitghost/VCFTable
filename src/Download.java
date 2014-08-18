

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vcf_parser.ParsedData;
import vcf_parser.Variant;


@WebServlet("/Download")
public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ParsedData data=null;
		BufferedWriter brWriter=null;
		try{
			brWriter=new BufferedWriter(new FileWriter("E:/JAVAupload/output.txt"));
			data = (ParsedData) getServletContext().getAttribute("parsedData");
			ArrayList<Variant> variantList = data.variantList;
			for(int i=0;i<variantList.size();i++){
				//System.out.println(variantList.get(i).pos+"\t"+variantList.get(i).info);
				brWriter.write(variantList.get(i).pos+"\t"+variantList.get(i).info);
				brWriter.newLine();
			}
			brWriter.flush();
			brWriter.close();
		}catch(IOException ex){
			System.out.println("Write file error\n");
		}
	}

}
