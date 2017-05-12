package com.niajafarve.web.ethicchallenge.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;
import com.niajafarve.web.ethicchallenge.filemetrics.FileMetrics;


/**
 * Servlet implementation class EthicChallenge
 */
@WebServlet("/")
@MultipartConfig
public class EthicChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 512; // 512MB
	
	private FileMetrics fm;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EthicChallenge() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/form.jsp").forward(request, response);
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Part filePart = request.getPart("uploadFile"); 
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		String wordCount = ""; 
		String charCount = "";
		List<String> listOfPals = null;
		fm = new FileMetrics();
		String fileContentType = filePart.getContentType();
		if (!fileContentType.contains("text/plain")){
			request.setAttribute("message", "You Must Upload A Text File");
			
		}
		else if (filePart.getSize() > MAX_FILE_SIZE){
			request.setAttribute("message", "This file is too large. Max file size is " + String.valueOf(MAX_FILE_SIZE) + "MB");
		}
		else{
			//move inputstream to a StringWriters so we can use the contents more than once
			StringWriter writer = new StringWriter();
			IOUtils.copy(fileContent, writer,StandardCharsets.UTF_8.name());
			
			//Get the word count for file
			wordCount = String.valueOf(fm.wordCount(writer.toString()));
			request.setAttribute("wordcount", wordCount);
			System.out.println(wordCount);
			
			//Get character count for file
			charCount = String.valueOf(fm.charCount(writer.toString()));
			request.setAttribute("charcount", charCount);
			
			//Get the list of palindromes for the file
			listOfPals = fm.palindromes(writer.toString());
			request.setAttribute("palList", listOfPals);
			
//			for (String element : listOfPals) {
//			    System.out.println(element);
//			}
//			String theString = IOUtils.toString(fileContent, StandardCharsets.UTF_8.name()); 
	        
	
			
		}
	
		boolean ajax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
        System.out.println("filename: " + fileName);
        request.setAttribute("filename", fileName);  
        if (ajax){
        	System.out.println("ajax request");
        	List<String> list = new ArrayList<String>();
        	list.add(fileName);
        	list.add(wordCount);
        	list.add(charCount);
        	list.addAll(listOfPals);
        	String json = new Gson().toJson(list);
        	response.setContentType("application/json");
        	response.setCharacterEncoding("UTF-8");
        	response.getWriter().write(json);
        }
        else{
        	request.getRequestDispatcher("/form.jsp").forward(request, response);
        }
	}

}
