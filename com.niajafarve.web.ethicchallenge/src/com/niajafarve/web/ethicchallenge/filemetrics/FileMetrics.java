package com.niajafarve.web.ethicchallenge.filemetrics;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringJoiner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileMetrics {

	public int wordCount(String input){
		//Takes a String and returns the number of words
		if (input.length()> 0 ){
			return input.split("\\s+").length;
		}
		else
			return 0;
		
	}
	
	public int charCount(String str){
		//Takes a string and returns the number of characters in the string
		str = str.replaceAll("\\r|\\n", "");
//		System.out.println(str);
		return str.length();
	}
	
	public List<String> palindromes(String strinput){
		//Checks for palindromes by first taking the text and splitting into sentences then checking each word in the sentence
		//before checking for palindrome phrases
		BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.US);
		iterator.setText(strinput);
		int start = iterator.first();
		String str;
		String test;
		List<String> listOfPal = new ArrayList<String>();
		
		//Exclude special characters
		Pattern p = Pattern.compile("[@#$%^&*()]", Pattern.CASE_INSENSITIVE);
		for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
			str = strinput.substring(start,end).trim();
			if(start==0 && end!=strinput.length()){
				//remove the end of sentence character if there are sentences in the text
				str = str.substring(0, str.length()-1);
			}
			
			str = str.replaceAll("\\r|\\n","");
			String elements[] = str.split("\\s+");
//			System.out.println(str);
			
			//First check each word for palindromes
			for (int i=0; i< elements.length; i++){
				test = elements[i].replaceAll("([\\.,'!?])", "").toLowerCase();
//				System.out.println(test);
				
				if (!p.matcher(test).find() && test.equals(new StringBuffer(test).reverse().toString()) && test.length() > 1){
					if (listOfPal.indexOf(elements[i]) == -1){
						listOfPal.add(elements[i]);
					}
				}
			}
			
			//Next check for palindromes phrases
			for (int i = 0; i < elements.length-1; i++) {
				StringJoiner joiner = new StringJoiner(" ");
				joiner.add(elements[i]);
		        for (int j = i+1;  j<elements.length; j++) {
		        	joiner.add(elements[j]);
		        	test = joiner.toString().replaceAll("\\s+","").replaceAll("([\\.,'!?])", "").toLowerCase();
//		        	System.out.println(joiner.toString() + String.valueOf(i) + String.valueOf(j));
//		        	System.out.println(test);
		        	if (!p.matcher(test).find() && test.equals(new StringBuffer(test).reverse().toString()) && test.length() >1){
		    				if(listOfPal.indexOf(joiner.toString()) == -1){
		    					listOfPal.add(joiner.toString());
		    				}
		            } 
		        }
		    }
		}
		return listOfPal;
	}
}
