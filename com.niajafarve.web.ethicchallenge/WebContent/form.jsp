<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ethic Code Challenge</title>
</head>
<body>
	<h1>${message}</h1>
	<h1>Welcome</h1>
    <p>Use the form below to upload a file: </p>
   	<center>
        <form method="post" action="${pageContext.request.contextPath}/" enctype="multipart/form-data">
            Select file to upload: <input type="file" name="uploadFile" accept="text/plain"/>
            <br/><br/>
            <input type="submit" value="Upload" />
        </form>
    </center>
   
	
	<h2>Results:</h2>
	<p>${filename }</p>
	<p>${filecontenttype }</p>
	<p>${wordcount }</p>
	<p>${charcount }</p>
	<h3>Palindromes:</h3>
	<table>
	  <c:forEach items="${palList}" var="item">
	    <tr>
	      <td><c:out value="${item}" /></td>
	    </tr>
	  </c:forEach>
	</table>
	
	
</body>
</html>