package csc;
import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Compiler {
	
	
	// Method to read an input file and convert it to a single input string
    public static String fileToString(String fileName) throws IOException {
    	
        // Create a BufferedReader to read the input file
        BufferedReader reader = new BufferedReader(new FileReader(fileName));

        // Create a string builder to store the input string
        StringBuilder inputString = new StringBuilder();

        // Read the input file line by line and append each line to the input string
        String line = reader.readLine();
        while (line != null) {
            inputString.append(line);
            line = reader.readLine();
        }

        // Close the reader and return the input string
        reader.close();
        return inputString.toString();
    }
    
    private Lexer lexer;
    private Parser parser;
    
    public Compiler(String inputStr) {
    	this.lexer = new Lexer(inputStr);
    	this.parser = new Parser(inputStr.lexer.tokenize());
    }
    
    
    public List<Token> compile(){
    	
    	return this.lexer.tokenize();
    }
    

	    
}
