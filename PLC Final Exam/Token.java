package csc;

public class Token {
	
	//String for lexeme representation
	  private String lexeme;
	  
	  //Int for token code
	  private int tokenCode;

	  // Constructor for initializing the lexeme and token code
	  public Token(String lexeme, int tokenCode)
	  {
	    this.lexeme = lexeme;
	    this.tokenCode = tokenCode;
	  }

	  // Getter for the lexeme
	  public String getLexeme()
	  {
	    return this.lexeme;
	  }
	  
	  //Setter method for the lexeme
	  public void setLexeme(String lexeme) {
	        this.lexeme = lexeme;
	    }

	  // Getter for the token code
	  public int getTokenCode()
	  {
	    return this.tokenCode;
	  }
	  
	  //Setter method for the token code
	  public void setTokenCode(int tokenCode) {
		  this.tokenCode = tokenCode;
	  }
	  
	  private TokenType type;
	  private Object value;

	  public Token(TokenType type, Object value) {
	    this.type = type;
	    this.value = value;
	  }

	  public TokenType getType() {
	    return type;
	  }

	  public Object getValue() {
	    return value;
	  }

	  public enum TokenType {
		  VAR,
		  IF,
		  ELSE_IF,
		  WHILE,
		  FUNCTION_CALL,
		  VARIABLE,
		  REAL_LITERAL,
		  NATURAL_LITERAL,
		  BOOLEAN_LITERAL,
		  CHARACTER_LITERAL,
		  STRING_LITERAL,
		  NEGATE,
		  ASSIGN,
		  ADD,
		  SUBTRACT,
		  MULTIPLY,
		  DIVIDE,
		  MODULO,
		  EQUAL,
		  NOT_EQUAL,
		  LESS_THAN,
		  LESS_THAN_EQUAL,
		  GREATER_THAN,
		  GREATER_THAN_EQUAL,
		  LEFT_PAREN,
		  RIGHT_PAREN,
		  LEFT_BRACE,
		  RIGHT_BRACE,
		  CODE_BLOCK,
		  SELECTION_STATEMENT,
		  LOOP_STATEMENT,
		  DECLARATION_STATEMENT
		}
}
