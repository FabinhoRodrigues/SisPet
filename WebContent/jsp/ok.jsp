<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sucesso</title>
<script type="text/javascript">
  document.write="Preparando...";
  window.setTimeout("location.href='/SisPet/jsp/index.jsp'", 1000)
</script> 
</head>
<body>
	<div class="alert alert-success">
		<h3>${msg}</h3>
	</div>
</body>
</html>