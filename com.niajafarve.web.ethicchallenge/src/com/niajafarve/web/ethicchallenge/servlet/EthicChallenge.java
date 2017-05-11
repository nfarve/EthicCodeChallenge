package com.niajafarve.web.ethicchallenge.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import com.niajafarve.web.ethicchallenge.filemetrics.FileMetrics;


/**
 * Servlet implementation class EthicChallenge
 */
@WebServlet("/")
@MultipartConfig
public class EthicChallenge extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String UPLOAD_DIRECTORY = "upload";
	private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
	private static final int MAX_FILE_SIZE      = 1024 * 1024 * 512; // 512MB
	private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 530; // 530MB
	
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

		System.out.println("Trying to Post");
		Part filePart = request.getPart("uploadFile"); 
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		String wordCount; 
		
		fm = new FileMetrics();
		String fileContentType = filePart.getContentType();
		if (!fileContentType.contains("text/plain")){
			request.setAttribute("message", "You Must Upload A Text File");
			
		}else{
			StringWriter writer = new StringWriter();
			IOUtils.copy(fileContent, writer,StandardCharsets.UTF_8.name());
			wordCount = String.valueOf(fm.wordCount(writer.toString()));
			List<String> listOfPals = fm.palindromes(writer.toString());
			request.setAttribute("palList", listOfPals);
			for (String element : listOfPals) {
			    System.out.println(element);
			}
//			String theString = IOUtils.toString(fileContent, StandardCharsets.UTF_8.name()); 
	        System.out.println(wordCount);
	        request.setAttribute("wordcount", wordCount);
	        request.setAttribute("charcount", String.valueOf(fm.charCount(writer.toString())));
			
		}
		
		
        System.out.println("username: " + fileName);
 

         
        request.setAttribute("filename", fileName); 

  
       
        
        request.getRequestDispatcher("/form.jsp").forward(request, response);
	}

}
