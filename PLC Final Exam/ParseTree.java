package csc;

import java.util.*;

public class ParseTree {
	private TokenType type;
	  private Object value;
	  private List<ParseTree> children;

	  public ParseTree(TokenType type) {
	    this.type = type;
	    this.value = null;
	    this.children = new ArrayList<>();
	  }

	  public ParseTree(Parser.TokenType type) {
		  this.type = Parser.TokenType;
	  }
	  public ParseTree(TokenType type, Object value) {
	    this.type = type;
	    this.value = value;
	    this.children = new ArrayList<>();
	  }

	  public void addChild(ParseTree expressionTree) {
	    children.add(expressionTree);
	  }

	  public TokenType getType() {
	    return type;
	  }

	  public Object getValue() {
	    return value;
	  }

	  public List<ParseTree> getChildren() {
	    return children;
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
		  SELECTION_STATEMENT,
		  LOOP_STATEMENT,
		  DECLARATION_STATEMENT
		}
}


