

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ParseVCF
 */
@WebServlet("/ParseVCF")
public class ParseVCF extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ParseVCF() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	public String readFile(String path, String fileName){ // Read disk file
		try{
			FileReader fr=new FileReader(path+fileName);
			BufferedReader br=new BufferedReader(fr);
			String line=new String();
			while(br.ready()){
				line=line+br.readLine()+"\n";
			}
			br.close();
			fr.close();
			return line;
		}catch(Exception ex){
			System.out.print("Open file error");
			return "";
		}
		
	}
	
	public String readFileInMemory(byte[] data){
		String s=new String(data);
		//System.out.print(s);
		return s;
	}
	
	public void parseFile(String content, ArrayList<String> columnList){
		
		String[] line = content.split("\n");
		for(String s:line){
			if(!s.startsWith("#")){
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
