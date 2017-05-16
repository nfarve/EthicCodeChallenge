<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ethic Code Challenge</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
 <script src="http://code.jquery.com/jquery-latest.min.js"></script>
 <script src="http://code.jquery.com/ui/1.11.1/jquery-ui.min.js"></script>
    <script>
    $( function() {
    
	    $("#loading").dialog({
	        hide: 'slide',
	    	show: 'slide',
	    	autoOpen: false
	    });
	    
	    $(document).on("submit", "#fileForm", function(event) {
	    	var form = new FormData($("#fileForm")[0]);
	    	$.ajax({
	    	        url: "${pageContext.request.contextPath}/",
	    	        method: "POST",
	    	        dataType: 'json',
	    	        data: form,
	    	        processData: false,
	    	        contentType: false,
	    	        beforeSend: function(){
	    	        	$("#loading").dialog('open').html("<p> Processing the file. Please wait....</p>");	
	    	        },
	    	        
	    	        success: function(result){
	    	        	console.log(result);
	    	        	$.each(result, function(index,item){
	    	        		if (index == 0){
	    	        			$("#filename").text("Filename " +item);
	    	        		}
	    	        		else if(index ==1){
	    	        			$("#count").text("# of words: " +item);
	    	        		}
	    	        		else if (index ==2){
	    	        			$("#chars").text("# of characters: " + item);
	    	        		}
	    	        		else{
	    	        			$("#pals").append("<li> "+ item + "</li>");
	    	        		}
	    	            	
	    	            });
	    	        	$("#loading").html("<p>Result Complete... </p>");
	    	        	$("#file").val('').clone(true);
	    	        },
	    	       
	    	        error: function(er){alert(er);}
	    	});
	
	        event.preventDefault(); // Important! Prevents submitting the form.
	    });
    });
    
    </script>
</head>
<body>

	<div id="loading" title="Loading"> 
    	<p>Please wait....</p>
    </div>
	<h1>${message}</h1>
	<h1>Welcome</h1>
    <p>Use the form below to upload a file: </p>
   	<div style="text-align:center;">
        <form method="post" id ="fileForm" action="${pageContext.request.contextPath}/" enctype="multipart/form-data">
            Select file to upload: <input id="file" type="file" name="uploadFile" accept="text/plain"/>
            <br/><br/>
         <input type="submit" value="Upload" />
        </form>
    </div>
   
	
	<h2 id="results">Results:</h2>
	<p id="filename">Filename: ${filename }</p>
	<p id ="count"># of words: ${wordcount }</p>
	<p id="chars"># of characters: ${charcount }</p>
	<h3 >Palindromes:</h3>
		<p ><ul id="pals"></ul>
	<table>
	  <c:forEach items="${palList}" var="item">
	    <tr>
	      <td><c:out value="${item}" /></td>
	    </tr>
	  </c:forEach>
	</table>
	
	
</body>
</html>