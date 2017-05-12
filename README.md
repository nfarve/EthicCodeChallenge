# EthicCodeChallenge
Code Challenge for Ethic using Java Servlets

Code containss two projects
1.  Servlet with simple form for file upload
2.  FormMetrics to calculate the word count, character count and palindromes in a file

For Palindromes calculation, words with "," and "'" are allowed to be included in the search. However, words or phrases with special characters are excluded (@#$%^&*()).
The code checks for single words, numbers and phrases that would be considered a palindrome. 
Search is performed sentence by sentence. Therefore, palindromes longer than a single sentence will not be checked. 

External Libraries Used:
1.  JSTL
2.  Apache Commons IO
3.  Apache Commons Fileupload

Code is written with Java 8 and uses Tomcat 9.0
