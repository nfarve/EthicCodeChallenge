# EthicCodeChallenge
Code Challenge for Ethic using Java Servlets

Code containss two projects
1.  Servlet with simple form for file upload
2.  FormMetrics to calculate the word count, character count and palindromes in a file

For Palindromes calculation, words with "," and "'" are allowed to be included in the search. However, words or phrases with special characters are excluded (@#$%^&*()).
The code checks for single words, numbers and phrases that would be considered a palindrome. 
Search is performed sentence by sentence. Therefore, palindromes longer than a single sentence will not be checked. 

Form currently submits using an ajax requests, but also has capability for traditional post requests. 

Files uploaded must be text files and less than 512 MB. 

External Libraries Used:
1.  JSTL
2.  Apache Commons IO
3.  Apache Commons Fileupload
4.  Google GSON

Code is written with Java 8 and uses Tomcat 9.0

To deploy:
1.  Ensure there is a user with manager-gui and manager-script roles on your tomcat server
2.  Update build.properties with the username and password of user above
3.  Run ant to build all neccessary files
4.  Run "ant deploy" to deploy to the path /ethic
