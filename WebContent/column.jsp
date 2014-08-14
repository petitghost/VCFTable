<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5" import="java.util.ArrayList,vcf_parser.Info, vcf_parser.Format"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="ColumnSelect">
<input type="checkbox" name="column" value="chrom">Chrom<br>
<input type="checkbox" name="column" value="pos">Position<br>
<input type="checkbox" name="column" value="id">ID<br>
<input type="checkbox" name="column" value="ref">Reference<br>
<input type="checkbox" name="column" value="alt">Alt<br>
<input type="checkbox" name="column" value="qual">Qual<br>
<input type="checkbox" name="column" value="filter">Filter<br>
<%
out.println("=======Info Fields========<br>");
ArrayList<Info> infoList = (ArrayList<Info>) request.getAttribute("infoColumn");
for(int i=0;i<infoList.size();i++){
	out.print("<input type=\"checkbox\" name=\"column\" value=\""+infoList.get(i).id+"\">"+infoList.get(i).id+"\t["+infoList.get(i).description+"]<br>");
}
out.println("=======Format Fields========<br>");
ArrayList<Format> formatList = (ArrayList<Format>) request.getAttribute("formatColumn");
for(int i=0;i<formatList.size();i++){
	out.print("<input type=\"checkbox\" name=\"column\" value=\""+formatList.get(i).id+"\">"+formatList.get(i).id+"\t["+formatList.get(i).description+"]<br>");
}
%>
<input type="submit" value="Select">
</form>
</body>
</html>