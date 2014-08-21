

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vcf_parser.AlleleCount;
import vcf_parser.AlleleFrequency;
import vcf_parser.BodyData;
import vcf_parser.InfoData;
import vcf_parser.Sample;
import vcf_parser.Variant;


@WebServlet("/ColumnSelect")
public class ColumnSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<String> header = new ArrayList<String>();
		ArrayList<ArrayList<String>> selectData=new ArrayList<ArrayList<String>>();
		try{
			String[] columns=request.getParameterValues("column");			
			for(String column:columns){
				header.add(column);
			}
			BodyData data = (BodyData) getServletContext().getAttribute("parsedData");
			ArrayList<Variant> variantList = data.variantList;
			for(int i=0;i<variantList.size();i++){
				ArrayList<String> eachVariant=new ArrayList<String>();
				Variant variant = variantList.get(i);				
				mandatoryColumnSelected(columns, variant, eachVariant);
				infoColumnSelected(columns, variant, eachVariant);
				formatColumnSelected(columns, variant, eachVariant);
				selectData.add(eachVariant);
			}
		} catch(NullPointerException ex) {
			System.out.print("Null point Exception");
		} catch(IllegalAccessException ex){
			System.out.print("Illegal Access Exception");
		}
		getServletContext().setAttribute("header", header);
		getServletContext().setAttribute("userSelectedData", selectData);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/Download");
    	dispatcher.forward(request, response);
	}

	private void mandatoryColumnSelected(String[] columns, Variant variant, ArrayList<String> eachVariant) throws IllegalAccessException {
		for(String column:columns){
			Field[] mandafields = variant.getClass().getFields();
			for(Field field:mandafields){
				if(field.getName().equals(column)){
					eachVariant.add(field.get(variant).toString());
				}
			}
		}
		
	}
	
	private void infoColumnSelected(String[] columns, Variant variant, ArrayList<String> eachVariant) {
		InfoData info=variant.information;
		for(String column:columns){
			if(column.equals("AC")){
				for(int j=0;j<info.alleleCountList.size();j++){
					AlleleCount alleleCount = info.alleleCountList.get(j);
					eachVariant.add(alleleCount.allele+"="+alleleCount.value+";");
				}
			}else if(column.equals("AF")){
				for(int j=0;j<info.alleleFrequencyList.size();j++){
					AlleleFrequency alleleFrequency = info.alleleFrequencyList.get(j);
					eachVariant.add(alleleFrequency.allele+"="+alleleFrequency.value+";");
				}
			}else if(info.get(column)!=null){
				eachVariant.add(info.get(column));
			}
		}
	}
	
	private void formatColumnSelected(String[] columns, Variant variant, ArrayList<String> eachVariant) {
		for(String column:columns){
			for(int s=0;s<variant.sampleList.size();s++){
				Sample sample = variant.sampleList.get(s);
				if(sample.get(column)!=null){
					eachVariant.add(sample.get(column));
				}
			}
		}
	}
	
}
