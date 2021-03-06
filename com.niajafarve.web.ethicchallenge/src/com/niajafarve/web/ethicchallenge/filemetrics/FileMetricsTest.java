package com.niajafarve.web.ethicchallenge.filemetrics;

import static org.junit.Assert.*;
import java.util.List;

import org.junit.Test;




public class FileMetricsTest {
	

	@Test
	public void testWordCount() {
		String str1 = "some test text that should be 8 words";
		String str2 = "asda\n"
					+ "d adsjhadkhad akjdh\n"
					+ "sdjfs dfkhs fkjshfd sdfjhks fd\n"
					+ "sfdjshkf hskjf hdsfsdfkjha dkjhwaeuoih isufh skjdf cvscvf sdkjfhskj dahjksdha sd\n"
					+ "asdjkahd kajsdhakjsdh akjsdh kajsdhkj fhsjkdfhs kdfjhsjk df\n"
					+ "asdjadfhksjdfh sf\n"
					+ "asdjhas djakhsdakjsdherdfjashdf ksjhdf\n" 
					+ "dfjks df\n";//33
	
		int wordCount1 = FileMetrics.wordCount(str1);
		int wordCount2 = FileMetrics.wordCount(str2);
		assertEquals(8,wordCount1);
		assertEquals(0, FileMetrics.wordCount(""));
		assertEquals(33, wordCount2);
		
	}
	
	@Test
	public void testCharacterCount(){
		
		String str1 = "Hello how are you";//17
		String str2 = "";//0
		String str3 = "asda\n"
					+ "d adsjhadkhad akjdh\n"
					+ "sdjfs dfkhs fkjshfd sdfjhks fd\n"
					+ "sfdjshkf hskjf hdsfsdfkjha dkjhwaeuoih isufh skjdf cvscvf sdkjfhskj dahjksdha sd\n"
					+ "asdjkahd kajsdhakjsdh akjsdh kajsdhkj fhsjkdfhs kdfjhsjk df\n"
					+ "asdjadfhksjdfh sf\n"
					+ "asdjhas djakhsdakjsdherdfjashdf ksjhdf\n" 
					+ "dfjks df";//255
		assertEquals(17, FileMetrics.charCount(str1));
		assertEquals(0, FileMetrics.charCount(str2));
		assertEquals(255, FileMetrics.charCount(str3));
				
	}
	
	@Test
	public void testPalin(){

		List<String> list1 = FileMetrics.palindromes("");
		List<String> list2 = FileMetrics.palindromes("racecar adsa asdasd 12321 asdads, dasads madam madam Im adam");//racear, 12321, madam, madam Im adam
		List<String> list4 = FileMetrics.palindromes("racecar adsa asdasd 12321\n asdads, dasads madam\n madam Im\n adam");//racear, 12321, madam, madam Im adam
		List<String> list3 = FileMetrics.palindromes("sadsjasdjdshjkad asjh adskjh asdkjh asdjkh asdjh adsjk hadskjh adsjk adsjkh ads");
		List<String> list5 = FileMetrics.palindromes("Racecar. Madam Im. Adam.");//racecar madam
		List<String> list6 = FileMetrics.palindromes("Racecar.\n Madam Im. Adam.");//Test to make sure new lines have no effect
		List<String> list7 = FileMetrics.palindromes("COPYRIGHT, 1877, BY JAMES R. OSGOOD AND CO.\n 1897, BY HOUGHTON, MIFFLIN AND CO.\n ALL RIGHTS RESERVED James R.\n sfsdfsdfsfsfdsfsf Im. Adam.");//test with several periods
		List<String> list8 = FileMetrics.palindromes("*** &&&&& R&R&R&R&R&R&R");//test with special characters

//		for (String element : list7) {
//		    System.out.println(element);
//		}
		
		assertEquals(0,list1.size());
		assertEquals(0, list3.size());
		assertEquals(5, list2.size());
		assertEquals("racecar", list2.get(0));
		assertEquals("madam", list2.get(2));
		assertEquals("12321", list2.get(1));
		assertEquals("madam Im adam", list2.get(4));
		assertEquals(5, list4.size());
		assertEquals("racecar", list4.get(0));
		assertEquals("madam", list4.get(2));
		assertEquals("12321", list4.get(1));
		assertEquals("madam Im adam", list4.get(4));
		assertEquals(2,list5.size());
		assertEquals(2,list6.size());
		assertEquals(0,list7.size());
		assertEquals(0,list8.size());
		
	}

}
