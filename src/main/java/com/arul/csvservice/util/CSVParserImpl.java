package com.arul.csvservice.util;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
/**
* @author Arul J
* 
*/
public class CSVParserImpl implements CSVParser{
	
/**
* Reads a line of text.
* Iterates each character and temporarily stores in staging array.
* A Comma character when read will trigger end of a column and transfers staging characters to tokenArray meant to be collecting all tokens.
* A comma is not considered a end of column when the first element in staging is a " and last character is not ".
* Remove quotes from each token if present.
* Surrund each token with []
* return the tokean array.
* Time complexity is O(n) and space complexity is O(n+m)
*/

public 	String parseLine(String input){
		ArrayList<String> tokens = new ArrayList<>();
		List<Character> inputChrs = input.chars().mapToObj(x->(char)x).collect(Collectors.toList());
		List<Character> stagingChars = new ArrayList<>();

		int i=0;

		while (i<input.length()){
			Character curr =  inputChrs.get(i);
		    
			if ((curr==',') && (stagingChars.get(0)=='"') && (stagingChars.get(stagingChars.size()-1)!='"')){
				stagingChars.add(',');
			} else if (curr==','){
				String token = stagingCharsAsString(stagingChars);
				tokens.add(token);
		        stagingChars.clear();
			} else {
				stagingChars.add(curr);
			}
			i++;
			
		}//end of while loop
		//flush the staging chars
		String token = stagingCharsAsString(stagingChars);
		tokens.add(token);

		return tokens.stream().map(String::valueOf).collect(Collectors.joining());
}

private String stagingCharsAsString(List<Character> stagingChars){

		if ( stagingChars.get(0)=='"'){
			stagingChars.remove(0);
		}
		if ( stagingChars.get(stagingChars.size()-1)=='"') {
			stagingChars.remove(stagingChars.size()-1);
		}
		String token = stagingChars.stream().map(String::valueOf).collect(Collectors.joining());
		return "["+token+"]";
}

}