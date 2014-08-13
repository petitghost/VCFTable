<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
<p>Upload VCF file here.<a href="http://www.1000genomes.org/wiki/Analysis/Variant%20Call%20Format/vcf-variant-call-format-version-41"> To see format details.</a></p>
<form enctype="multipart/form-data" method="post" action="Upload">  
<input type="file" name="file" size="40" maxlength="20"/><br>
<input type="submit" value="Upload"> <input type="reset" value="Clean">
</form>
</body>
</html>