package csc;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
	private String inputStr;
	
	public Lexer(String inputStr) {
		this.inputStr = inputStr;
	}
	
	public List<Token> tokenize() {
		 List<Token> tokens = new ArrayList<>();
		    
		    Pattern blockCommentPattern = Pattern.compile("/\\*[^*]*\\*+(?:[^/*][^*]*\\*+)*/");
		    
		    Matcher blockCommentMatcher = blockCommentPattern.matcher(inputStr);
		    
		    inputStr = blockCommentMatcher.replaceAll("");
		    
		    Pattern singleLineCommentPattern = Pattern.compile("//.*$", Pattern.MULTILINE);
		    
		    Matcher singleLineCommentMatcher = singleLineCommentPattern.matcher(inputStr);
		    
		    inputStr = singleLineCommentMatcher.replaceAll("");
		    
		    return tokens;
		     
		    }
	
	 // Represents fractional numbers
    private static final Pattern real_literal = Pattern.compile("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?");
    
    // Represents naturals numbers
    private static final Pattern natural_literal = Pattern.compile("[0-9]+");
    
    // Represents boolean values
    private static final Pattern bool_literal = Pattern.compile("true|false");
    
    // Represents a single ascii character including escape character
    private static final Pattern char_literal = Pattern.compile("'(\\\\.|[^\\\\'])'");
    
    // Represents any number of ascii characters including escape character
    private static final Pattern string_literal = Pattern.compile("\"(\\\\.|[^\\\\\"])*\"");
    
    // Keywords for selection statements, loop statements, and variable declarations
    private static final Pattern keywordsPattern = Pattern.compile("if|else|while|String|int|char|double|boolean");
    
    // Special symbols for arithmetic operations, breaking order of operations, comparison operations, logical operations, and grouping
    private static final Pattern specialSymbolsPattern = Pattern.compile("[+-/*^()<>=!&|{}]|\\.\\.|\\+\\+|--");
    
    // Variable/function identifier
    private static final Pattern identifierPattern = Pattern.compile("[a-zA-Z_][a-zA-Z0-9_]*");
    
    public static boolean isRealLiteral(String token) {
        Matcher matcher = real_literal.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isNaturalLiteral(String token) {
        Matcher matcher = natural_literal.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isBoolLiteral(String token) {
        Matcher matcher = bool_literal.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isCharLiteral(String token) {
        Matcher matcher = char_literal.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isStringLiteral(String token) {
        Matcher matcher = string_literal.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isKeyword(String token) {
        Matcher matcher = keywordsPattern.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isSpecialSymbol(String token) {
        Matcher matcher = specialSymbolsPattern.matcher(token);
        return matcher.matches();
    }
    
    public static boolean isIdentifier(String token) {
        Matcher matcher = identifierPattern.matcher(token);
        return matcher.matches();
    }
}

	
